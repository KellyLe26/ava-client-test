package ava.client.models;
import java.util.List;

/**
 * Manifest Item V3 model class
 */
public class ManifestItemV3 {

    /**
     * Path to the config
     */
    private String p;

    /**
     * Segments applied
     */
    private List<String> s;

    /**
     * AB tests and group applied
     */
    private List<List<String>> a;

    /**
     * Security, true if public config, false if private config
     */
    private boolean c;

    public ManifestItemV3() {
    }

    // Constructor with all fields
    public ManifestItemV3(String p, List<String> s, List<List<String>> a, boolean c) {
        this.p = p;
        this.s = s;
        this.a = a;
        this.c = c;
    }

    // Getters and Setters
    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public List<String> getS() {
        return s;
    }

    public void setS(List<String> s) {
        this.s = s;
    }

    public List<List<String>> getA() {
        return a;
    }

    public void setA(List<List<String>> a) {
        this.a = a;
    }

    public boolean getC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }
}
