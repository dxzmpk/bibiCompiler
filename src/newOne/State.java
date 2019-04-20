package newOne;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Satori on 2016/2/18.
 */
public class State
{
    private int sta_id;
    private ArrayList<Sentence> sentences;
    Symbols sym;

    State(int id, ArrayList<Sentence> stc, Symbols symbols)
    {
        sta_id = id;
        sentences = stc;
        sym = symbols;

        //fill all the sentence
        Queue<Sentence> que = new LinkedList<>();

        for (Sentence s : sentences)
        {
            if (!s.isReduce() && Symbols.isVN(s.dotChar()))
            {
                que.offer(s);
            }
        }

        while (!que.isEmpty())
        {
            Sentence current_stc = que.poll();
            char current_VN = current_stc.dotChar();
            ArrayList<ArrayList<Character>> exps = sym.getExps(current_VN);
            for (ArrayList<Character> exp : exps)
            {
                ArrayList<Character> seek = current_stc.getNextSeek();
                ArrayList<Character> newSeek = new ArrayList<>();
                for (Character ch : seek)//deep copy
                {
                    newSeek.add(ch);
                }
                Sentence s = new Sentence(current_VN, exp, sym, newSeek);
                //search to find whether s is already in sentences
                boolean hasSame = false;
                for (Sentence ori : sentences)
                {
                    if (ori.contains(s))
                    {
                        hasSame = true;
                    }
                }
                if (!hasSame)
                {
                    boolean flag = false;
                    for (Sentence ori : sentences)
                    {
                        if (ori.mergeSeek(s))
                        {
                            flag = true;
                        }
                    }
                    if (!flag)//not existed at all
                    {
                        sentences.add(s);
                    }
                    //if the new exp's first character is VN, too
                    if (Symbols.isVN(s.dotChar()))
                    {
                        que.offer(s);
                    }
                }
            }
        }
    }

    int getSta_id()
    {
        return sta_id;
    }

    ArrayList<Sentence> getSentences()
    {
        return sentences;
    }

    boolean sentence_equals(State s)
    {
        for (int i = 0; i < sentences.size(); i++)
        {
            boolean find = false;
            for (int j = 0; j < s.sentences.size(); j++)
            {
                if (sentences.get(i).equals(s.sentences.get(j)))
                {
                    find = true;
                    break;
                }
            }
            if (!find)
            {
                return false;
            }
        }
        for (int i = 0; i < s.sentences.size(); i++)
        {
            boolean find = false;
            for (int j = 0; j < sentences.size(); j++)
            {
                if (s.sentences.get(i).equals(sentences.get(j)))
                {
                    find = true;
                }
            }
            if (!find)
            {
                return false;
            }
        }
        return true;
    }

}
