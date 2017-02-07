package rpc.health;

import rpc.IRpcApi;
import rpc.RpcApiWrapper;
import rpc.RpcCallSupplier;
import rpc.RpcResponse;

public final class RpcApiPingWrapper extends RpcApiWrapper
{
    public RpcApiPingWrapper(final IRpcApi api)
    {
        super(api, "/ping", uri -> new RpcCallSupplier<>(uri, x -> new RpcResponse(x.RequestId)));
    }
}
