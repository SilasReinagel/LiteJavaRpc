package rpc;

import java.util.UUID;

public class RpcRequest
{
    public final String RequestId;

    public RpcRequest()
    {
        RequestId = UUID.randomUUID().toString();
    }

    public RpcRequest(final String requestId)
    {
        RequestId = requestId;
    }
}
