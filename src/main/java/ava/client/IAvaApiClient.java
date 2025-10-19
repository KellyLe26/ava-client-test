package ava.client;

public interface IAvaApiClient {
    IAvaApiClientV3 getV3();

    /**
     * Java equivalent of:
     * public interface IAvaApiClientV3
     * {
     * IManifestV3ApiService Manifests { get; }
     * }
     */
    interface IAvaApiClientV3 {
        IManifestV3ApiService getManifests();
    }
}
