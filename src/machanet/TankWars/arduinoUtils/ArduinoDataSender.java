package machanet.TankWars.arduinoUtils;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import machanet.TankWars.io.DataSender;

import java.io.IOException;
import java.io.OutputStream;

class ArduinoDataSender implements DataSender
{
    final Supplier<BluetoothSocket> socketSupplier;

    ArduinoDataSender(Supplier<BluetoothSocket> socketSupplier)
    {
        this.socketSupplier = socketSupplier;
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
            return Optional.of(socketSupplier.get().getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return Optional.absent();
    }

    public static ArduinoDataSender createFrom(Supplier<BluetoothSocket> socket)
    {
        return new ArduinoDataSender(socket);
    }
}
