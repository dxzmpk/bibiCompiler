package newOne;

/**
 * Created by Satori on 2016/2/20.
 */
public abstract  class Action
{
    enum Type
    {
        Acc, Shift, Reduce
    }
    Type type;
    abstract Type getType();
}
