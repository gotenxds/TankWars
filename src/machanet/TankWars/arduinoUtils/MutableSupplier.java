package machanet.TankWars.arduinoUtils;

import com.google.common.base.Supplier;

public class MutableSupplier<T> implements Supplier<T>
{
    private T t;

    @Override
    public T get()
    {
        return t;
    }

    public void set(T t)
    {
        this.t = t;
    }
}
