package ava.client.models.requests;


import ava.client.constants.ApiConstants;
import ava.client.models.AvaSettings;
import ava.client.utilities.cheating.CheatTime;
import ava.client.utilities.cheating.ICheatTimeAble;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ava.client.utilities.HttpRequestWithCheatAbleExtensions.buildRequestParams;

/**
 * HTTP request to generate a ManifestV3 by DabInAppId
 */
public class ManifestV3PostByDabInAppIdHttpRequest implements IAvaHttpRequest, ICheatTimeAble {

    private final String dabInAppId;
    private final String psaId;
    private final Long optionalId; // nullable
    private final Map<String, Object> parameterMappings;
    private final boolean isMinifyManifest;

    private CheatTime cheatTime; // optional

    public ManifestV3PostByDabInAppIdHttpRequest(String dabInAppId,
                                                 String psaId,
                                                 Long optionalId,
                                                 Map<String, Object> parameterMappings,
                                                 boolean isMinifyManifest) {
        this.dabInAppId = dabInAppId;
        this.psaId = psaId;
        this.optionalId = optionalId;
        this.parameterMappings = parameterMappings != null ? parameterMappings : Collections.emptyMap();
        this.isMinifyManifest = isMinifyManifest;
    }

    @Override
    public String getApiKeyHeader() {
        return AvaSettings.PRODUCT_API_KEY_HEADER;
    }

    public Map<String, Object> getBody() {
        Map<String, Object> data = new HashMap<>();
        if (!parameterMappings.isEmpty()) {
            data.put("p", parameterMappings);
        }
        return data;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
    @Override
    public String getPath() {
        return String.format("%s/%s/%s/%s",
                ApiConstants.BASE_MANIFEST_V3_PATH,
                dabInAppId,
                psaId,
                optionalId != null ? optionalId : "");
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new ConcurrentHashMap<>(buildRequestParams(this));
        params.put("isMinifyManifest", Boolean.toString(isMinifyManifest));
        return params;
    }


    @Override
    public CheatTime getCheatTime() {
        return cheatTime;
    }

    @Override
    public void setCheatTime(CheatTime cheatTime) {
        this.cheatTime = cheatTime;
    }

    @Override
    public void applyCheatTime(CheatTime cheatTime) {
        this.cheatTime = cheatTime;
    }

    // Getters
    public String getDabInAppId() {
        return dabInAppId;
    }

    public String getPsaId() {
        return psaId;
    }

    public Long getOptionalId() {
        return optionalId;
    }

    public Map<String, Object> getParameterMappings() {
        return parameterMappings;
    }

    public boolean isMinifyManifest() {
        return isMinifyManifest;
    }
}