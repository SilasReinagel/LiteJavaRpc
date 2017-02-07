package rpc.info;

import rpc.RpcResponse;

public final class RpcApiVersionResponse extends RpcResponse
{
    public final String Version;

    public RpcApiVersionResponse(final String requestId, final String version)
    {
        super(requestId);
        Version = version;
    }
}
