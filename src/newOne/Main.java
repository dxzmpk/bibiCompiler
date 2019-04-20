package newOne;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Satori on 2016/2/17.
 */
public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> strs = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the number of expressions");
        int count = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < count; i++)
        {
            String str = scan.nextLine();
            strs.add(str);
        }
        System.out.println("Input the start VN");
        char startCh = scan.nextLine().charAt(0);
        Table table = new Table(startCh, strs);
        table.printForm();
        table.printConflicts();
    }
}
