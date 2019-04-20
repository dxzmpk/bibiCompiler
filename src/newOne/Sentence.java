package newOne;

import java.util.ArrayList;

/**
 * Created by Satori on 2016/2/17.
 * Attention
 * 1.getNextSeek only can be called when the current character is VN
 */
public class Sentence
{
    private char left;
    private ArrayList<Character> right;
    private int dot_pos;
    private ArrayList<Character> seek;

    Symbols symbols;

    Sentence(char l, ArrayList<Character> r, Symbols sym)
    {
        left = l;
        right = r;
        dot_pos = 0;
        seek = new ArrayList<>();
        symbols = sym;
    }

    Sentence(char l, ArrayList<Character> r, Symbols sym, ArrayList<Character> seeks)
    {
        left = l;
        right = r;
        dot_pos = 0;
        seek = seeks;
        symbols = sym;
    }

    char getVN()
    {
        return left;
    }

    //judge the current sentence has the same exp with s
    boolean contains(Sentence s)
    {
        if (left != s.left)
        {
            return false;
        }
        if (right.size() != s.right.size())
        {
            return false;
        }
        for (int i = 0; i < right.size(); i++)
        {
            if (!right.get(i).equals(s.right.get(i)))
            {
                return false;
            }
        }
        if (dot_pos != s.dot_pos)
        {
            return false;
        }
        return seek.containsAll(s.seek);
    }

    //Only be called when the current character is VN
    ArrayList<Character> getNextSeek()
    {
        if (dot_pos == right.size() - 1)// dot reaches the last VN
        {
            return seek;
        }
        else
        {
            ArrayList<Character> nextSeek = new ArrayList<>();
            for (int i = dot_pos + 1; i < right.size(); i++)//consider that the next VN can result in EPSILON
            {
                ArrayList<Character> cur_chs = symbols.getFirst(right.get(i));
                for (Character ch : cur_chs)
                {
                    if (!ch.equals(Symbols.EPSILON) && !nextSeek.contains(ch))
                    {
                        nextSeek.add(ch);
                    }
                }
                if (!cur_chs.contains(Symbols.EPSILON))
                {
                    return nextSeek;//return
                }
            }
            //all VN can result in EPSILON
            for (Character ch : seek)
            {
                if (!nextSeek.contains(ch))
                {
                    nextSeek.add(ch);
                }
            }
            return nextSeek;
        }
    }

    char dotChar()
    {
        return right.get(dot_pos);
    }

    boolean isReduce()
    {
        if (right.size() == 1 && right.get(0).equals(Symbols.EPSILON))//has epsilon
        {
            return true;
        }
        else if (dot_pos == right.size())
        {
            return true;
        }
        return false;
    }

    Sentence getNextSentence()
    {
        if (isReduce())
        {
            return null;
        }
        Sentence s = new Sentence(left, right, symbols, seek);
        s.dot_pos = dot_pos + 1;//the dotpos increase by 1
        return s;
    }
    //if sentence s has same the right part, merge the seek
    boolean mergeSeek(Sentence s)
    {
        boolean flag = false;
        if (s.left == left && s.right.equals(right) && s.dot_pos == dot_pos)
        {
            ArrayList<Character> seeks = s.seek;
            for (Character ch : seeks)
            {
                if (!seek.contains(ch))
                {
                    seek.add(ch);
                    flag = true;
                }
            }
        }
        return flag;
    }

    boolean equals(Sentence s)
    {
        if (left != s.left)
        {
            return false;
        }
        if (!(right.size() == s.right.size()))
        {
            return false;
        }
        for (int i = 0; i < right.size(); i++)
        {
            if (!right.get(i).equals(s.right.get(i)))
            {
                return false;
            }
        }
        if (dot_pos != s.dot_pos)
        {
            return false;
        }
        ArrayList<Character> seekA = new ArrayList<>();
        ArrayList<Character> seekB = new ArrayList<>();
        for (Character ch : seek)
        {
            seekA.add(ch);
        }
        for (Character ch : s.seek)
        {
            seekB.add(ch);
        }
        seekA.sort(null);
        seekB.sort(null);
        return seekA.equals(seekB);
    }

    ArrayList<Character> getRight()
    {
        return right;
    }

    ArrayList<Character> getSeek()
    {
        return seek;
    }
}
