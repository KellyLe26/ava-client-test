package ava.client;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
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

 class ApiConstants {

    public static final String BASE_MANIFEST_MAPPING_PATH = "api/v3/mapping";
    public static final String BASE_CONFIG_V3_PATH = "api/v3/configs";
    public static final String BASE_MANIFEST_V3_PATH = "api/v3/manifest";
    public static final String BASE_SCHEDULE_V3_PATH = "api/v3/schedules";
    public static final String BASE_STATUS_PATH = "api/status";

    // Private constructor to prevent instantiation
    private ApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
