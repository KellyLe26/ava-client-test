package ava.client.models.requests;

import ava.client.constants.ApiConstants;
import ava.client.models.AvaSettings;
import ava.client.utilities.cheating.CheatTime;
import ava.client.utilities.cheating.ICheatTimeAble;

import java.util.HashMap;
import java.util.Map;

import static ava.client.utilities.HttpRequestWithCheatAbleExtensions.buildRequestParams;


/**
 * Request class for getting manifest V3 hash.
 */
public class ManifestV3GetManifestHashRequest implements IAvaHttpRequest, ICheatTimeAble {

    private String dabInAppId;
    private String psaId;
    private Map<String, Object> parameterMappings;
    private CheatTime cheatTime;

    /**
     * Constructor for ManifestV3GetManifestHashRequest.
     *
     * @param dabInAppId        The DAB in-app ID
     * @param psaId             The PSA ID
     * @param parameterMappings The parameter mappings
     */
    public ManifestV3GetManifestHashRequest(String dabInAppId, String psaId, Map<String, Object> parameterMappings) {
        this.psaId = psaId;
        this.dabInAppId = dabInAppId;
        this.parameterMappings = parameterMappings;
    }

    public String getDabInAppId() {
        return dabInAppId;
    }

    public void setDabInAppId(String dabInAppId) {
        this.dabInAppId = dabInAppId;
    }

    public String getPsaId() {
        return psaId;
    }

    public void setPsaId(String psaId) {
        this.psaId = psaId;
    }

    public Map<String, Object> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(Map<String, Object> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    @Override
    public String getApiKeyHeader() {
        return AvaSettings.PRODUCT_API_KEY_HEADER;
    }

    @Override
    public Map<String, Object> getBody() {
        Map<String, Object> data = new HashMap<>();
        if (parameterMappings != null) {
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
        return String.format("%s/%s/%s/hash",
                ApiConstants.BASE_MANIFEST_V3_PATH,
                dabInAppId,
                psaId);
    }

    @Override
    public Map<String, String> getRequestParams() {
        return buildRequestParams(this);
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
}