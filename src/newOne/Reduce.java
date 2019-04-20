package newOne;

/**
 * Created by Satori on 2016/2/20.
 */
public class Reduce extends Action
{
    int reduceCnt;
    char l_VN;
    String exp;
    Reduce(int _reduce_Cnt, char _l_VN, String _exp)
    {
        reduceCnt = _reduce_Cnt;
        l_VN = _l_VN;
        exp = _exp;
        type = Type.Reduce;
    }
    int getReduceCnt()
    {
        return reduceCnt;
    }
    char getL_VN()
    {
        return l_VN;
    }
    String getExp()
    {
        return exp;
    }
    @Override
    Type getType()
    {
        return type;
    }
}
