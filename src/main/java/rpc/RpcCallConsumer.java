package rpc;

import java.util.function.Consumer;

public class RpcCallConsumer<T extends RpcRequest> extends RpcCallHandler<T, RpcResponse>
{
    public RpcCallConsumer(final IRpcUri uri, final Class<T> requestType, final Consumer<T> onReceiptAction)
    {
        this(uri.get(), requestType, onReceiptAction);
    }

    public RpcCallConsumer(final String uri, final Class<T> requestType, final Consumer<T> onReceiptAction)
    {
        super(uri, requestType, x -> { onReceiptAction.accept(x); return new RpcResponse(x.RequestId); });
    }
}
