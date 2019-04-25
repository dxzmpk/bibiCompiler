package dxzmpk.inter;

import dxzmpk.impl.Production;

public interface ProductionListInterface {

    public void readProductionFromFile(String fileName);

    public Production getProductionByNum(int i);


}
