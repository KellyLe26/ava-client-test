package ava.client.models.requests;

import ava.client.constants.ApiConstants;
import ava.client.models.AvaSettings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ManifestMappingGetRequest  implements IAvaHttpRequest {
    private String userId;
    private Long optionalId;
    private String dabInAppId;

    /**
     * Constructor for ManifestMappingGetRequest.
     * This is PSAID, not userid in db.
     *
     * @param dabInAppId The DAB in-app ID
     * @param userId     The user ID (PSAID)
     * @param optionalId The optional ID
     */
    public ManifestMappingGetRequest(String dabInAppId, String userId, Long optionalId) {
        this.dabInAppId = dabInAppId;
        this.userId = userId;
        this.optionalId = optionalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(Long optionalId) {
        this.optionalId = optionalId;
    }

    public String getDabInAppId() {
        return dabInAppId;
    }

    public void setDabInAppId(String dabInAppId) {
        this.dabInAppId = dabInAppId;
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
        // This is PSAID, not userid in db
        return String.format("%s/%s/%s/%s",
                ApiConstants.BASE_MANIFEST_MAPPING_PATH,
                dabInAppId,
                userId,
                optionalId);
    }

    @Override
    public Map<String, String> getRequestParams() {
        return new ConcurrentHashMap<>();
    }

    @Override
    public Map<String, Object> getBody() {
        return new ConcurrentHashMap<>();
    }
}
