package rpc.exceptions;

public final class RpcTimeoutException extends RpcException
{
    public RpcTimeoutException(final String message)
    {
        super(message);
    }
}
