package rpc;

import rpc.exceptions.RpcException;

public class RpcResponse
{
    public final String RequestId;
    public final RpcRequestStatus Status;
    public final String ErrorMessage;

    public RpcResponse(final String requestId)
    {
        RequestId = requestId;
        Status = RpcRequestStatus.Ok;
        ErrorMessage = "";
    }

    public RpcResponse(final String requestId, final RpcRequestStatus requestStatus, final String errorMessage)
    {
        RequestId = requestId;
        Status = requestStatus;
        ErrorMessage = errorMessage;
    }

    public boolean matchesRequest(final RpcRequest request)
    {
        return RequestId != null && RequestId.equals(request.RequestId);
    }

    public void validateSucceeded()
    {
        if (Status.equals(RpcRequestStatus.Error))
            throw new RpcException(ErrorMessage);
    }
}
