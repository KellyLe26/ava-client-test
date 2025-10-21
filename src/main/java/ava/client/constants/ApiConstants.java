package ava.client.constants;

public class ApiConstants {
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
