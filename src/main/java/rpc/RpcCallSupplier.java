package rpc;

import java.util.function.Function;

public class RpcCallSupplier<R extends RpcResponse> extends RpcCallHandler<RpcRequest, R>
{
    public RpcCallSupplier(final IRpcUri uri, final Function<RpcRequest, R> function)
    {
        this(uri.get(), function);
    }

    public RpcCallSupplier(final String uri, final Function<RpcRequest, R> function)
    {
        super(uri, RpcRequest.class, function);
    }
}
