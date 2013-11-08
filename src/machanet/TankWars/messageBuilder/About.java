package machanet.TankWars.messageBuilder;

public class About
{
    private final static Character delimiter = '~';
    private final MessageType messageType;

    public About(MessageType messageType)
    {
        this.messageType = messageType;
    }

    public String about(Message message)
    {
        return String.format("%s%s%s", messageType, delimiter, message);
    }
}
