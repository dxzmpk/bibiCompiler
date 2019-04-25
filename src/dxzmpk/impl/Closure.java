package dxzmpk.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Closure {

    List<Item> items;

    boolean[] mark;

    public Closure() {

        List<Item> items = new ArrayList<>();
        this.items = items;

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

    @Override
    public String toString() {
        return "Closure{" +
                "items=" + items +
                ", mark=" + Arrays.toString(mark) +
                '}';
    }
}
