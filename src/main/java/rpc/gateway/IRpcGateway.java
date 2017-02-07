package rpc.gateway;

import rpc.IRpcClient;
import rpc.health.IRpcApiHealthGateway;
import rpc.info.IRpcApiInfoGateway;

public interface IRpcGateway extends IRpcClient, IRpcApiHealthGateway, IRpcApiInfoGateway
{
}
