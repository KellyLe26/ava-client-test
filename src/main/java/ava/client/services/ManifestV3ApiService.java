package ava.client.services;

import ava.client.models.AvaClientSettings;
import ava.client.models.requests.IAvaHttpRequest;
import ava.client.models.requests.ManifestV3GetGlobalHashGetRequest;
import ava.client.http.AvaHttpClient;

import java.util.concurrent.CompletableFuture;

public class ManifestV3ApiService  implements IManifestV3ApiService {

        private final AvaHttpClient httpClient;
        // private final Cache<String, Object> cache;
        private final AvaClientSettings settings;
      //  private final AvaGrpcClient grpcClient;

        // Cache<String, Object> cache,
    public ManifestV3ApiService(AvaHttpClient httpClient,
                                AvaClientSettings settings) {
            this.httpClient = httpClient;
            // this.cache = cache;
            this.settings = settings;
           // this.grpcClient = grpcClient;
        }


        @Override
        public CompletableFuture<Object> getGlobalHashAsync(String dabInAppId, String psaId) {
            // Fetch from API
            IAvaHttpRequest request = new ManifestV3GetGlobalHashGetRequest(dabInAppId, psaId);
            return httpClient.makeRequestAsync(request, Object.class)
                    .thenApply(globalHash -> {
//                        // Add global hash that is found from Ava API to local cache
//                        addGlobalHashToCache(dabInAppId, psaId, globalHash);
                        return globalHash;
                    })
                    .exceptionally(throwable -> null); // Return null on exception
        }



}

