package machanet.TankWars.arduinoUtils;

import android.bluetooth.BluetoothSocket;
import com.google.common.base.Supplier;

class ArduinoCommanderFactory
{
    static ArduinoCommander createFrom(Supplier<BluetoothSocket> bluetoothSocketSupplier)
    {
        return new ArduinoCommander(ArduinoDataSender.createFrom(bluetoothSocketSupplier));
    }
}
