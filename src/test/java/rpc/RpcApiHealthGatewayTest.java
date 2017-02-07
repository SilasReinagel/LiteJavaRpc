package rpc;

import org.junit.*;
import rpc._testObjects.MockRpcClient;
import rpc.exceptions.RpcException;
import rpc.health.RpcApiHealthGateway;
import testing.ExceptionAssert;

public class RpcApiHealthGatewayTest
{
    private MockRpcClient _rpcClientMock;
    private RpcApiHealthGateway _gateway;
    private final String _sampleApiBaseUri = "/api/mock";

    @Before
    public void init()
    {
        _rpcClientMock = new MockRpcClient();
        _gateway = new RpcApiHealthGateway(_rpcClientMock);
    }

    @Test
    public void RpcApiHealthGateway_ApiNotAvailable_IsAvailableFalse()
    {
        Assert.assertFalse(_gateway.isAvailable(_sampleApiBaseUri));
    }

    @Test
    public void RpcApiHealthGateway_ApiAvailable_IsAvailableTrue()
    {
        setApiPingResponse();

        Assert.assertTrue(_gateway.isAvailable(_sampleApiBaseUri));
    }

    @Test
    public void RpcApiHealthGateway_ResponseTime_IsCorrect()
    {
        setApiPingResponse();
        _rpcClientMock.setResponseTime(10);

        int responseTimeMs = _gateway.getResponseTimeMs(_sampleApiBaseUri);

        Assert.assertEquals(10, responseTimeMs, 2.0);
    }

    @Test
    public void RpcApiHealthGateway_ResponseTimeWhenApiNotAvailable_ThrowsRpcException()
    {
        ExceptionAssert.assertThrows(RpcException.class, () -> _gateway.getResponseTimeMs(_sampleApiBaseUri));
    }

    private void setApiPingResponse()
    {
        String uri = _sampleApiBaseUri + "/ping";
        _rpcClientMock.setResponseObject(uri, new RpcResponse(""));
    }
}
