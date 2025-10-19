package ava.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AvaHttpClient implements IAvaHttpClient, AutoCloseable {

    private final HttpClient httpClient;
    private final String apiKey;
    private final String endPoint;
    private final ObjectMapper objectMapper;
    private volatile boolean disposed = false;

    /**
     * Start new AVA client
     *
     * @param httpClient The HTTP client to use for requests
     * @param settings   The client settings containing API key and endpoint
     */
    public AvaHttpClient(HttpClient httpClient, AvaClientSettings settings) {
        this.apiKey = settings.getApiKey();
        this.endPoint = settings.getEndPoint();
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Convenience constructor that creates a default HttpClient
     *
     * @param settings The client settings containing API key and endpoint
     */
    public AvaHttpClient(AvaClientSettings settings) {
        this(HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build(), settings);
    }

    @Override
    public <T> CompletableFuture<T> makeRequestAsync(IAvaHttpRequest request, Class<T> responseType) {
        if (disposed) {
            return CompletableFuture.failedFuture(new IllegalStateException("HttpClient has been disposed"));
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                URI uri = buildQualifiedUri(request);
                HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                        .uri(uri)
                        .header(request.getApiKeyHeader(), apiKey)
                        .timeout(Duration.ofSeconds(30));

                // Set HTTP method and body
                if (request.getHttpMethod() == IAvaHttpRequest.HttpMethod.GET) {
                    requestBuilder.GET();
                } else if (request.getHttpMethod() == IAvaHttpRequest.HttpMethod.POST) {
                    if (request.getBody() != null && !request.getBody().isEmpty()) {
                        String jsonBody = objectMapper.writeValueAsString(request.getBody());
                        requestBuilder.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                                .header("Content-Type", "application/json");
                    } else {
                        requestBuilder.POST(HttpRequest.BodyPublishers.noBody());
                    }
                }

                HttpRequest httpRequest = requestBuilder.build();
                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                // Check for successful status code
                if (response.statusCode() < 200 || response.statusCode() >= 300) {
                    throw new RuntimeException("HTTP request failed with status: " + response.statusCode() +
                            ", body: " + response.body());
                }

                String responseBody = response.body();
                if (responseBody == null || responseBody.trim().isEmpty()) {
                    return null;
                }

                return objectMapper.readValue(responseBody, responseType);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to execute HTTP request", e);
            }
        });
    }

    @Override
    public URI buildQualifiedUri(IAvaHttpRequest request) {
        try {
            String path = request.getPath();
            URI baseUri = new URI(endPoint);

            // Combine base URI with path
            String fullPath = baseUri.getPath();
            if (fullPath == null) {
                fullPath = "";
            }
            if (!fullPath.endsWith("/") && !path.startsWith("/")) {
                fullPath += "/";
            }
            fullPath += path;

            // Build query string if request parameters exist
            String query = null;
            if (request.getRequestParams() != null && !request.getRequestParams().isEmpty()) {
                query = request.getRequestParams().entrySet().stream()
                        .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                                URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                        .collect(Collectors.joining("&"));
            }

            return new URI(baseUri.getScheme(), baseUri.getAuthority(), fullPath, query, null);

        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to build qualified URI", e);
        }
    }

    @Override
    public void close() throws Exception {
        dispose();
    }

    /**
     * Dispose of resources used by this client.
     */
    public void dispose() {
        if (disposed) {
            return;
        }

        disposed = true;
        // HttpClient doesn't need explicit disposal in Java 11+
        // The underlying connection pool will be cleaned up by the JVM
    }

    /**
     * Check if this client has been disposed.
     *
     * @return true if disposed, false otherwise
     */
    public boolean isDisposed() {
        return disposed;
    }
}
