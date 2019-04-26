package dxzmpk.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Closure {

    int statusNum;

    List<Item> items;

    boolean[] mark;

    public Closure() {

        List<Item> items = new ArrayList<>();
        this.items = items;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Closure closure = (Closure) o;
        return Arrays.equals(mark, closure.mark);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(statusNum, items);
        result = 31 * result + Arrays.hashCode(mark);
        return result;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(int statusNum) {
        this.statusNum = statusNum;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean[] getMark() {
        return mark;
    }

    public void setMark(boolean[] mark) {
        this.mark = mark;
    }

    public Item[] isComplete(){
        Item[] result = new Item[items.size()];
        int i = 0;
        for(Item item : items){
            if(ProductionProcessor.productions.get(item.productionNum).right.length == item.pointAligin){
                result[i] = item;
                i++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Closure{" +
                "items=" + items +
                ", mark=" + Arrays.toString(mark) +
                '}';
    }
}
