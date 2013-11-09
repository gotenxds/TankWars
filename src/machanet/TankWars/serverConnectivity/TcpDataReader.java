package machanet.TankWars.serverConnectivity;

import com.google.common.base.Optional;
import machanet.TankWars.io.DataReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class TcpDataReader implements DataReader
{
    private final Socket socket;

    public TcpDataReader(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public String read()
    {
        if (getInputStream().isPresent())
        {
            DataInputStream dataInputStream = new DataInputStream(getInputStream().get());
            try
            {
                dataInputStream.readUTF();
            }
            catch (IOException e)
            {
                throw new TcpConnectivityException("Cant read from server");
            }
        }

        return "";
    }

    private Optional<InputStream> getInputStream()
    {
        try
        {
            return Optional.of(socket.getInputStream());
        }
        catch (IOException e)
        {
            return Optional.absent();
        }
    }

    public static TcpDataReader createFrom(Socket socket)
    {
        return new TcpDataReader(socket);
    }
}
