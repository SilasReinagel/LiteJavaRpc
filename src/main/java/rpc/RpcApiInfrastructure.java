package rpc;

import rpc.health.RpcApiPingWrapper;
import rpc.info.RpcApiHelpWrapper;
import rpc.info.RpcApiVersionWrapper;

public final class RpcApiInfrastructure
{
    public static IRpcApi addAllInfrastructureCalls(final IRpcApi rpcApi)
    {
        return new RpcApiHelpWrapper(
                new RpcApiPingWrapper(
                new RpcApiVersionWrapper(rpcApi)));
    }
}
