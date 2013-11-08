package machanet.TankWars.blueToothUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.google.common.base.Optional;
import machanet.TankWars.arduinoUtils.MutableSupplier;

import java.io.IOException;

public class BlueToothConnector
{
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private MutableSupplier<BluetoothSocket> bluetoothSocketSupplier;

    public BlueToothConnector(MutableSupplier<BluetoothSocket> bluetoothSocketSupplier)
    {
        this.bluetoothSocketSupplier = bluetoothSocketSupplier;
    }

    public Optional<BluetoothSocket> connectTo(String address)
    {
        try
        {
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
            BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(UuidProvider.create());
            socket.connect();
            bluetoothSocketSupplier.set(socket);

            return Optional.of(socket);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return Optional.absent();
    }
}
