package machanet.TankWars.serverConnectivity;

import java.net.Socket;
import java.util.concurrent.Callable;

public class TcpDataReaderCallable implements Callable<String>
{
    private final TcpDataReader tcpDataReader;

    TcpDataReaderCallable(TcpDataReader tcpDataReader)
    {
        this.tcpDataReader = tcpDataReader;
    }

    @Override
    public String call() throws Exception
    {
        return tcpDataReader.read();
    }

    public static TcpDataReaderCallable createFrom(Socket socket)
    {
        return new TcpDataReaderCallable(TcpDataReader.createFrom(socket));
    }
}
