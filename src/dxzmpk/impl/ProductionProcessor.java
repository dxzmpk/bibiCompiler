package dxzmpk.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductionProcessor {

    static List<Production> productions;

    static Set<String> nonTerminals;

    static Set<String> terminals;

    static int productionNum;

    public List<Production> readProductionFromFile(String fileName) {

        productions = new ArrayList<>();

        nonTerminals = new HashSet<>();


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
                production = new Production(left,right,i);
                i++;
                productions.add(production);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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


}
