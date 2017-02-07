package rpc.exceptions;

public class RpcException extends RuntimeException
{
    public RpcException()
    {
    }

    public RpcException(final String message)
    {
        super(message);
    }

    public RpcException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public RpcException(final Throwable cause)
    {
        super(cause);
    }
}
