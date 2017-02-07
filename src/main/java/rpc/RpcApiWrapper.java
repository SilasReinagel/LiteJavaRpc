package rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RpcApiWrapper implements IRpcApi
{
    private final IRpcApi _baseApi;
    private final IRpcCallHandler _addedCallHandler;

    public RpcApiWrapper(final IRpcApi api, final String relativeUri, final Function<String, IRpcCallHandler> callHandlerFactory)
    {
        _baseApi = api;
        _addedCallHandler = callHandlerFactory.apply(_baseApi.info().BaseUri + relativeUri);
    }

    @Override
    public RpcApiInfo info()
    {
        return _baseApi.info();
    }

    @Override
    public Map<String, IRpcCallHandler> getCallHandlers()
    {
        Map<String, IRpcCallHandler> callHandlers = new HashMap<>(_baseApi.getCallHandlers());
        callHandlers.put(_addedCallHandler.getUri(), _addedCallHandler);
        return Collections.unmodifiableMap(callHandlers);
    }
}
