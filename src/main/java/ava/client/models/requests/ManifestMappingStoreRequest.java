package ava.client.models.requests;

import ava.client.constants.ApiConstants;
import ava.client.models.AvaSettings;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Request class for storing manifest mapping.
 * This is PSAID, not userid in db.
 */
public class ManifestMappingStoreRequest implements IAvaHttpRequest {

    private String userId;
    private String hash;
    private Long optionalId;
    private String dabInAppId;

    /**
     * Constructor for ManifestMappingStoreRequest.
     * This is PSAID, not userid in db.
     *
     * @param dabInAppId The DAB in-app ID
     * @param userId     The user ID (PSAID)
     * @param hash       The hash to store
     * @param optionalId The optional ID
     */
    public ManifestMappingStoreRequest(String dabInAppId, String userId, String hash, Long optionalId) {
        this.userId = userId;
        this.hash = hash;
        this.optionalId = optionalId;
        this.dabInAppId = dabInAppId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
    public Map<String, Object> getBody() {
        Map<String, Object> data = new HashMap<>();

        // This is PSAID, not userid in db
        data.put("u", userId);
        data.put("h", hash);
        data.put("d", dabInAppId);

        if (optionalId != null) {
            data.put("o", optionalId);
        }

        return data;
    }

    @Override
    public HttpMethod getHttpMethod () {
        return HttpMethod.POST;
    }

    @Override
    public String getPath() {
        return ApiConstants.BASE_MANIFEST_MAPPING_PATH;
    }

    @Override
    public Map<String, String> getRequestParams() {
        return new ConcurrentHashMap<>();
    }
}