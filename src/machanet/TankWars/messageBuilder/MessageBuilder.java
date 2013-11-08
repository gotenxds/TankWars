package machanet.TankWars.messageBuilder;

public class MessageBuilder
{
    public About of(MessageType messageType)
    {
        return new About(messageType);
    }
}
