package ava.client.grpc;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Abstraction for gRPC client operations
 */
public interface IAvaGrpcClient {

    /**
     * Creates a gRPC client stub of the given type.
     *
     * @param stubClass gRPC stub class
     * @param <TClient> stub type
     * @return instance of the stub
     */
//    <TClient extends AbstractStub<TClient>> TClient createClient(Class<TClient> stubClass);

    /**
     * Executes an asynchronous gRPC call.
     *
     * @param client gRPC client stub
     * @param call   function that performs the gRPC call with Metadata headers
     * @param <TClient> stub type
     * @param <TResponse> response type
     * @return CompletableFuture of the response
     */
//    <TClient extends AbstractStub<TClient>, TResponse>
//    CompletableFuture<TResponse> executeAsync(
//            TClient client,
//            TriFunction<TClient, Metadata, AtomicBoolean, CompletableFuture<TResponse>> call
//    );

    /**
     * Functional interface to mimic a gRPC call with client, metadata, and cancellation flag.
     */
    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
