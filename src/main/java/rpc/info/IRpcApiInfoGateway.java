package rpc.info;

import java.util.List;

public interface IRpcApiInfoGateway
{
    String getVersion(final String apiBaseUri);
    List<String> getApiCallInfo(final String apiBaseUri);
}
