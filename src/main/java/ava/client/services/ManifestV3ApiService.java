package ava.client.services;

import ava.client.grpc.AvaGrpcClient;
import ava.client.models.AvaClientSettings;
import ava.client.models.AvaManifestV3Hash;
import ava.client.models.ManifestV3;
import ava.client.models.requests.*;
import ava.client.http.AvaHttpClient;
import ava.client.utilities.AvaApiClientUtilityFactory;
import ava.client.utilities.ManifestHelper;
import ava.client.utilities.cheating.CheatTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ManifestV3ApiService  implements IManifestV3ApiService {

        private final AvaHttpClient avaHttpClient;
        // private final Cache<String, Object> cache;
        private final AvaClientSettings settings;
        private final AvaGrpcClient avaGrpcClient;

        // Cache<String, Object> cache,
    public ManifestV3ApiService(AvaHttpClient avaHttpClient,
                                AvaClientSettings settings, AvaGrpcClient avaGrpcClient) {
            this.avaHttpClient = avaHttpClient;
            // this.cache = cache;
            this.settings = settings;
            this.avaGrpcClient = avaGrpcClient;
        }

    @Override
    public CompletableFuture<ManifestV3> generateAsync(String dabInAppId, long psaId, Long optionalId,
                                                       Map<String, Object> parameterMappings,
                                                       LocalDateTime cheatTime, Duration cheatTimeOffset, boolean useGrpc) {
        CheatTime timeCheat = new CheatTime(cheatTime, cheatTimeOffset);
        return internalGenerateAsync(dabInAppId, String.valueOf(psaId), optionalId, parameterMappings, false, timeCheat, useGrpc);
    }


    private CompletableFuture<ManifestV3> internalGenerateAsync(String dabInAppId, String psaId, Long optionalId,
                                                                Map<String, Object> parameterMappings,
                                                                boolean forceRefresh, CheatTime cheatTime, boolean useGrpc) {
        CompletableFuture<ManifestV3> manifestFuture = CompletableFuture.completedFuture(null);

        return tryGetAndStoreMappingHashAsync(dabInAppId, psaId, optionalId, parameterMappings, cheatTime)
                .thenCompose(hash -> {

//                    if (!forceRefresh) {
//                        manifestFuture = getAsync(hash);
//                    }

                    return manifestFuture.thenCompose(manifest -> {
                        if (manifest != null) {
                            return CompletableFuture.completedFuture(manifest);
                        }

                        // To ensure hash has value or actually run - as Thomas said
                        // Try to request mapping hash one more time ^0^
                        return tryGetAndStoreMappingHashAsync(dabInAppId, psaId, optionalId, parameterMappings,
                                cheatTime)
                                .thenCompose(finalHash -> {
                                    // Use the abstraction layer to determine HTTP or gRPC
                                    var apiClientUtility = AvaApiClientUtilityFactory.create(avaHttpClient,
                                            avaGrpcClient, useGrpc);

                                    // isMinifyManifest is true to get the minify manifest.
                                    return apiClientUtility
                                            .generateManifestAsync(dabInAppId, psaId, optionalId, parameterMappings,
                                                    cheatTime, true)
                                            .thenApply(generatedManifest -> {
                                                if (generatedManifest != null) {
                                                    generatedManifest = ManifestHelper
                                                            .originalManifestInPlace(generatedManifest);
                                                }

//                                                // Cache regular manifest
//                                                if (!isManifestByHashLocalCacheDisabled()) {
//                                                    var cacheItemPolicy = CacheUtils.createManifestsCachePolicy(
//                                                            settings.getCachePolicies(), OffsetDateTime.now());
//                                                    cacheClient.add(finalHash, generatedManifest, cacheItemPolicy);
//                                                }
//
//                                                // Cache manifest with global hash
//                                                tryAddManifestToLocalCacheByGlobalHash(dabInAppId, psaId,
//                                                        generatedManifest);

                                                return generatedManifest;
                                            });
                                });
                    });
                });
    }



    private CompletableFuture<String> tryGetAndStoreMappingHashAsync(String dabInAppId, String psaId, Long optionalId,
                                                                     Map<String, Object> parameterMappings, CheatTime cheatTime) {
        return getMappingHashAsync(dabInAppId, psaId, optionalId)
                .thenApply(mappingHash -> mappingHash.getHash())
                .exceptionally(throwable -> {
                    if (throwable.getCause() != null &&
                            throwable.getMessage().contains(String.valueOf(404))) {

                        ManifestV3GetManifestHashRequest request = new ManifestV3GetManifestHashRequest(dabInAppId,
                                psaId, parameterMappings);
                        request.applyCheatTime(cheatTime);

                        try {
                            AvaManifestV3Hash avaManifestV3Hash = avaHttpClient
                                    .makeRequestAsync(request, AvaManifestV3Hash.class).join();
                            storeMappingHashAsync(dabInAppId, psaId, avaManifestV3Hash.getHash(), optionalId);


                            ObjectMapper mapper = new ObjectMapper()
                                    .findAndRegisterModules() // automatically registers jsr310, etc.
                                    .enable(SerializationFeature.INDENT_OUTPUT); // pretty print

                            String json = mapper.writeValueAsString(avaManifestV3Hash.getHash());

                            return json ;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    throw new RuntimeException(throwable);
                });
    }

    CompletableFuture<AvaManifestV3Hash> getMappingHashAsync(String dabInAppId, String userId, Long optionalId) {
        return avaHttpClient.makeRequestAsync(new ManifestMappingGetRequest(dabInAppId, userId, optionalId),
                AvaManifestV3Hash.class);
    }

    CompletableFuture<String> storeMappingHashAsync(String dabInAppId, String userId, String hash, Long optionalId) {
        return avaHttpClient.makeRequestAsync(new ManifestMappingStoreRequest(dabInAppId, userId, hash, optionalId),
                String.class);
    }

    @Override
        public CompletableFuture<Object> getGlobalHashAsync(String dabInAppId, String psaId) {
            // Fetch from API
            IAvaHttpRequest request = new ManifestV3GetGlobalHashGetRequest(dabInAppId, psaId);
            return avaHttpClient.makeRequestAsync(request, Object.class)
                    .thenApply(globalHash -> {
//                        // Add global hash that is found from Ava API to local cache
//                        addGlobalHashToCache(dabInAppId, psaId, globalHash);
                        return globalHash;
                    })
                    .exceptionally(throwable -> null); // Return null on exception
        }

}

