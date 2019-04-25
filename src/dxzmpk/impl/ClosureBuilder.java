package dxzmpk.impl;

import dxzmpk.inter.ClosureInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosureBuilder implements ClosureInterface {


    @Override
    public Closure closureBuilder(Item item, List<Production> productions) {

        Closure closure = new Closure();

        List<Item> items = closure.items;

        //初始化所有mark未false，表示没有item被加入到itemList中
        boolean[] mark;
        mark = new boolean[productions.size()];
        Arrays.fill(mark,false);
        items.add(item);
        //必须先将要添加的产生式打包起来然后统一添加，否则会出现java.util.ConcurrentModificationException
        List<Item> addPackge;
        List<Item> itemsForIterate = new ArrayList<>();
        itemsForIterate.addAll(items);
        Item itemToBeDeleted = null;
        while (true){

            addPackge = new ArrayList<>();

            for(Item item1 : itemsForIterate){
                Production production = productions.get(item1.productionNum);
                String nextSymbol = production.getNextSymbol(item1.pointAligin+1);
                //记录本次遍历的item，下次遍历前在itemsForIterate中删除
                itemToBeDeleted = item1;
                //说明遍历到了最后
                if(nextSymbol == null){
                    break;
                }
                for(Production production1 : ProductionProcessor.headLikeSymbol(nextSymbol)){
                    if (mark[production1.num] == false){
                        Item newItem = new Item(production1.num,0);
                        addPackge.add(newItem);
                        mark[production1.num] = true;
                    }
                }
            }


            if(addPackge.size() == 0 ){
                break;
            } else {
                itemsForIterate.addAll(addPackge);
                itemsForIterate.remove(itemToBeDeleted);
                items.addAll(addPackge);
            }
        }
        closure.mark = mark;
        return closure;
    }

    @Override
    public Closure gotoFunction(Closure closure, String symbol, List<Production> productions) {
        Closure toClosure = new Closure();
        List<Item> items = new ArrayList<>();
        Item newItem = null;
        for (Item item: closure.getItems()
             ) {
            String nextSymbol = productions.get(item.productionNum).getNextSymbol(item.pointAligin + 1);
            if (nextSymbol != null && nextSymbol.equals(symbol)) {
                 newItem = new Item(item.productionNum, item.pointAligin + 1);
                 items.add(newItem);
            }
        }
        toClosure = closureBuilder(newItem,productions);
        for (Item item : items
             ) {
            if(!toClosure.getItems().contains(item)){
                toClosure.getItems().add(item);
            }
        }
        return toClosure;
    }
}
