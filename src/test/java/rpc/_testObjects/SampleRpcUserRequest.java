package rpc._testObjects;

import rpc.RpcRequest;

public class SampleRpcUserRequest extends RpcRequest
{
    public final String UserId;

    public SampleRpcUserRequest(final String userId)
    {
        super();
        UserId = userId;
    }
}
