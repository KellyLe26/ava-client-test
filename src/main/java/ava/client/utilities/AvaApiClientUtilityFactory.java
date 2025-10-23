package ava.client.utilities;

import ava.client.http.IAvaHttpClient;
import ava.client.grpc.IAvaGrpcClient;

public final class AvaApiClientUtilityFactory {

    // Private constructor to prevent instantiation
    private AvaApiClientUtilityFactory() { }

    /**
     * Creates the appropriate API client utility based on the useGrpc flag
     *
     * @param httpClient HTTP client (used when useGrpc is false)
     * @param grpcClient gRPC client (used when useGrpc is true)
     * @param useGrpc    whether to use gRPC client
     * @return the appropriate IAvaApiClientUtility implementation
     */
    public static IAvaApiClientUtility create(IAvaHttpClient httpClient,
                                              IAvaGrpcClient grpcClient,
                                              boolean useGrpc) {
        if (useGrpc) {
            return new GrpcAvaApiClientUtility(grpcClient);
        } else {
            return new HttpAvaApiClientUtility(httpClient);
        }
    }

    // Overload without useGrpc parameter (defaults to false)
    public static IAvaApiClientUtility create(IAvaHttpClient httpClient,
                                              IAvaGrpcClient grpcClient) {
        return create(httpClient, grpcClient, false);
    }
}