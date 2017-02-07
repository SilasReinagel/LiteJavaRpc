package rpc.gateway;

import rpc.IRpcClient;
import rpc.RpcRequest;
import rpc.RpcResponse;
import rpc.health.IRpcApiHealthGateway;
import rpc.health.RpcApiHealthGateway;
import rpc.info.IRpcApiInfoGateway;
import rpc.info.RpcApiInfoGateway;

import java.util.List;

public final class RpcCompositeGateway implements IRpcGateway
{
    private final IRpcClient _client;
    private final IRpcApiHealthGateway _healthGateway;
    private final IRpcApiInfoGateway _infoGateway;

    public RpcCompositeGateway(final IRpcClient client)
    {
        _client = client;
        _healthGateway = new RpcApiHealthGateway(client);
        _infoGateway = new RpcApiInfoGateway(client);
    }

    @Override
    public boolean isAvailable(final String apiBaseUri)
    {
        return _healthGateway.isAvailable(apiBaseUri);
    }

    @Override
    public int getResponseTimeMs(final String apiBaseUri)
    {
        return _healthGateway.getResponseTimeMs(apiBaseUri);
    }

    @Override
    public String getVersion(final String apiBaseUri)
    {
        return _infoGateway.getVersion(apiBaseUri);
    }

    @Override
    public List<String> getApiCallInfo(final String apiBaseUri)
    {
        return _infoGateway.getApiCallInfo(apiBaseUri);
    }

    @Override
    public void execute(final String uri, final RpcRequest request)
    {
        _client.execute(uri, request);
    }

    @Override
    public <T extends RpcResponse> T get(final String uri, final RpcRequest request, final Class<T> responseType)
    {
        return _client.get(uri, request, responseType);
    }
}
