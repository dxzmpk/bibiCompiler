package dxzmpk.impl;

import java.io.*;
import java.util.*;

public class ProductionProcessor {

    static List<Production> productions;

    static Set<String> nonTerminals;

    static Set<String> symbols;

    static Set<String> terminals;

    static HashMap<String, ArrayList<String>> firsts;

    static HashMap<String, ArrayList<String>> follows;

    static int productionNum;

    public static List<Production> readProductionFromFile(String fileName) {

        productions = new ArrayList<>();

        nonTerminals = new HashSet<>();

        symbols = new HashSet<>();

        terminals = new HashSet<>();

        firsts = new HashMap<>();

        follows = new HashMap<>();

        try {
            File file = new File(fileName);
            Reader grammerReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(grammerReader);
            String readLine = "";
            Production production;
            int i = 0;
            while ((readLine = bufferedReader.readLine())!= null){
                String left = readLine.split("->")[0].trim();
                String[] right = readLine.split("->")[1].split(" ");
                nonTerminals.add(left);
                symbols.add(left);
                symbols.addAll(Arrays.asList(right));
                production = new Production(left,right,i);
                i++;
                productions.add(production);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFollows();
        getFirsts();
        productionNum = productions.size();
        return productions;
    }


    public static boolean isNonTermical(String a){
        return nonTerminals.contains(a);
    }

    public static List<Production> headLikeSymbol(String symbol){
        List<Production> productionList = new ArrayList<>();
        for (Production production: productions
             ) {
            if (production.left.equals(symbol)){
                productionList.add(production);
            }
        }
        return productionList;
    }

    public static void getFirsts(){
        Iterator iterator = terminals.iterator();
        Iterator nonTermicals = nonTerminals.iterator();
        ArrayList<String> first;
        while (iterator.hasNext()){
            first = new ArrayList<String>();
            String termical = (String) iterator.next();
            first.add(termical);
            firsts.put(termical, first);
        }

        //注册非终结符,注册很形象
        while (nonTermicals.hasNext()) {
            first = new ArrayList<>();
            firsts.put((String) nonTermicals.next(), first);
        }

        boolean flag;
        while (true) {
            flag = true;
            String left;
            String right;
            String[] rights;
            for(int i = 0; i< productions.size();i++){
                left = productions.get(i).left;
                rights = productions.get(i).right;
                for(int j = 0; j < rights.length; j++){
                    right = rights[j];
                    // right为空怎么办
                    if(!right.equals("$")){
                        for(int l = 0; l < firsts.get(right).size();l++){
                            if(firsts.get(left).contains(firsts.get(right).get(l))){
                                continue;
                            } else {
                                firsts.get(left).add(firsts.get(right).get(l));
                                flag = false;
                            }
                        }
                    }
                    if (isCanBeNull(right)) {
                        continue;
                    }
                    else {
                        break;
                    }
                }
            }
            if(flag){
                break;
            }
        }
    }

    public static boolean isCanBeNull(String symbol){
        String[] rights;
        for(int i = 0; i < productions.size(); i++){
            if(productions.get(i).left.equals(symbol)) {
                rights = productions.get(i).right;
                if(rights[0].equals("$")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFollows(){
        ArrayList<String> follow;
        Iterator nonTermicals = nonTerminals.iterator();
        //注册非终结符,注册很形象
        while (nonTermicals.hasNext()) {
            follow = new ArrayList<>();
            firsts.put((String) nonTermicals.next(), follow);
        }
        //将#加入到follow(S)中
        follows.get("E").add("#");
        boolean flag;
        boolean fab;
        while (true) {
            flag = true;
            //循环
            for(int i = 0; i < productions.size(); i++){
                String left;
                String right;
                String[] rights;
                rights = productions.get(i).right;
                for(int j = 0; j < rights.length; j++){
                    right = rights[j];

                    //非终结符
                    if(isNonTermical(right)){
                        fab = true;
                        for(int k = j+1; k<rights.length;k++){

                            //查找first集
                            for(int v = 0; v<firsts.get(rights[k]).size();v++){
                                if(follows.get(right).contains(firsts.get(rights[k]).get(v))){
                                    continue;
                                } else {
                                    follows.get(right).add(firsts.get(rights[k]).get(v));
                                    flag = false;
                                }
                            }
                            if (isCanBeNull(rights[k])) {
                                continue;
                            }
                            else {
                                fab = false;
                                break;
                            }
                        }
                        if (fab) {
                            left = productions.get(i).left;
                            for(int p = 0; p < follows.get(left).size();p++){
                                if(follows.get(right).contains(follows.get(left).get(p))){
                                    continue;
                                }
                                else {
                                    follows.get(right).add(follows.get(left).get(p));
                                    flag = false;
                                }

                            }
                        }
                    }

                }
            }
            if(flag == true){
                break;
            }
        }
        String left;
        Iterator nonIterator = nonTerminals.iterator();
        while (nonIterator.hasNext()){
            left =(String) nonIterator.next();
            for (int v=0; v<follows.get(left).size(); v++) {
                if(follows.get(left).get(v).equals("#"))
                    follows.get(left).remove(v);
            }
        }
    }

    public static List<Production> getProductions() {
        return productions;
    }

    public static Set<String> getNonTerminals() {
        return nonTerminals;
    }

    public static Set<String> getSymbols() {
        return symbols;
    }

    public static Set<String> getTerminals() {
        return terminals;
    }

    public static int getProductionNum() {
        return productionNum;
    }
}
