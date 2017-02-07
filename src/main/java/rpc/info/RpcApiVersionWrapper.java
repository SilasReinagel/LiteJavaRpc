package rpc.info;

import rpc.IRpcApi;
import rpc.RpcApiWrapper;
import rpc.RpcCallHandler;
import rpc.RpcRequest;

public final class RpcApiVersionWrapper extends RpcApiWrapper
{
    public RpcApiVersionWrapper(final IRpcApi api)
    {
        super(api, "/version", x -> new RpcCallHandler<>(x, RpcRequest.class, y -> new RpcApiVersionResponse(y.RequestId, api.info().Version)));
    }
}
