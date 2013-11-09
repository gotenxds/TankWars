package machanet.TankWars.serverConnectivity;

public class ServerEvent
{
    private final String data;
    private final ServerEventType serverEventType;

    public ServerEvent(String data, ServerEventType serverEventType)
    {
        this.data = data;
        this.serverEventType = serverEventType;
    }
}
