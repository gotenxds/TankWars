package machanet.TankWars.blueToothUtils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.ochafik.util.listenable.CollectionEvent;
import com.ochafik.util.listenable.CollectionListener;
import machanet.TankWars.arduinoUtils.MutableSupplier;

public class ConnectorCollectionListener implements CollectionListener<BluetoothDevice>
{
    private final static String arduinoName = "HC-06";
    private final BlueToothConnector blueToothConnector;

    public ConnectorCollectionListener(BlueToothConnector blueToothConnector)
    {
        this.blueToothConnector = blueToothConnector;
    }

    @Override
    public void collectionChanged(CollectionEvent<BluetoothDevice> event)
    {
        if (event.getType() == CollectionEvent.EventType.ADDED)
        {
            findAndConnectToArduino(event);
        }
    }

    private void findAndConnectToArduino(CollectionEvent<BluetoothDevice> event)
    {
        for (BluetoothDevice device : event.getElements())
        {
            if (device.getName().equals(arduinoName))
            {
                blueToothConnector.connectTo(device.getAddress());
                removeSelf(event);
            }
        }
    }

    public static ConnectorCollectionListener createFrom(MutableSupplier<BluetoothSocket> bluetoothSocketSupplier)
    {
        return new ConnectorCollectionListener(new BlueToothConnector(bluetoothSocketSupplier));
    }

    private void removeSelf(CollectionEvent<BluetoothDevice> event)
    {
        event.getSource().removeCollectionListener(this);
    }
}
