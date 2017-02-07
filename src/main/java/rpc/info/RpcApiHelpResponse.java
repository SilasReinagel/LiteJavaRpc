package rpc.info;

import rpc.RpcResponse;

import java.util.List;

public class RpcApiHelpResponse extends RpcResponse
{
    public final List<String> ApiCalls;

    public RpcApiHelpResponse(final String requestId, final List<String> apiCalls)
    {
        super(requestId);
        ApiCalls = apiCalls;
    }
}
