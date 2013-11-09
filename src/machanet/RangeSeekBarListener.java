package machanet;

import android.widget.SeekBar;
import machanet.TankWars.arduinoUtils.ArduinoController;
import machanet.TankWars.serverConnectivity.ServerCommunicatorFactory;

public class RangeSeekBarListener implements SeekBar.OnSeekBarChangeListener
{
    private final ArduinoController arduinoController;
    private ServerCommunicatorFactory serverCommunicatorFactory = new ServerCommunicatorFactory();

    public RangeSeekBarListener(ArduinoController arduinoController)
    {
        this.arduinoController = arduinoController;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        arduinoController.adjustAim(seekBar.getProgress());
    }
}
