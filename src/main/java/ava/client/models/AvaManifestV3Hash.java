package ava.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvaManifestV3Hash {
    @JsonProperty("Hash")
    private String hash;

    // Default constructor
    public AvaManifestV3Hash() {
    }

    // Constructor with parameter
    public AvaManifestV3Hash(String hash) {
        this.hash = hash;
    }

    // Getter
    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "AvaManifestV3Hash{" +
                "hash='" + hash + '\'' +
                '}';
    }
    // Setter
    public void setHash(String hash) {
        this.hash = hash;
    }
}
