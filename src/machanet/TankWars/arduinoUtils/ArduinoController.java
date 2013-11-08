package machanet.TankWars.arduinoUtils;

import machanet.TankWars.messageBuilder.Message;

public class ArduinoController
{
    private final ArduinoCommander arduinoCommander;

    ArduinoController(ArduinoCommander arduinoCommander)
    {
        this.arduinoCommander = arduinoCommander;
    }

    public void moveForward()
    {
        arduinoCommander.commandTo(Message.moveForward);
    }

    public void moveBackward()
    {
        arduinoCommander.commandTo(Message.moveBackward);
    }

    public void moveLeft()
    {
        arduinoCommander.commandTo(Message.moveLeft);
    }

    public void moveRight()
    {
        arduinoCommander.commandTo(Message.moveRight);
    }

    public void adjustAim(int angle)
    {
        arduinoCommander.sendData(angle);
    }

    public void shot(int power)
    {
        arduinoCommander.sendData(power + 91);
    }
}
