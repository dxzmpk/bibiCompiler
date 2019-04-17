package syntacticAnalyzer.model;

import java.util.List;

/**
 * 求得闭包放在此结构内，名字暂定为 String name
 */
public class Closure {

    private String name;

    private List<FormulaItem> formulaItemList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FormulaItem> getFormulaItemList() {
        return formulaItemList;
    }

    public void setFormulaItemList(List<FormulaItem> formulaItemList) {
        this.formulaItemList = formulaItemList;
    }

}
