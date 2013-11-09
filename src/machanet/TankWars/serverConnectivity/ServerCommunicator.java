package machanet.TankWars.serverConnectivity;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.MoreExecutors;
import machanet.TankWars.messageBuilder.Message;
import machanet.TankWars.messageBuilder.MessageBuilder;
import machanet.TankWars.messageBuilder.MessageType;

import java.net.Socket;
import java.util.Set;

public class ServerCommunicator
{
    private final TcpDataSender tcpDataSender;
    private final TcpDataReaderCallable tcpDataReaderCallable;
    private final MessageBuilder messageBuilder;
    private final Set<ServerListener> serverListeners = Sets.newHashSet();

    ServerCommunicator(TcpSocketFactory tcpSocketFactory, MessageBuilder messageBuilder, String serverIp, int port)
    {
        Socket socket = createSocket(tcpSocketFactory, serverIp, port);

        this.messageBuilder = messageBuilder;
        this.tcpDataSender = TcpDataSender.createFrom(socket);
        this.tcpDataReaderCallable = TcpDataReaderCallable.createFrom(socket);
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

    public void beginRead(RunableServerListener runableServerListener)
    {
        ListenableFutureTask<String> stringListenableFutureTask = ListenableFutureTask.create(tcpDataReaderCallable);

        stringListenableFutureTask.addListener(runableServerListener, MoreExecutors.sameThreadExecutor());
    }

    public <L extends ServerListener> void addServerListener(L serverListener)
    {
        serverListeners.add(serverListener);
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

    public static ServerCommunicator createFrom(String ip, int port)
    {
        return new ServerCommunicator(new TcpSocketFactory(), new MessageBuilder(), ip, port);
    }
}
