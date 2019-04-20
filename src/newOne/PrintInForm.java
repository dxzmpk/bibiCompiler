package newOne;

/**
 * Created by Satori on 2016/2/24.
 */
public class PrintInForm
{
    private final int limit = 8;
    void print(String str)
    {
        if (str.length() > limit)
        {
            return;
        }
        int vacant = limit - str.length();
        System.out.print(str);
        for (int i = 0; i < vacant; i++)
        {
            System.out.print(" ");
        }
    }
}
