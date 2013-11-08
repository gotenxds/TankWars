package machanet.TankWars.messageBuilder;

public enum Message
{
    moveForward(200),
    moveRight(201),
    moveBackward(202),
    moveLeft(203),
    turnStart("turnStart"),
    turnEnd("turnEnd");

    private final int id;
    private final String name;

    Message(int id)
    {
        this.id = id;
        this.name = "";
    }

    Message(String name)
    {
        this.id = 0;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }
}
