package rpc.validation;

import org.junit.*;
import rpc.RpcRequest;
import rpc.exceptions.RpcInvalidRequestException;
import testing.ExceptionAssert;

@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
public class RpcRequestValidatorTest
{
    private RpcRequestValidator _validator;

    @Before
    public void init()
    {
        _validator = new RpcRequestValidator();
    }

    @Test
    public void RpcRequestValidator_BasicRequestInvalid_ThrowsException()
    {
        assertThrowsMissingFieldException("RequestId", () -> _validator.validate(new RpcRequest(null)));
        assertThrowsMissingFieldException("RequestId", () -> _validator.validate(new RpcRequest("")));
    }

    @Test
    public void RpcRequestValidator_BasicRequestValid_NoExceptionThrown()
    {
        _validator.validate(new RpcRequest());
    }

    @Test
    public void RpcRequestValidator_ValidatorDoesNotRequireRequestId_NoExceptionThrown()
    {
        _validator.setRequiresRequestId(false);

        _validator.validate(new RpcRequest(null));
    }

    @Test
    public void RpcRequestValidator_SimpleRequestValid_NoExceptionThrown()
    {
        _validator.validate(new SimpleRpcRequest("sampleParam"));
    }

    @Test
    public void RpcRequestValidator_SimpleRequestParamNotSupplied_ThrowsException()
    {
        assertThrowsMissingFieldException("Param", () -> _validator.validate(new SimpleRpcRequest(null)));
        assertThrowsMissingFieldException("Param", () -> _validator.validate(new SimpleRpcRequest("")));
    }

    @Test
    public void RpcRequestValidator_ValidateRequestAgainstMoreComplexType_ThrowsException()
    {
        assertThrowsMissingFieldException("Param", () -> _validator.validate(new RpcRequest(), SimpleRpcRequest.class));
    }

    @Test
    public void RpcRequestValidator_ValidateRequestAgainstSimplerType_NoExceptionThrown()
    {
        _validator.validate(new OrdinaryRpcRequest("1", "2"), SimpleRpcRequest.class);
    }

    @Test
    public void RpcRequestValidator_ValidateRequestAgainstSimilarType_NoExceptionThrown()
    {
        _validator.validate(new SimpleRpcRequest("sampleParam"), SimpleRpcRequest2.class);
    }

    private void assertThrowsMissingFieldException(final String fieldName, final Runnable action)
    {
        Exception ex = ExceptionAssert.getException(RpcInvalidRequestException.class, action);
        Assert.assertTrue(ex.getMessage().contains(fieldName));
    }

    private class SimpleRpcRequest extends RpcRequest
    {
        public final String Param;

        public SimpleRpcRequest(final String param)
        {
            super();
            Param = param;
        }
    }

    private class SimpleRpcRequest2 extends RpcRequest
    {
        public final String Param;

        public SimpleRpcRequest2(final String param)
        {
            super();
            Param = param;
        }
    }

    private class OrdinaryRpcRequest extends RpcRequest
    {
        public final String Param;
        public final String Param2;

        public OrdinaryRpcRequest(final String param, final String param2)
        {
            super();
            Param = param;
            Param2 = param2;
        }
    }
}
