package machanet.TankWars.arduinoUtils;

import android.bluetooth.BluetoothSocket;
import com.google.common.base.Supplier;

public class ArduinoControllerFactory
{
    public static ArduinoController createFrom(Supplier<BluetoothSocket> bluetoothSocketSupplier)
    {
        ArduinoCommander arduinoCommander = ArduinoCommanderFactory.createFrom(bluetoothSocketSupplier);
        return new ArduinoController(arduinoCommander);
    }
}
