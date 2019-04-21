package syntacticAnalyzer.LrOne;

import grammerParser.FrontAndFollowList;
import grammerParser.Production;

import java.util.ArrayList;
import java.util.HashMap;

public class FollowProducer {

    // 成员变量,产生式集，终结符集，非终结符集
    ArrayList<Production> productions;
    ArrayList<String> terminals;
    ArrayList<String> nonterminals;
    HashMap<String, ArrayList<String>> assists;
    HashMap<String, ArrayList<String>> follows;
    public static void main(String[] args){
        FrontAndFollowList f = new FrontAndFollowList();
        f.Predict();
    }

    public FollowProducer(){
        productions = new ArrayList<Production>();
        terminals = new ArrayList<String>();
        nonterminals = new ArrayList<String>();
        assists = new HashMap<String, ArrayList<String>>();
        follows = new HashMap<String, ArrayList<String>>();

    }

    public void getFollow(){
        // 所有非终结符的follow集初始化一下
        ArrayList<String> follow;
        for (int i = 0; i < nonterminals.size(); i++) {
            follow = new ArrayList<String>();
            follows.put(nonterminals.get(i), follow);
            int c;
        }
        // 将#加入到follow(S)中
        follows.get("S").add("#");
        boolean flag;
        boolean fab;
        while (true) {
            flag = true;
            int a;
            // 循环
            for (int i = 0; i < productions.size(); i++) {
                String left;
                String right;
                String[] rights;
                rights = productions.get(i).returnRights();
                for (int j = 0; j < rights.length; j++) {
                    right = rights[j];

                    // 非终结符
                    if (nonterminals.contains(right)) {
                        fab = true;
                        for(int k=j+1;k<rights.length;k++){
                            int b;
                            int opk;
                            // 查找first集
                            for(int v = 0; v< assists.get(rights[k]).size(); v++){
                                // 将后一个元素的first集加入到前一个元素的follow集中
                                if(follows.get(right).contains(assists.get(rights[k]).get(v))){
                                    continue;
                                }
                                else {
                                    follows.get(right).add(assists.get(rights[k]).get(v));
                                    flag=false;
                                }
                            }
                        }
                        if(fab){
                            left = productions.get(i).returnLeft();
                            for (int p = 0; p < follows.get(left).size(); p++) {
                                if (follows.get(right).contains(follows.get(left).get(p))) {
                                    int c;int ko;
                                }
                                    continue;
                                }
                            }
                        }
                    }

                }
            }
        }

    }
