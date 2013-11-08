package machanet.TankWars.serverConnectivity;

import android.util.Log;
import com.google.common.base.Optional;
import machanet.TankWars.io.DataSender;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class TcpDataSender implements DataSender
{
    final Socket socket;

    TcpDataSender(Socket socket)
    {
        this.socket = socket;
    }

    public void send(String data)
    {
        OutputStream outputStream = getOutputStream().get();
        sendData(outputStream, data.getBytes());
    }

    public void send(int data)
    {
        OutputStream outputStream = getOutputStream().get();
        sendData(outputStream, data);
    }

    public void flushData()
    {
        try
        {
            getOutputStream().get().flush();
        }
        catch (IOException e)
        {
            Log.d("Error", "Filed to flush data.");
        }
    }

    private void sendData(OutputStream outputStream, byte[] data)
    {
        try
        {
            outputStream.write(data);
        }
        catch (IOException e)
        {
            Log.d("Error", "Filed to send data.");
        }
    }

    private void sendData(OutputStream outputStream, int data)
    {
        try
        {
            outputStream.write(data);
        }
        catch (IOException e)
        {
            Log.d("Error", "Filed to send data.");
        }
    }

    private Optional<OutputStream> getOutputStream()
    {
        try
        {
            return Optional.of(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return Optional.absent();
    }

    public static TcpDataSender createFrom(Socket socket)
    {
        return new TcpDataSender(socket);
    }
}
