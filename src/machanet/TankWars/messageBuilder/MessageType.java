package machanet.TankWars.messageBuilder;

public enum MessageType
{
    message("MSG"),
    command("CMD");

    private final String prefix;

    MessageType(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }
}
