package rpc._testobjects;

import rpc.IRpcApi;
import rpc.IRpcClient;
import rpc.RpcRequest;
import rpc.RpcResponse;
import rpc.exceptions.RpcException;

public final class MockApiClient implements IRpcClient
{
    private final IRpcApi hostedApi;

    public MockApiClient(final IRpcApi hostedApi)
    {
        this.hostedApi = hostedApi;
    }

    @Override
    public void execute(final String uri, final RpcRequest request)
    {
        get(uri, request, RpcResponse.class);
    }

    @Override
    public <T extends RpcResponse> T get(final String uri, final RpcRequest request, final Class<T> responseType)
    {
        if (!hostedApi.getCallHandlers().containsKey(uri))
            throw new RpcException("Unknown Uri: '" + uri + "'");
        T response = (T)hostedApi.getCallHandlers().get(uri).getResponse(request);
        response.validateSucceeded();
        return response;
    }
}
