package newOne;

/**
 * Created by Satori on 2016/2/20.
 */
public class Shift extends Action
{
    int nextState;
    Shift(int nextSta)
    {
        nextState = nextSta;
        type = Type.Shift;
    }
    int getNextState()
    {
        return nextState;
    }
    @Override
    Type getType()
    {
        return type;
    }
}
