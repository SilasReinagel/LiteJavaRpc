package rpc.exceptions;

public final class RpcInvalidResponseException extends RuntimeException
{
    public RpcInvalidResponseException(final String message)
    {
        super(message);
    }
}
