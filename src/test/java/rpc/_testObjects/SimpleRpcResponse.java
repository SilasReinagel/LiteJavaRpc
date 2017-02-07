package rpc._testObjects;

import rpc.RpcResponse;

public class SimpleRpcResponse extends RpcResponse
{
    public final String Value;

    public SimpleRpcResponse(final String requestId, final String value)
    {
        super(requestId);
        Value = value;
    }
}
