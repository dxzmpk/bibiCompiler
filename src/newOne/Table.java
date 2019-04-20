package newOne;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Satori on 2016/2/20.
 */
public class Table
{
    private final char analyze_start = (char)-2;//extended grammar start
    Symbols sym_table;
    ArrayList<Action[]> action_table = new ArrayList<>();
    ArrayList<Integer[]> goto_table = new ArrayList<>();
    char grammar_start;//the beginning character of the grammar
    ArrayList<Character> VN = new ArrayList<>();
    ArrayList<Character> VT = new ArrayList<>();
    ArrayList<State> states = new ArrayList<>();
    ArrayList<Conflict> conflicts = new ArrayList<>();

    Table(char start_ch, ArrayList<String> strs)//unchecked strings
    {
        //initial symbols
        sym_table = new Symbols(strs);
        grammar_start = start_ch;

        //initial action_table and goto_table
        //first count the number of VN and VT(including EOF, excluding EPSILON)
        sym_table.getVN_VT(VN, VT);
        VT.add(Symbols.EOF);//add '$'
        //second allocate space

        /******************Fill the states***********************/
        //distribute id for each state, start with 0, increase when added to states
        int id = 0;
        Queue<State> que = new LinkedList<>();//BFS

        //first the initial state
        ArrayList<Character> right0 = new ArrayList<>();
        right0.add(grammar_start);
        ArrayList<Character> seek0 = new ArrayList<>();
        seek0.add(Symbols.EOF);
        Sentence s0 = new Sentence(analyze_start, right0, sym_table, seek0);
        ArrayList<Sentence> S0 = new ArrayList<>();
        S0.add(s0);
        State ini_sta = new State(id, S0, sym_table);
        states.add(ini_sta);
        id++;
        que.offer(ini_sta);

        //deal with all states
        while (!que.isEmpty())
        {
            State curret_state = que.poll();
            ArrayList<Sentence> current_stcs = curret_state.getSentences();
            boolean[] hasDealt = new boolean[current_stcs.size()];
            //initial hasDealt form
            for (int i = 0 ; i < current_stcs.size(); i++)
            {
                if (current_stcs.get(i).isReduce())//if it is a reduce item, no need to deal with
                {
                    hasDealt[i] = true;
                }
                else
                {
                    hasDealt[i] = false;
                }
            }

            //allocate sta_act and sta_goto form, and initialize
            Action[] cur_sta_act = new Action[VT.size()];
            Integer[] cur_sta_goto = new Integer[VN.size()];
            for (Action act : cur_sta_act)
            {
                act = null;
            }
            for (Integer go : cur_sta_goto)
            {
                go = null;//invalid is null
            }

            //deal with all sentences that are not reduce items
            for (int i = 0 ; i < current_stcs.size(); i++)
            {
                if (!hasDealt[i])
                {
                    hasDealt[i] = true;
                    Sentence current_stc = current_stcs.get(i);

                    ArrayList<Sentence> sameDotChar = new ArrayList<>();
                    //add next sentence
                    sameDotChar.add(current_stc.getNextSentence());

                    char current_char = current_stc.dotChar();
                    //then collect all sentences that contains the same dot_char
                    for (int j = i + 1; j < current_stcs.size(); j++)
                    {
                        Sentence cmp = current_stcs.get(j);
                        if (!hasDealt[j] && cmp.dotChar() == current_char)
                        {
                            sameDotChar.add(current_stcs.get(j).getNextSentence());//add to list
                            hasDealt[j] = true;
                        }
                    }
                    //generate next State
                    State nextState = new State(id, sameDotChar, sym_table);

                    /*judge Whether the state is in state collection*/
                    boolean hasExisted  = false;
                    for (int k = 0; k < states.size(); k++)
                    {
                        if (states.get(k).sentence_equals(nextState))
                        {
                            /*renew shift form or goto form*/
                            addToShiftGoto(current_char, states.get(k).getSta_id(),
                                    cur_sta_act, cur_sta_goto);
                            hasExisted = true;
                            break;
                        }
                    }

                    /*if it is a new state,
                    add to the state collection
                    add the state to the que
                    fill goto or shift form*/
                    if (!hasExisted)
                    {
                        states.add(nextState);
                        id++;
                        que.offer(nextState);
                        addToShiftGoto(current_char, nextState.getSta_id(),
                                cur_sta_act, cur_sta_goto);
                    }
                }
            }

            //add to form
            action_table.add(cur_sta_act);
            goto_table.add(cur_sta_goto);
        }
        /*Then fill all the reduce form in each state*/
        for (State state : states)
        {
            ArrayList<Sentence> sentences = state.getSentences();
            for (Sentence sentence : sentences)
            {
                if (sentence.isReduce())
                {
                    int reduce_cnt;
                    String exp;
                    ArrayList<Character> chars = sentence.getRight();
                    if (chars.contains(Symbols.EPSILON))
                    {
                        reduce_cnt = 0;
                        exp = String.valueOf(sentence.getVN()) + "->";
                    }
                    else
                    {
                        reduce_cnt = chars.size();
                        exp = String.valueOf(sentence.getVN()) + "->";
                        for (Character ch : chars)
                        {
                            exp += ch;
                        }
                    }

                    Action action;
                    if (sentence.getVN() == analyze_start)//accepted
                    {
                        action = new Accepted();
                    }
                    else//just a normal reduce item
                    {
                        action = new Reduce(reduce_cnt, sentence.getVN(), exp);
                    }

                    int row = state.getSta_id();
                    Action[] act = action_table.get(row);
                    ArrayList<Character> seek = sentence.getSeek();
                    for (Character ch : seek)
                    {
                        int col = getActionCol(ch);
                        if (act[col] != null)//if conflicts occurs
                        {
                            addConflict(row, col, action);
                            act[col] = null;//not decided yet
                        }
                        else
                        {
                            act[col] = action;
                        }
                    }
                }
            }
        }
    }

