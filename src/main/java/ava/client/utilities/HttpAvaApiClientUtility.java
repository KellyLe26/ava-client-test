package ava.client.utilities;

import ava.client.models.requests.ManifestV3PostByDabInAppIdHttpRequest;
import ava.client.utilities.IAvaApiClientUtility;
import ava.client.http.IAvaHttpClient;
import ava.client.models.ManifestV3;
import ava.client.utilities.cheating.CheatTime;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP implementation of the API client utility
 */
public class HttpAvaApiClientUtility implements IAvaApiClientUtility {

    private final IAvaHttpClient avaHttpClient;

    public HttpAvaApiClientUtility(IAvaHttpClient avaHttpClient) {
        this.avaHttpClient = avaHttpClient;
    }

    @Override
    public CompletableFuture<ManifestV3> generateManifestAsync(
            String dabInAppId,
            String psaId,
            Long optionalId,
            Map<String, Object> parameterMappings,
            CheatTime cheatTime,
            boolean isMinifyManifest
    ) {
        ManifestV3PostByDabInAppIdHttpRequest request =
                new ManifestV3PostByDabInAppIdHttpRequest(dabInAppId, psaId, optionalId, parameterMappings, isMinifyManifest);

        if (cheatTime != null) {
            request.applyCheatTime(cheatTime);
        }

        // Make async request through IAvaHttpClient
        return avaHttpClient.makeRequestAsync(request, ManifestV3.class);
    }

    @Override
    public CompletableFuture<ManifestV3> getManifestByGlobalHashAsync(
            String dabInAppId,
            String globalHash,
            boolean isMinifyManifest
    ) {
//        ManifestV3GetManifestByGlobalHashGetRequest request =
//                new ManifestV3GetManifestByGlobalHashGetRequest(dabInAppId, globalHash, isMinifyManifest);

        return avaHttpClient.makeRequestAsync(null, ManifestV3.class);
    }
}