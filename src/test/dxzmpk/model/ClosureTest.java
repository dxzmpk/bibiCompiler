package test.dxzmpk.model;


import dxzmpk.impl.*;
import org.junit.Test;
import test.BaseTest;

import java.util.List;

public class ClosureTest extends BaseTest {

    //求解所给例子文法中闭包计算正确性
    @Test
    public void getClousure(){
        ClosureBuilder closureBuilder = new ClosureBuilder();
        Item item = new Item(0,0);
        ProductionProcessor productionProcessor = new ProductionProcessor();
        List<Production> productions = productionProcessor.readProductionFromFile("src/dxzmpk/file/grammerSample.txt");
        Closure closure1 = closureBuilder.buildClosure(item,productions);
        System.out.println(closure1.getItems().size());
        System.out.println(closure1.getItems().toString());
        System.out.println(productions.toString());
    }

    @Test
    public void gotoFunctionTest(){
        ClosureBuilder closureBuilder = new ClosureBuilder();
        Item item = new Item(0,0);
        ProductionProcessor productionProcessor = new ProductionProcessor();
        List<Production> productions = productionProcessor.readProductionFromFile("src/dxzmpk/file/grammerSample.txt");
        Closure closure1 = closureBuilder.buildClosure(item,productions);
        Closure closure = closureBuilder.gotoFunction(closure1,"(",productions);
        System.out.println(closure.toString());
        System.out.println(closure.getItems().size());
        System.out.println("************************************");
        System.out.println(closure1.getItems().size());
        System.out.println(closure1.getItems().toString());
        System.out.println(productions.toString());
    }
}