    int getActionCol(char ch)
    {
        for(int i = 0; i < VT.size(); i++)
        {
            if (VT.get(i).equals(ch))
            {
                return i;
            }
        }
        return  -1;
    }

    int getGotoCol(char ch)
    {
        for(int i = 0; i < VN.size(); i++)
        {
            if (VN.get(i).equals(ch))
            {
                return i;
            }
        }
        return -1;
    }

    void addToShiftGoto(char ch, int next_state_id, Action[] act, Integer[] go)
    {
        if (Symbols.isVN(ch))
        {
            go[getGotoCol(ch)] = next_state_id;
        }
        else
        {
            act[getActionCol(ch)] = new Shift(next_state_id);
        }
    }

    void addConflict(int row, int col, Action act)
    {
        for (Conflict conflict : conflicts)
        {
            if (conflict.getRow() == row && conflict.getCol() == col)//has existed
            {
                conflict.getActions().add(act);
                return;
            }
        }
        //not existed yet, new
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(act);
        actions.add(action_table.get(row)[col]);//the origin one should be included
        Conflict conflict = new Conflict(row, col, actions);
        conflicts.add(conflict);
    }

    ArrayList<Conflict> getConflicts()
    {
        return conflicts;
    }

    void dealConflicts(ArrayList<Integer> indexes, ArrayList<Integer> choices)
    {
        for (Integer index : indexes)
        {
            action_table.get(conflicts.get(index).getRow())[conflicts.get(index).getCol()] =
                    conflicts.get(index).getActions().get(choices.get(index));
        }
    }

    void printForm()
    {
        PrintInForm printer = new PrintInForm();
        printer.print("");
        for (Character ch : VT)
        {
            if (ch.equals(Symbols.EOF))
            {
                printer.print("EOF");
            }
            else
            {
                printer.print(String.valueOf(ch));
            }
        }
        for (Character ch : VN)
        {
            printer.print(String.valueOf(ch));
        }
        System.out.println();

        for (int i = 0; i < action_table.size(); i++)
        {
            printer.print(String.valueOf(i));
            Action[] actions = action_table.get(i);
            for (Action action : actions)
            {
                if (action == null)
                {
                    printer.print("");
                }
                else
                {
                    if (action.getType().equals(Action.Type.Shift))
                    {
                        printer.print("s" + String.valueOf(((Shift)action).getNextState()));
                    }
                    else if (action.getType().equals(Action.Type.Reduce))
                    {
                        printer.print(((Reduce)action).getExp());
                    }
                    else
                    {
                        printer.print("ACC");
                    }
                }
            }

            Integer[] gotos = goto_table.get(i);
            for (Integer go : gotos)
            {
                if (go == null)
                {
                    printer.print("");
                }
                else
                {
                    printer.print(String.valueOf(go));
                }
            }
            System.out.println();
        }
    }

    void printConflicts()
    {
        if (!conflicts.isEmpty())
        {
            System.out.println("Conflicts exist");
            for (Conflict conflict : conflicts)
            {
                Character current_VT = VT.get(conflict.getCol());
                String foo = current_VT.equals(Symbols.EOF) ?
                        "EOF" : String.valueOf(current_VT);
                System.out.println("In state " + conflict.getRow() +
                        " character " + foo);
                ArrayList<Action> conflict_actions = conflict.getActions();
                for (Action act : conflict_actions)
                {
                    if (act.getType().equals(Action.Type.Acc))
                    {
                        System.out.println("Accepted");
                    }
                    else if (act.getType().equals(Action.Type.Shift))
                    {
                        System.out.println("Shift " + ((Shift)act).getNextState());
                    }
                    else
                    {
                        System.out.println("Reduce by " + ((Reduce)act).getExp());
                    }
                }
            }
        }
    }

}
