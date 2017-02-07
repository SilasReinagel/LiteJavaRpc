package rpc;

import org.junit.*;
import rpc._testobjects.MockRpcApi;

public class RpcApiWrapperTest
{
    private MockRpcApi _apiMock;
    private RpcApiWrapper _wrapper;

    @Before
    public void init()
    {
        _apiMock = new MockRpcApi();
        _apiMock.addCallHandler("/originalCall");
        _wrapper = new RpcApiWrapper(_apiMock, "/newCall", uri -> new RpcCallSupplier<>(uri, x -> new RpcResponse(x.RequestId)));
    }

    @Test
    public void RpcApiWrapper_Info_MatchesApiInfo()
    {
        Assert.assertEquals(_apiMock.info(), _wrapper.info());
    }

    @Test
    public void RpcApiWrapper_GetCallHandlers_ContainsAllApiCallHandlers()
    {
        Assert.assertTrue(_wrapper.getCallHandlers().entrySet().containsAll(_apiMock.getCallHandlers().entrySet()));
    }

    @Test
    public void RpcApiWrapper_GetNewCallHandler_MatchesAddedHandler()
    {
        Assert.assertTrue(_wrapper.getCallHandlers().containsKey(_apiMock.info().BaseUri + "/newCall"));
    }
}
