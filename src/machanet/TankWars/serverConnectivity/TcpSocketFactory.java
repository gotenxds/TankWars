package machanet.TankWars.serverConnectivity;

import com.google.common.base.Optional;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

class TcpSocketFactory
{
    public Optional<Socket> createFrom(String ip, int port)
    {
        try
        {
            InetAddress adr = InetAddress.getByName(ip);

            return Optional.of(new Socket(adr, port));
        }
        catch (IOException e)
        {
            return Optional.absent();
        }
    }
}
