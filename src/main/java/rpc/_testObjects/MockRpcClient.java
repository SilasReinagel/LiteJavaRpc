package rpc._testObjects;

import rpc.IRpcClient;
import rpc.IRpcUri;
import rpc.RpcRequest;
import rpc.RpcResponse;
import rpc.exceptions.RpcException;
import rpc.exceptions.RpcTimeoutException;

import java.util.*;

public final class MockRpcClient implements IRpcClient
{
    private Map<String, Boolean> _rpcCallShouldThrow = new HashMap<>();
    private Map<String, RpcResponse> _responses = new HashMap<>();
    private Set<String> _urisCalled = new HashSet<>();

    private List<RpcRequest> _requestsSent = new ArrayList<>();
    private int _responseTime = 0;
    private boolean _requiresResponseObject = true;

    @Override
    public void execute(final String uri, final RpcRequest request)
    {
        get(uri, request, RpcResponse.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends RpcResponse> T get(final String uri, final RpcRequest request, final Class<T> responseType)
    {
        _urisCalled.add(uri);
        _requestsSent.add(request);
        delayForResponseTimeDuration();
        throwIfRequired(uri);
        if (_requiresResponseObject)
            if (!_responses.containsKey(uri))
                throw new RpcTimeoutException("Request timed out.");
        return (T) _responses.get(uri);
    }

    private void delayForResponseTimeDuration()
    {
        try
        {
            Thread.sleep(_responseTime);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void setShouldThrow(final String uri)
    {
        _rpcCallShouldThrow.put(uri, true);
    }

    public void setResponseObject(final IRpcUri uri, final RpcResponse response)
    {
        setResponseObject(uri.get(), response);
    }

    public void setResponseObject(final String uri, final RpcResponse response)
    {
        _responses.put(uri, response);
    }

    public void setResponseObjectRequired(final boolean isRequired)
    {
        _requiresResponseObject = isRequired;
    }

    public boolean wasCalled(final IRpcUri uri)
    {
        return wasCalled(uri.get());
    }

    public boolean wasCalled(final String uri)
    {
        return _urisCalled.contains(uri);
    }

    @SuppressWarnings("unchecked")
    public <T extends RpcRequest> T getLastRequest()
    {
        if (_requestsSent.isEmpty())
            throw new RpcException("No requests sent.");
        return (T)_requestsSent.get(_requestsSent.size() - 1);
    }

    private void throwIfRequired(final String url)
    {
        if (_rpcCallShouldThrow.containsKey(url) && _rpcCallShouldThrow.get(url))
            throw new RpcException("Sample Exception Occurred");
    }

    public void setResponseTime(final int millis)
    {
        _responseTime = millis;
    }
}
