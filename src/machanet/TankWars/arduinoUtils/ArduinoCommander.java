package machanet.TankWars.arduinoUtils;

import machanet.TankWars.messageBuilder.Message;

class ArduinoCommander
{
    private final ArduinoDataSender arduinoDataSender;

    public ArduinoCommander(ArduinoDataSender arduinoDataSender)
    {
        this.arduinoDataSender = arduinoDataSender;
    }

    public void commandTo(Message message)
    {
        arduinoDataSender.send(message.getId());
    }

    public void sendData(int data)
    {
        arduinoDataSender.send(data);
    }
}
