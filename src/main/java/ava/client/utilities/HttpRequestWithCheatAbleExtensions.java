package ava.client.utilities
;

import ava.client.utilities.cheating.CheatTime;
import ava.client.utilities.cheating.ICheatTimeAble;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides utility methods for objects that implement the ICheatTimeAble
 * interface.
 * This is the Java equivalent of C# extension methods.
 */
public final class HttpRequestWithCheatAbleExtensions {

    // Private constructor to prevent instantiation
    private HttpRequestWithCheatAbleExtensions() {}

    /**
     * Builds a map of request parameters based on the cheat time.
     *
     * @param cheatTimeAble the object implementing ICheatTimeAble
     * @return a map containing cheat time request parameters
     */
    public static Map<String, String> buildRequestParams(ICheatTimeAble cheatTimeAble) {
        Map<String, String> requestParams = new HashMap<>();

        if (cheatTimeAble.getCheatTime() == null) {
            return new ConcurrentHashMap<>();
        }

        CheatTime cheatTime = cheatTimeAble.getCheatTime();

        if (cheatTime.getCheatTimeDelta() != null) {
            String timeSpanString = cheatTime.getCheatTimeDelta().toString();
            requestParams.put("to", timeSpanString);
        }

        if (cheatTime.getCheatTimeExact() != null) {
            // Using ISO format for invariant culture equivalent
            String timeString = cheatTime.getCheatTimeExact().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            requestParams.put("te", timeString);
        }

        return requestParams;
    }
}