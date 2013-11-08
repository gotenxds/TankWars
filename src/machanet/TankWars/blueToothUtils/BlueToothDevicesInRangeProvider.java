package machanet.TankWars.blueToothUtils;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.common.collect.Sets;
import com.ochafik.util.listenable.ListenableCollections;
import com.ochafik.util.listenable.ListenableSet;

public class BlueToothDevicesInRangeProvider extends BroadcastReceiver
{
    private final ListenableSet<BluetoothDevice> bluetoothDevices = ListenableCollections.listenableSet(Sets.<BluetoothDevice>newHashSet());

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction()))
        {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            bluetoothDevices.add(device);
        }
    }

    public static BlueToothDevicesInRangeProvider create()
    {
        return new BlueToothDevicesInRangeProvider();
    }

    public ListenableSet<BluetoothDevice> getBluetoothDevices()
    {
        return bluetoothDevices;
    }
}
