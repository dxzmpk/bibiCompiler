package dxzmpk.impl;

import dxzmpk.inter.ProductionInterface;

import java.util.Arrays;

public class Production implements ProductionInterface {

    public String left;

    public String[] right;

    int num;

    public Production(String left, String[] right, int num) {
        this.left = left;
        this.right = right;
        this.num = num;
    }

    @Override
    public String getNextSymbol(int pointAligin) {
        if(pointAligin >= right.length){
            return null;
        }
        return right[pointAligin];
    }

    @Override
    public String toString() {
        return "Production{" +
                "left='" + left + '\'' +
                ", right=" + Arrays.toString(right) +
                ", statusNum=" + num +
                '}';
    }
}
