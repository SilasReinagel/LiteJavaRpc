package rpc.validation;

import org.junit.Before;
import org.junit.Test;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;
import rpc.exceptions.RpcInvalidResponseException;
import testing.ExceptionAssert;

public class RpcResponseValidatorTests
{
    private RpcResponseValidator _validator;

    @Before
    public void init()
    {
        _validator = new RpcResponseValidator();
    }

    @Test
    public void RpcResponseValidator_ResponseWithNull_ThrowsInvalidResponseException()
    {
        ExceptionAssert.assertThrows(RpcInvalidResponseException.class, () -> _validator.validate(new RpcResponse(null)));
    }

    @Test
    public void RpcResponseValidator_ResponseWithNoNulls_DoesNotThrow()
    {
        _validator.validate(new RpcResponse("SampleId", RpcRequestStatus.Error, "Sample Error Message"));
    }
}
