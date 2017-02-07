package rpc;

public interface IRpcClient
{
    void execute(String uri, RpcRequest request);
    <T extends RpcResponse> T get(String uri, RpcRequest request, Class<T> responseType);

    default void execute(IRpcUri uri, RpcRequest request)
    {
        execute(uri.get(), request);
    }

    default <T extends RpcResponse> T get(IRpcUri uri, RpcRequest request, Class<T> responseType)
    {
        return get(uri.get(), request, responseType);
    }
}
