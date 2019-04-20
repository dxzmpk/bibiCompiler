package newOne;

import java.util.ArrayList;

/**
 * Created by Satori on 2016/2/23.
 */
public class Conflict
{
    private int row;
    private int col;
    private ArrayList<Action> actions;
    Conflict(int _row, int _col, ArrayList<Action> _actions)
    {
        row = _row;
        col = _col;
        actions = _actions;
    }
    int getRow()
    {
        return row;
    }
    int getCol()
    {
        return col;
    }
    ArrayList<Action> getActions()
    {
        return actions;
    }

}
