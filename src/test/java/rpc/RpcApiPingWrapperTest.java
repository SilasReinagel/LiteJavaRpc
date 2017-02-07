package rpc;

import org.junit.*;
import rpc._testObjects.MockRpcApi;
import rpc.health.RpcApiPingWrapper;

public class RpcApiPingWrapperTest
{
    @Test
    public void RpcApiPingWrapper_Wrapper_ContainsPingCall()
    {
        IRpcApi wrapped = new RpcApiPingWrapper(new MockRpcApi());

        Assert.assertTrue(wrapped.getCallHandlers().containsKey(MockRpcApi.BaseUri + "/ping"));
    }
}
