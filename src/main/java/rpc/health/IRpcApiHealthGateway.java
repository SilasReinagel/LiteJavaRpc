package rpc.health;

public interface IRpcApiHealthGateway
{
    boolean isAvailable(final String apiBaseUri);
    int getResponseTimeMs(final String apiBaseUri);
}
