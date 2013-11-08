package machanet.TankWars.serverConnectivity;

import machanet.TankWars.messageBuilder.MessageBuilder;

public class ServerCommunicatorFactory
{
   public ServerCommunicator createFrom(String ip, int port)
   {
       return new ServerCommunicator(new TcpSocketFactory(), new MessageBuilder(), ip, port);
   }
}
