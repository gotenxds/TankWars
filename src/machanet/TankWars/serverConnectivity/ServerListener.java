package machanet.TankWars.serverConnectivity;

import java.util.EventListener;

public interface ServerListener extends EventListener
{
    public void dataReceived(ServerEvent serverEvent);
}
