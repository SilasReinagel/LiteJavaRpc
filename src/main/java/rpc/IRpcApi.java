package rpc;

import java.util.Map;

public interface IRpcApi
{
    RpcApiInfo info();
    Map<String, IRpcCallHandler> getCallHandlers();
}
