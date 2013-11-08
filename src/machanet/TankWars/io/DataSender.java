package machanet.TankWars.io;

public interface DataSender
{

    void send(String data);

    void send(int data);

    void flushData();
}
