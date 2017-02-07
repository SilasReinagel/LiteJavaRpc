package rpc.info;

import org.junit.*;
import rpc._testObjects.MockRpcClient;
import java.util.Collections;
import java.util.List;

public class RpcApiInfoGatewayTest
{
    private MockRpcClient _rpcClientMock;
    private RpcApiInfoGateway _gateway;
    private final String _sampleApiBaseUri = "/api/mock";

    @Before
    public void init()
    {
        _rpcClientMock = new MockRpcClient();
        _gateway = new RpcApiInfoGateway(_rpcClientMock);
    }

    @Test
    public void RpcApiHealthGateway_GetVersion_VersionCorrect()
    {
        setApiVersionResponse("1.2.3");

        String version = _gateway.getVersion(_sampleApiBaseUri);

        Assert.assertEquals("1.2.3", version);
    }

    @Test
    public void RpcApiHealthGateway_GetApiCallInfo_CallInfoCorrect()
    {
        _rpcClientMock.setResponseObject(_sampleApiBaseUri + "/help", new RpcApiHelpResponse("", Collections.singletonList("ApiInfo")));

        List<String> callInfo = _gateway.getApiCallInfo(_sampleApiBaseUri);

        Assert.assertEquals(1, callInfo.size());
        Assert.assertEquals("ApiInfo", callInfo.get(0));
    }

    private void setApiVersionResponse(final String version)
    {
        String uri = _sampleApiBaseUri + "/version";
        _rpcClientMock.setResponseObject(uri, new RpcApiVersionResponse("", version));
    }
}
