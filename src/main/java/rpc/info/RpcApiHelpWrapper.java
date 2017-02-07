package rpc.info;

import rpc.IRpcApi;
import rpc.IRpcCallHandler;
import rpc.RpcApiWrapper;
import rpc.RpcCallSupplier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RpcApiHelpWrapper extends RpcApiWrapper
{
    public RpcApiHelpWrapper(final IRpcApi rpcApi)
    {
        super(rpcApi, "/help", uri -> new RpcCallSupplier<>(uri, x -> new RpcApiHelpResponse(x.RequestId, getApiCallDescriptions(rpcApi))));
    }

    private static List<String> getApiCallDescriptions(final IRpcApi rpcApi)
    {
        return rpcApi.getCallHandlers().entrySet().stream()
                .sorted((x1, x2) -> x1.getKey().compareTo(x2.getKey()))
                .map(x -> getDescription(x.getValue()))
                .collect(Collectors.toList());
    }

    private static String getDescription(final IRpcCallHandler callHandler)
    {
        return "URI: '" + callHandler.getUri() + "', "
                + "Params: (" + getParamDescriptions(callHandler.getRequestType()) + ")";
    }

    private static String getParamDescriptions(final Class requestType)
    {
        List<String> requiredParams = new ArrayList<>();
        Field[] fields = requestType.getFields();
        for (Field field : fields)
            requiredParams.add(field.getType().getSimpleName() + " " + field.getName());
        return String.join(", ", requiredParams);
    }
}
