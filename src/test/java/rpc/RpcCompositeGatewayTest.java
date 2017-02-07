package rpc;

import rpc._testobjects.MockRpcApi;
import rpc._testobjects.MockRpcClient;
import rpc.gateway.RpcCompositeGateway;
import org.junit.*;
import rpc.info.RpcApiHelpResponse;
import rpc.info.RpcApiVersionResponse;

import java.util.ArrayList;

public class RpcCompositeGatewayTest
{
    private MockRpcClient _rpcClientMock;
    private RpcCompositeGateway _gateway;

    @Before
    public void init()
    {
        _rpcClientMock = new MockRpcClient();
        _rpcClientMock.setResponseObjectRequired(false);
        _gateway = new RpcCompositeGateway(_rpcClientMock);
    }

    @Test
    public void RpcCompositeGateway_Execute_UnderlyingClientIsCalled()
    {
        _rpcClientMock.setResponseObject("123", new RpcResponse(""));

        _gateway.execute("123", new RpcRequest());

        Assert.assertTrue(_rpcClientMock.wasCalled("123"));
    }

    @Test
    public void RpcCompositeGateway_Get_UnderlyingClientIsCalled()
    {
        _rpcClientMock.setResponseObject("321", new RpcResponse(""));

        _gateway.get("321", new RpcRequest(), RpcResponse.class);

        Assert.assertTrue(_rpcClientMock.wasCalled("321"));
    }

    @Test
    public void RpcCompositeGateway_IsAvailable_PingWasCalled()
    {
        _gateway.isAvailable(MockRpcApi.BaseUri);

        Assert.assertTrue(_rpcClientMock.wasCalled(MockRpcApi.PingUri));
    }

    @Test
    public void RpcCompositeGateway_GetResponseTime_PingWasCalled()
    {
        _gateway.getResponseTimeMs(MockRpcApi.BaseUri);

        Assert.assertTrue(_rpcClientMock.wasCalled(MockRpcApi.PingUri));
    }

    @Test
    public void RpcCompositeGateway_GetApiVersion_VersionWasCalled()
    {
        _rpcClientMock.setResponseObject(MockRpcApi.VersionUri, new RpcApiVersionResponse("", MockRpcApi.Version));

        _gateway.getVersion(MockRpcApi.BaseUri);

        Assert.assertTrue(_rpcClientMock.wasCalled(MockRpcApi.VersionUri));
    }

    @Test
    public void RpcCompositeGateway_GetApiCallInfo_HelpWasCalled()
    {
        _rpcClientMock.setResponseObject(MockRpcApi.HelpUri, new RpcApiHelpResponse("", new ArrayList<>()));

        _gateway.getApiCallInfo(MockRpcApi.BaseUri);

        Assert.assertTrue(_rpcClientMock.wasCalled(MockRpcApi.HelpUri));
    }
}
