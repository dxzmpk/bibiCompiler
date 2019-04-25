package dxzmpk.impl;

public class Item {

    int productionNum;

    int pointAligin;

    public Item(int productionNum, int pointAligin) {
        this.productionNum = productionNum;
        this.pointAligin = pointAligin;
    }

    @Override
    public String toString() {
        return "Item{" +
                "productionNum=" + productionNum +
                ", pointAligin=" + pointAligin +
                '}';
    }
}
