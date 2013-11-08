package machanet.TankWars.blueToothUtils;

import android.bluetooth.BluetoothAdapter;

public class BlueToothInitializer
{
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public boolean enableBlueTooth()
    {
        if(!bluetoothAdapter.isEnabled())
        {
            return bluetoothAdapter.enable();
        }

        return true;
    }

    public static BlueToothInitializer create()
    {
        return new BlueToothInitializer();
    }
}
