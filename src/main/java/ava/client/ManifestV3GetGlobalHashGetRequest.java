package ava.client;

import java.util.Collections;
import java.util.Map;

public class ManifestV3GetGlobalHashGetRequest implements  IAvaHttpRequest {

    private final String userId;
    private final String dabInAppId;

    public ManifestV3GetGlobalHashGetRequest(String dabInAppId, String userId) {
        this.dabInAppId = dabInAppId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDabInAppId() {
        return dabInAppId;
    }

    @Override
    public String getApiKeyHeader() {
        return AvaSettings.PRODUCT_API_KEY_HEADER;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getPath() {
        return String.format("%s/%s/%s/globalhash",
                ApiConstants.BASE_MANIFEST_V3_PATH, dabInAppId, userId);
    }

    @Override
    public Map<String, String> getRequestParams() {
        return Collections.emptyMap(); // immutable empty map
    }

    @Override
    public Map<String, Object> getBody() {
        return Collections.emptyMap(); // immutable empty map
    }
}
