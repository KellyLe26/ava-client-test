package ava.client;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IManifestV3ApiService {
    /**
     * Retrieve the global hash for a user.
     *
     * @param dabInAppId The in-app ID of the target dab
     * @param psaId      The PSA ID as a string
     * @return CompletableFuture containing the global hash representing the
     *         manifest
     */
    CompletableFuture<Object> getGlobalHashAsync(String dabInAppId, String psaId);


}
