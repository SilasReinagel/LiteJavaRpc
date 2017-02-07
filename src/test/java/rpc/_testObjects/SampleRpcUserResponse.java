package rpc._testObjects;

import rpc.RpcResponse;

public class SampleRpcUserResponse extends RpcResponse
{
    public final String Username;
    public final String Password;

    public SampleRpcUserResponse(final String requestId, final String username, final String password)
    {
        super(requestId);
        Username = username;
        Password = password;
    }
}
