package ava.client.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manifest V3 model class
 */
public class ManifestV3 {

    /**
     * Manifest contents
     */
    private Map<String, ManifestItemV3> cs;

    /**
     * Matched segment inAppIds
     */
    private List<String> ms;

    /**
     * Hash of this manifest.
     *
     * WARNING: INTERNAL USE ONLY
     * Do NOT use this value to determine your own local cache.
     * This hash is specifically for internal processing and may change without
     * notice,
     * leading to inconsistent results if used externally.
     */
    private String h;

    /**
     * Global hash of this manifest.
     */
    private String gh;

    private String t;

    // Default constructor
    public ManifestV3() {
        this.ms = new ArrayList<>();
        this.cs = new HashMap<>();
        this.t = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // Constructor with all fields
    public ManifestV3(Map<String, ManifestItemV3> cs, List<String> ms, String h, String gh, String t) {
        this.cs = cs != null ? cs : new HashMap<>();
        this.ms = ms != null ? ms : new ArrayList<>();
        this.h = h;
        this.gh = gh;
        this.t = t != null ? t : LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public void add(String inAppId, ManifestItemV3 entry) {
        cs.put(inAppId, entry);
    }

    // Getters and Setters
    public Map<String, ManifestItemV3> getCs() {
        return cs;
    }

    public void setCs(Map<String, ManifestItemV3> cs) {
        this.cs = cs;
    }

    public List<String> getMs() {
        return ms;
    }

    public void setMs(List<String> ms) {
        this.ms = ms;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
