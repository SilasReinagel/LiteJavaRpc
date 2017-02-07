package rpc.info;

import rpc.IRpcApi;
import rpc.RpcCallHandler;
import rpc.RpcRequest;
import rpc.RpcResponse;
import rpc._testObjects.MockRpcApi;
import rpc._testObjects.SampleRpcUserRequest;
import org.junit.*;

public class RpcApiHelpWrapperTests
{
    @Test
    public void RpcApiHelpWrapper_WrapApi_ContainsHealthCall()
    {
        IRpcApi wrapped = new RpcApiHelpWrapper(new MockRpcApi());

        Assert.assertTrue(wrapped.getCallHandlers().containsKey(MockRpcApi.HelpUri));
    }

    @Test
    public void RpcApiHelpWrapper_ApiCallsForZeroCallApi_ZeroCalls()
    {
        IRpcApi wrapped = new RpcApiHelpWrapper(new MockRpcApi());

        RpcApiHelpResponse help = getHelpResponse(wrapped);

        Assert.assertEquals(0, help.ApiCalls.size());
    }

    @Test
    public void RpcApiHelpWrapper_ApiCallForSingleActionCall_IsCorrect()
    {
        MockRpcApi api = new MockRpcApi();
        api.addCallHandler("/call");
        IRpcApi wrapped = new RpcApiHelpWrapper(api);

        RpcApiHelpResponse help = getHelpResponse(wrapped);
        String apiCallHelp = help.ApiCalls.get(0);

        Assert.assertTrue(apiCallHelp.contains(MockRpcApi.BaseUri + "/call"));
        Assert.assertTrue(apiCallHelp.contains("Params"));
        Assert.assertTrue(apiCallHelp.contains("String RequestId"));
    }

    @Test
    public void RpcApiHelpWrapper_ApiCallForFunctionCall_IsCorrect()
    {
        MockRpcApi api = new MockRpcApi();
        api.addCallHandler(new RpcCallHandler<>(MockRpcApi.BaseUri + "/user", SampleRpcUserRequest.class, x -> new RpcResponse(x.RequestId)));
        IRpcApi wrapped = new RpcApiHelpWrapper(api);

        RpcApiHelpResponse help = getHelpResponse(wrapped);
        String apiCallHelp = help.ApiCalls.get(0);

        Assert.assertTrue(apiCallHelp.contains(MockRpcApi.BaseUri + "/user"));
        Assert.assertTrue(apiCallHelp.contains("Params"));
        Assert.assertTrue(apiCallHelp.contains("String RequestId"));
        Assert.assertTrue(apiCallHelp.contains("String UserId"));
    }

    @Test
    public void RpcApiHelpWrapper_MultipleApiCalls_Alphabetical()
    {
        MockRpcApi api = new MockRpcApi();
        api.addCallHandler("/xyz");
        api.addCallHandler("/abc");
        api.addCallHandler("/123");
        IRpcApi wrapped = new RpcApiHelpWrapper(api);

        RpcApiHelpResponse help = getHelpResponse(wrapped);

        Assert.assertTrue(help.ApiCalls.get(0).contains(MockRpcApi.BaseUri + "/123"));
        Assert.assertTrue(help.ApiCalls.get(1).contains(MockRpcApi.BaseUri + "/abc"));
        Assert.assertTrue(help.ApiCalls.get(2).contains(MockRpcApi.BaseUri + "/xyz"));
    }

    private RpcApiHelpResponse getHelpResponse(final IRpcApi rpcApi)
    {
        return (RpcApiHelpResponse)rpcApi.getCallHandlers().get(MockRpcApi.HelpUri).getResponse(new RpcRequest());
    }
}
