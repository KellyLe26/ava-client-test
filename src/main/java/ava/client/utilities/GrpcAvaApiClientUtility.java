package ava.client.utilities;

import ava.client.grpc.IAvaGrpcClient;
import ava.client.utilities.IAvaApiClientUtility;
import ava.client.models.ManifestV3;
import ava.client.utilities.cheating.CheatTime;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * gRPC implementation of the API client utility
 */
public class GrpcAvaApiClientUtility implements IAvaApiClientUtility {


    private final IAvaGrpcClient avaGrpcClient;
   // private final ManifestGrpc.ManifestFutureStub manifestClient;

    public GrpcAvaApiClientUtility(IAvaGrpcClient avaGrpcClient) {
        if (avaGrpcClient == null) {
            throw new IllegalStateException(
                    "The gRPC endpoint has not been configured. Please set it before using gRPC features.");
        }
        this.avaGrpcClient = avaGrpcClient;
       // this.manifestClient = avaGrpcClient.createManifestClient(); // stub for async calls
    }



    @Override
    public CompletableFuture<ManifestV3> generateManifestAsync(String dabInAppId, String psaId, Long optionalId, Map<String, Object> parameterMappings, CheatTime cheatTime, boolean isMinifyManifest) {
        return null;
    }

    @Override
    public CompletableFuture<ManifestV3> getManifestByGlobalHashAsync(String dabInAppId, String globalHash, boolean isMinifyManifest) {
        return null;
    }

//    @Override
//    public CompletableFuture<ManifestV3> generateManifestAsync(
//            String dabInAppId,
//            String psaId,
//            Long optionalId,
//            Map<String, Object> parameterMappings,
//            Object cheatTime, // replace with CheatTime class
//            boolean isMinifyManifest
//    ) {
//        GenerateManifestRequest.Builder requestBuilder = GenerateManifestRequest.newBuilder()
//                .setDabInAppId(dabInAppId)
//                .setPsaId(psaId)
//                .setP(JsonUtil.toJson(parameterMappings)) // assuming JsonUtil.toJson exists
//                .setIsMinifyManifest(isMinifyManifest);
//
//        if (optionalId != null) {
//            requestBuilder.setOptionalId(optionalId.toString());
//        }
//
//        if (cheatTime != null) {
//            CheatTime ct = (CheatTime) cheatTime;
//            if (ct.getCheatTimeExact() != null) {
//                Instant instant = ct.getCheatTimeExact().toInstant();
//                requestBuilder.setTe(Timestamp.newBuilder()
//                        .setSeconds(instant.getEpochSecond())
//                        .setNanos(instant.getNano())
//                        .build());
//            }
//            if (ct.getCheatTimeDelta() != null) {
//                requestBuilder.setTo(Duration.newBuilder()
//                        .setSeconds(ct.getCheatTimeDelta().getSeconds())
//                        .setNanos(ct.getCheatTimeDelta().getNano())
//                        .build());
//            }
//        }
//
//        GenerateManifestRequest request = requestBuilder.build();
//
//        // Execute gRPC call asynchronously
//        return avaGrpcClient.executeAsync(
//                manifestClient,
//                (client) -> client.generateManifest(request),
//                ProtoManifestHelper::toManifestV3
//        );
//    }
//
//    @Override
//    public CompletableFuture<ManifestV3> getManifestByGlobalHashAsync(
//            String dabInAppId,
//            String globalHash,
//            boolean isMinifyManifest
//    ) {
//        GetManifestByGlobalHashRequest request = GetManifestByGlobalHashRequest.newBuilder()
//                .setDabInAppId(dabInAppId)
//                .setGlobalHash(globalHash)
//                .setIsMinifyManifest(isMinifyManifest)
//                .build();
//
//        return avaGrpcClient.executeAsync(
//                manifestClient,
//                (client) -> client.getManifestByGlobalHash(request),
//                ProtoManifestHelper::toManifestV3
//        );
//    }
}