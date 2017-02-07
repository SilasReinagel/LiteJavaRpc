package rpc;

import java.util.function.Function;

public class RpcCallHandler<T extends RpcRequest, R extends RpcResponse> implements IRpcCallHandler<T, R>
{
    private final String _uri;
    private final Class<T> _requestType;
    private final Function<T, R> _func;

    public RpcCallHandler(final IRpcUri uri, final Class<T> requestType, final Function<T, R> function)
    {
        this(uri.get(), requestType, function);
    }

    public RpcCallHandler(final String uri, final Class<T> requestType, final Function<T, R> function)
    {
        _uri = uri;
        _requestType = requestType;
        _func = function;
    }

    @Override
    public String getUri()
    {
        return _uri;
    }

    @Override
    public Class<T> getRequestType()
    {
        return _requestType;
    }

    @Override
    public R getResponse(final T request)
    {
        return _func.apply(request);
    }

    @Override
    public String toString()
    {
        return "RpcCallHandler: " + getUri();
    }
}
