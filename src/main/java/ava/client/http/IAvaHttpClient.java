package ava.client.http;

import ava.client.models.requests.IAvaHttpRequest;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for HTTP client operations for the Ava API.
 */
public interface IAvaHttpClient {

    /**
     * Make an asynchronous HTTP request.
     *
     * @param request      The HTTP request to make
     * @param responseType The expected response type class
     * @param <T>          The response type
     * @return CompletableFuture containing the response
     */
    <T> CompletableFuture<T> makeRequestAsync(IAvaHttpRequest request, Class<T> responseType);

    /**
     * Build a qualified URI from the request parameters.
     *
     * @param request The HTTP request containing path and parameters
     * @return The fully qualified URI
     */
    URI buildQualifiedUri(IAvaHttpRequest request);
}
