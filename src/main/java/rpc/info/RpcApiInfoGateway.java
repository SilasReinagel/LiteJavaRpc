package rpc.info;

import rpc.IRpcClient;
import rpc.RpcRequest;

import java.util.List;

public final class RpcApiInfoGateway implements IRpcApiInfoGateway
{
    private final IRpcClient _rpcClient;

    public RpcApiInfoGateway(final IRpcClient rpcClient)
    {
        _rpcClient = rpcClient;
    }

    @Override
    public String getVersion(final String apiBaseUri)
    {
        return _rpcClient.get(apiBaseUri + "/version", new RpcRequest(), RpcApiVersionResponse.class).Version;
    }

    @Override
    public List<String> getApiCallInfo(String apiBaseUri)
    {
        return _rpcClient.get(apiBaseUri + "/help", new RpcRequest(), RpcApiHelpResponse.class).ApiCalls;
    }
}
