package rpc._testObjects;

import rpc.*;

import java.util.HashMap;
import java.util.Map;

public final class MockRpcApi implements IRpcApi
{
    public static final String Version = "0.0.0";
    public static final String BaseUri = "/api/mock/subsection";
    public static final String PingUri = BaseUri + "/ping";
    public static final String VersionUri = BaseUri + "/version";
    public static final String HelpUri = BaseUri + "/help";

    private final Map<String, IRpcCallHandler> _callHandlers = new HashMap<>();

    @Override
    public RpcApiInfo info()
    {
        return new RpcApiInfo("Mock Microservice", Version, BaseUri);
    }

    @Override
    public Map<String, IRpcCallHandler> getCallHandlers()
    {
        return _callHandlers;
    }

    public void addCallHandler(final String callUri)
    {
        addCallHandler(new RpcCallHandler<>(BaseUri + callUri, RpcRequest.class, x -> new RpcResponse(x.RequestId)));
    }

    public void addCallHandler(final IRpcCallHandler callHandler)
    {
        _callHandlers.put(callHandler.getUri(), callHandler);
    }
}
