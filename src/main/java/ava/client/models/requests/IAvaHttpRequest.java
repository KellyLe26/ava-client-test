package ava.client.models.requests;


import java.util.Map;

public interface IAvaHttpRequest {

    /**
     * HTTP methods supported by the API.
     */
    enum HttpMethod {
        GET, POST, PUT, DELETE, PATCH
    }

    /**
     * Get the HTTP method.
     *
     * @return The HTTP method
     */
    HttpMethod getHttpMethod();
    String getPath();
    Map<String, String> getRequestParams();
    Map<String, Object> getBody();
    String getApiKeyHeader();
}
