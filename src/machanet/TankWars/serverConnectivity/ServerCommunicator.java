package machanet.TankWars.serverConnectivity;

import com.google.common.base.Optional;
import machanet.TankWars.messageBuilder.Message;
import machanet.TankWars.messageBuilder.MessageBuilder;
import machanet.TankWars.messageBuilder.MessageType;

import java.net.Socket;

public class ServerCommunicator
{
    private final TcpDataSender tcpDataSender;
    private final MessageBuilder messageBuilder;

    ServerCommunicator(TcpSocketFactory tcpSocketFactory, MessageBuilder messageBuilder, String serverIp, int port)
    {
        this.messageBuilder = messageBuilder;
        this.tcpDataSender = TcpDataSender.createFrom(createSocket(tcpSocketFactory, serverIp, port));
    }

    public void turnEnd()
    {
        tcpDataSender.send
                (
                        messageBuilder
                            .of(MessageType.message)
                            .about(Message.turnEnd)
                );
    }

    private Socket createSocket(TcpSocketFactory tcpSocketFactory, String serverIp, int port)
    {
        Optional<Socket> socketOptional = tcpSocketFactory.createFrom(serverIp, port);

        if (socketOptional.isPresent())
        {
            return socketOptional.get();
        }

        throw new TcpConnectivityException("Unable to connect to server.");
    }
}
