package ava.client;

public class AvaClientSettings {

    private String apiKey;
    private String endPoint;
    private String grpcEndPoint;

    // Constructor
    public AvaClientSettings() {
    }

    // ApiKey getter/setter
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    // EndPoint getter/setter with '/' logic
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        if (endPoint != null && !endPoint.isEmpty()) {
            this.endPoint = endPoint.endsWith("/") ? endPoint : endPoint + "/";
        } else {
            this.endPoint = endPoint;
        }
    }

    // GrpcEndPoint getter/setter
    public String getGrpcEndPoint() {
        return grpcEndPoint;
    }

    public void setGrpcEndPoint(String grpcEndPoint) {
        this.grpcEndPoint = grpcEndPoint;
    }


}
