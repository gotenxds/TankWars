package machanet.TankWars.serverConnectivity;

public class TcpConnectivityException extends RuntimeException
{
    public TcpConnectivityException(String detailMessage)
    {
        super(detailMessage);
    }
}
