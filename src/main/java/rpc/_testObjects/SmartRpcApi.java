package rpc._testObjects;

import rpc.*;
import rpc.exceptions.RpcException;

import java.util.Map;

public final class SmartRpcApi implements IRpcApi
{
    private final IRpcApi _rpcApi;

    public SmartRpcApi(final IRpcApi rpcApi) { _rpcApi = rpcApi; }

    @Override
    public RpcApiInfo info()
    {
        return _rpcApi.info();
    }

    @Override
    public Map<String, IRpcCallHandler> getCallHandlers()
    {
        return _rpcApi.getCallHandlers();
    }

    @SuppressWarnings("unchecked")
    public <T extends RpcResponse> T getResponse(final String uri, final RpcRequest request)
    {
        if (!_rpcApi.getCallHandlers().containsKey(uri))
            throw new RpcException("Server: Unknown request uri: '" + uri + "'");
        return (T)getCallHandlers().get(uri).getResponse(request);
    }

    public <T extends RpcResponse> T getResponse(final IRpcUri uri, final RpcRequest request)
    {
        return getResponse(uri.get(), request);
    }

    public void execute(final String uri, final RpcRequest request)
    {
        RpcResponse response = getResponse(uri, request);
        response.validateSucceeded();
    }

    public void execute(final IRpcUri uri, final RpcRequest request)
    {
        execute(uri.get(), request);
    }
}
