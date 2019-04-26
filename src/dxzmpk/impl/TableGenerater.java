package dxzmpk.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dxzmpk.model.Action;
import dxzmpk.model.Reduce;
import dxzmpk.model.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableGenerater {

    public static Table<Integer, String, Action> table;

    public static Table<Integer, String, Action> generateTable(){
        table = HashBasedTable.create();
        ClosureBuilder closureBuilder = new ClosureBuilder();
        ProductionProcessor productionProcessor = new ProductionProcessor();
        List<Production> productions = productionProcessor.
                readProductionFromFile("src/dxzmpk/file/grammerSample.txt");
        Set<String> symbols = ProductionProcessor.getSymbols();
        symbols.remove("");
        Item initItem = new Item(0,0);
        Closure initClosure = closureBuilder.buildClosure(initItem, productions);
        List<Closure> closures = new ArrayList<>();
        closures.add(initClosure);
        List<Closure> addPackage;
        initClosure.setStatusNum(0);
        List<Closure> closuresForIterate = new ArrayList<>();
        closuresForIterate.addAll(closures);
        int i = 1;
        while (true){
            addPackage = new ArrayList<>();
            for (Closure closure : closuresForIterate
                 ) {
                for(String symbol : symbols){
                    Closure newClosure = closureBuilder.gotoFunction(closure, symbol, productions);
                    if(newClosure == null){
                        continue;
                    }
                    for(Closure closure1 : closures){
                        if(newClosure.equals(closure1)){
                            closure1.getStatusNum();
                            table.put(closure.getStatusNum(),symbol,new Shift(closure1.getStatusNum()));
                        }
                    }
                    newClosure.setStatusNum(i);
                    i++;
                    table.put(closure.getStatusNum(),symbol,new Shift(newClosure.getStatusNum()));
                    addPackage.add(newClosure);
                    Item[] completeItems = closure.isComplete();
                    for(Item item: completeItems){
                        List<String> follows = ProductionProcessor.follows.get(productions.get(item.productionNum).left);
                        for(String follow :follows){
                            table.put(closure.getStatusNum(),follow,new Reduce(productions.get(item.productionNum)));
                        }
                    }
                }

            }
            //没有新的添加进来，表示分析完成
            if(addPackage.size() == 0){
                break;
            } else {
                closuresForIterate.removeAll(closures);
                closures.addAll(addPackage);
                closuresForIterate.addAll(addPackage);
            }
        }
        return table;
    }
}
