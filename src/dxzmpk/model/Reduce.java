package dxzmpk.model;

import dxzmpk.impl.Production;

public class Reduce extends Action{

    Production production;

    public Reduce(Production production) {
        this.production = production;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public int getInt() {
        return production.right.length;
    }
}
