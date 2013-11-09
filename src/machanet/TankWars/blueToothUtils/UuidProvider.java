package machanet.TankWars.blueToothUtils;

import java.util.UUID;

class UuidProvider
{
    public static UUID create()
    {
       return UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
    }
}
