package newOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Satori on 2016/2/13.
 *Attention
 * 1.NULL and EOF belongs to VT
 */
public class Symbols
{
    public static final char EOF = (char) -1;
    public static final char EPSILON = (char)0;

    private HashMap<Character, ArrayList<ArrayList<Character>>> exps = new HashMap<>();
    private HashMap<Character, ArrayList<Character>> first = new HashMap<>();

    Symbols(ArrayList<String> strs)
    {
        readFromStrings(strs);
    }
    /*Judge Whether ch is VN*/
    static boolean isVN(char ch)
    {
        return (ch >= 'A' && ch <= 'Z');
    }
    /*find one*/
    /*fill the first*/
    private void fillFirst()
    {
        //initial, allocate space for each VN
        Iterator itr_ini = exps.entrySet().iterator();
        while (itr_ini.hasNext())// search each VN
        {
            Map.Entry entry = (Map.Entry) itr_ini.next();
            first.put((Character)entry.getKey(), new ArrayList<>());
        }

        //then fill first collection
        boolean modified = true;
        while (modified)
        {
            modified = false;//first assume not modified
            Iterator itr = exps.entrySet().iterator();
            while (itr.hasNext())// search each VN
            {
                Map.Entry entry = (Map.Entry) itr.next();
                char current_VN = (char)entry.getKey();
                ArrayList<ArrayList<Character>> current_exps =
                        (ArrayList<ArrayList<Character>>)entry.getValue();

                for (int i = 0; i < current_exps.size(); i++)//search each sentence of the VN
                {
                    boolean eps_ava = true;//could result in epsilon
                    ArrayList<Character> current_exp = current_exps.get(i);
                    ArrayList<Character> current_first = first.get(current_VN);
                    for (int j = 0; j < current_exp.size(); j++)//search each character of the sentence
                    {
                        if (!isVN(current_exp.get(j)))//if it is VT
                        {
                            if (!current_first.contains(current_exp.get(j)))//has not added yet
                            {
                                //add this VT to the current VN
                                current_first.add(current_exp.get(j));
                                modified = true;
                            }
                            eps_ava = false;
                            break;//find first in the current expression is over
                        }
                        else
                        {
                            char first_exp_VN = current_exp.get(j);
                            ArrayList<Character> trans_first = first.get(first_exp_VN);
                            //add all first to the current VN, epsilon is not added!
                            for (int k = 0; k < trans_first.size(); k++)
                            {
                                if (!current_first.contains(trans_first.get(k)) &&
                                        !trans_first.get(k).equals(EPSILON))//not epsilon, not already added then add
                                {
                                    current_first.add(trans_first.get(k));
                                    modified = true;
                                }
                            }
                            //if it contains epsilon, search the next character of exp
                            if (!trans_first.contains(EPSILON))
                            {
                                eps_ava = false;
                                break;
                            }
                        }
                    }
                    if (eps_ava)
                    {
                        if (!current_first.contains(EPSILON))
                        {
                            current_first.add(EPSILON);
                            modified = true;
                        }
                    }
                }
            }
        }
        //sort first collection for each VN

        Iterator itr = first.entrySet().iterator();
        while (itr.hasNext())
        {
            Map.Entry entry = (Map.Entry) itr.next();
            ArrayList<Character> foo = (ArrayList<Character>)entry.getValue();
            foo.sort(null);
        }
    }
    /*Look up first collection*/
    ArrayList<Character> getFirst(char ch)
    {
        if (isVN(ch))
        {
            if (!first.containsKey(ch))//if has been calculated
            {
                fillFirst();
            }
            return first.get(ch);
        }
        else
        {
            ArrayList<Character> foo = new ArrayList<>();
            foo.add(ch);
            return foo;
        }
    }
    /*read from string to fill expressions*/
    /*
    * format
    * like this: S->S+S
    * start with VN, then ->
    * if there's nothing behind ->, then it is ->EPSILON
    * */
    private boolean readFromStrings(ArrayList<String> strs)
    {
        for (int i = 0; i < strs.size(); i++)
        {
            String str = strs.get(i);
            ArrayList<Character> current_exp = new ArrayList<>();
            if (str.length() < 3)
            {
                return false;
            }
            if (isVN(str.charAt(0)) && str.charAt(1) == '-' && str.charAt(2) == '>')
            {
                for (int j = 3; j < str.length(); j++)
                {
                    current_exp.add(str.charAt(j));
                }
                if (str.length() == 3)
                {
                    current_exp.add(EPSILON);
                }
            }

            if (!exps.containsKey(str.charAt(0)))//if the VN has never shown up, allocate space
            {
                exps.put(str.charAt(0), new ArrayList<>());
            }
            ArrayList<ArrayList<Character>> expressions = exps.get(str.charAt(0));
            expressions.add(current_exp);
        }
        return true;
    }

    ArrayList<ArrayList<Character>> getExps(char ch)
    {
        return exps.get(ch);
    }

    void getVN_VT(ArrayList<Character> VN, ArrayList<Character> VT)
    {
        Iterator itr = exps.entrySet().iterator();
        while (itr.hasNext())// search each VN
        {
            Map.Entry entry = (Map.Entry) itr.next();
            char current_VN = (char) entry.getKey();
            ArrayList<ArrayList<Character>> current_exps =
                    (ArrayList<ArrayList<Character>>) entry.getValue();
            if (!VN.contains(current_VN))
            {
                VN.add(current_VN);
            }
            for (ArrayList<Character> exp : current_exps)
            {
                for (Character ch : exp)
                {
                    if (isVN(ch) && !VN.contains(ch))
                    {
                        VN.add(ch);
                    }
                    else if (!isVN(ch) && ch != EPSILON && !VT.contains(ch))
                    {
                        VT.add(ch);
                    }
                }
            }
        }
    }
}
