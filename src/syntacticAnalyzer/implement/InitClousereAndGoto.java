package syntacticAnalyzer.implement;

import syntacticAnalyzer.model.Closure;
import syntacticAnalyzer.model.FormulaItem;

import java.util.ArrayList;
import java.util.List;

public class InitClousereAndGoto {

    public Closure getClousure(){
        List<FormulaItem> formulaItems = new ArrayList<>();
        FileProcessor fileProcessor = new FileProcessor("src/grammerParser/file/grammar.txt");
        String line = " ";
        FormulaItem formulaItem;
        while ((line = fileProcessor.getNextLine())!=null){
            String left = line.split("->")[0].trim();
            String right = line.split("->")[1].trim();
            formulaItem = new FormulaItem(0,left,right);
            System.out.println(formulaItem.toString());
            formulaItems.add(formulaItem);
        }
        Closure closure = new Closure();
        closure.setFormulaItemList(formulaItems);
        return closure;
    }



}
