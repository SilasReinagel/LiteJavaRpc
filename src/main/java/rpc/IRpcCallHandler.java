package rpc;

public interface IRpcCallHandler<T extends RpcRequest, R extends RpcResponse>
{
    String getUri();
    Class<T> getRequestType();
    R getResponse(final T request);
}
