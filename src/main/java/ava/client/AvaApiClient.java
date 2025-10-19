package ava.client;
import java.net.http.HttpClient;


// Main client
public class AvaApiClient implements IAvaApiClient, AutoCloseable {
    private final AvaHttpClient httpClient;
    private boolean disposed = false;
    private final AvaApiClientV3 v3;

    // Default constructor
    public AvaApiClient() {
        this(null, new AvaClientSettings());
    }

    // Constructor with HTTP client + settings
    public AvaApiClient(HttpClient externalHttpClient, AvaClientSettings settings) {
        this.httpClient = new AvaHttpClient(externalHttpClient, settings);

        // Initialize V3 client
        this.v3 = new AvaApiClientV3(
                new ManifestV3ApiService(httpClient, settings)
        );
    }

    @Override
    public IAvaApiClientV3 getV3() {
        return v3;
    }

    // AutoCloseable
    @Override
    public void close() {
        dispose(true);
    }

    protected void dispose(boolean disposing) {
        if (disposed) return;

        if (disposing) {
            try {
                httpClient.close();
            } catch (Exception ignored) {
            }
        }
        disposed = true;
    }

    // Inner class for V3 client
    public static class AvaApiClientV3 implements IAvaApiClientV3 {
        private final IManifestV3ApiService manifests;

        public AvaApiClientV3(IManifestV3ApiService manifests) {
            this.manifests = manifests;
        }

        @Override
        public IManifestV3ApiService getManifests() {
            return manifests;
        }
    }
}



//public class AvaApiClient implements IAvaApiClient, AutoCloseable {
//
//
//    // -----------------------------
//    // Inner class for V3
//    // -----------------------------

//    }
//}





