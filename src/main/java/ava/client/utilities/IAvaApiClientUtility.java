package ava.client.utilities;

import ava.client.models.ManifestV3;
import ava.client.utilities.cheating.CheatTime;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Abstraction for API client utilities to support both HTTP and gRPC implementations
 */
public interface IAvaApiClientUtility {

    /**
     * Generates a manifest using the configured transport (HTTP or gRPC)
     *
     * @param dabInAppId        the DAB in-app ID
     * @param psaId             the PSA ID
     * @param optionalId        optional long ID, can be null
     * @param parameterMappings map of parameters
     * @param cheatTime         optional cheat time, can be null
     * @param isMinifyManifest  whether to minify the manifest
     * @return a CompletableFuture of ManifestV3
     */
    CompletableFuture<ManifestV3> generateManifestAsync(
            String dabInAppId,
            String psaId,
            Long optionalId,
            Map<String, Object> parameterMappings,
            CheatTime cheatTime,
            boolean isMinifyManifest
    );

    /**
     * Retrieves a manifest by global hash
     *
     * @param dabInAppId       the DAB in-app ID
     * @param globalHash       the global hash
     * @param isMinifyManifest whether to minify the manifest
     * @return a CompletableFuture of ManifestV3
     */
    CompletableFuture<ManifestV3> getManifestByGlobalHashAsync(
            String dabInAppId,
            String globalHash,
            boolean isMinifyManifest
    );
}