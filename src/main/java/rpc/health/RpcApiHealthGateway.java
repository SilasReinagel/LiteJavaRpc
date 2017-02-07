package rpc.health;

import rpc.IRpcClient;
import rpc.RpcRequest;

public final class RpcApiHealthGateway implements IRpcApiHealthGateway
{
    private final IRpcClient _rpcClient;

    public RpcApiHealthGateway(final IRpcClient rpcClient)
    {
        _rpcClient = rpcClient;
    }

    @Override
    public boolean isAvailable(final String apiBaseUri)
    {
        try
        {
            _rpcClient.execute(apiBaseUri + "/ping", new RpcRequest());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public int getResponseTimeMs(final String apiBaseUri)
    {
        long startTime = System.currentTimeMillis();
        _rpcClient.execute(apiBaseUri + "/ping", new RpcRequest());
        long stopTime = System.currentTimeMillis();
        return (int)(stopTime - startTime);
    }
}
