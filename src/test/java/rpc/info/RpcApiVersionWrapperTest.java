package rpc.info;

import org.junit.*;
import rpc.IRpcApi;
import rpc.RpcRequest;
import rpc._testObjects.MockRpcApi;

public class RpcApiVersionWrapperTest
{
    @Test
    public void RpcApiVersionWrapperTests_GetVersion_IsCorrect()
    {
        IRpcApi wrapped = new RpcApiVersionWrapper(new MockRpcApi());

        RpcApiVersionResponse version = (RpcApiVersionResponse)wrapped.getCallHandlers()
                .get(MockRpcApi.BaseUri + "/version").getResponse(new RpcRequest());

        Assert.assertEquals(MockRpcApi.Version, version.Version);
    }
}
