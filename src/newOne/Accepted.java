package newOne;

/**
 * Created by Satori on 2016/2/23.
 */
public class Accepted extends Action
{
    Accepted()
    {
        type = Type.Acc;
    }
    @Override
    Type getType()
    {
        return type;
    }
}
