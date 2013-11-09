package machanet.TankWars;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import machanet.RangeSeekBarListener;
import machanet.TankWars.arduinoUtils.ArduinoController;
import machanet.TankWars.arduinoUtils.ArduinoControllerFactory;
import machanet.TankWars.arduinoUtils.MutableSupplier;
import machanet.TankWars.blueToothUtils.BlueToothDevicesInRangeProvider;
import machanet.TankWars.blueToothUtils.BlueToothInitializer;
import machanet.TankWars.blueToothUtils.ConnectorCollectionListener;
import machanet.TankWars.components.ExitAlertDialogFactory;
import machanet.TankWars.serverConnectivity.ServerCommunicator;

public class ControlScreen extends Activity
{
    private final MutableSupplier<BluetoothSocket> bluetoothSocketSupplier = new MutableSupplier<BluetoothSocket>();
    private final BlueToothInitializer blueToothInitializer = BlueToothInitializer.create();
    private final BlueToothDevicesInRangeProvider blueToothDevicesInRangeProvider = BlueToothDevicesInRangeProvider.create();
    private final ArduinoController arduinoController = ArduinoControllerFactory.createFrom(bluetoothSocketSupplier);
    private ServerCommunicator serverCommunicator;
    private ImageButton upButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;
    private SeekBar seekBar;
    private SeekBar angleBar;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controlscreen);

        initializeUIComponents();
        enableBluetoothOrExit();
        registerBlueToothReceiver();
        initializeServerConnectivity();
        initializeAngleBar();
    }

    private void initializeServerConnectivity()
    {
        Bundle extras = getIntent().getExtras();
        Boolean isServerGame = extras.getBoolean("serverGame");

        if (isServerGame)
        {
            serverCommunicator = ServerCommunicator.createFrom(extras.getString("ip"), extras.getInt("port"));
        }
    }

    private void initializeUIComponents()
    {
        upButton = (ImageButton) findViewById(R.id.upArrow);
        rightButton = (ImageButton) findViewById(R.id.rightArrow);
        downButton = (ImageButton) findViewById(R.id.downArrow);
        leftButton = (ImageButton) findViewById(R.id.leftArrow);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        angleBar = (SeekBar) findViewById(R.id.angleBar);
    }

    private void initializeAngleBar()
    {
        angleBar.setOnSeekBarChangeListener(new RangeSeekBarListener(arduinoController));
    }

    private void enableBluetoothOrExit()
    {
        boolean isBlueToothEnabled = blueToothInitializer.enableBlueTooth();

        if (!isBlueToothEnabled)
        {
            ExitAlertDialogFactory.alertUser(this);
            System.exit(0);
        }
    }

    public void arrowClick(View arrow)
    {
        if (arrow.equals(upButton))
        {
            arduinoController.moveForward();
        }
        else if(arrow.equals(rightButton))
        {
            arduinoController.moveRight();
        }
        else if (arrow.equals(downButton))
        {
            arduinoController.moveBackward();
        }
        else if (arrow.equals(leftButton))
        {
            arduinoController.moveLeft();
        }
    }

    public void fireClicked(View view)
    {
        arduinoController.shot(seekBar.getProgress());
    }

    private void registerBlueToothReceiver()
   {
        registerReceiver(blueToothDevicesInRangeProvider, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        blueToothDevicesInRangeProvider.getBluetoothDevices().addCollectionListener(ConnectorCollectionListener.createFrom(bluetoothSocketSupplier));
        BluetoothAdapter.getDefaultAdapter().startDiscovery();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(blueToothDevicesInRangeProvider);
    }
}
