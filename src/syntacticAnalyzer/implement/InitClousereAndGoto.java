package syntacticAnalyzer.implement;

import syntacticAnalyzer.model.Closure;
import syntacticAnalyzer.model.FormulaItem;

import java.util.ArrayList;
import java.util.List;

public class InitClousereAndGoto {

    public Closure getClousure(){
        List<FormulaItem> formulaItems = new ArrayList<>();
        FileProcessor fileProcessor = new FileProcessor("src/grammerParser/file/grammar.txt");
        FileProcessor fileProcessor1 = new FileProcessor("src/syntacticAnalyzer/file/goto.txt");
        String line = " ";
        FormulaItem formulaItem;
        Closure closure = new Closure();
        while ((line = fileProcessor.getNextLine())!=null){
            String left = line.split("->")[0].trim();
            String right = line.split("->")[1].trim();
            formulaItem = new FormulaItem(0,left,right);
            FormulaItem formulaItem1 = formulaItem.nextFormula();
            System.out.println(formulaItem.toString());
            formulaItems.add(formulaItem);
        }
        closure.setFormulaItemList(formulaItems);
        return closure;
    }

    public Closure GOTO(){
        Closure closure = new Closure();
        List<FormulaItem> formulaItems = closure.getFormulaItemList();
        for (FormulaItem formulaItem : formulaItems
             ) {
            char c = formulaItem.hasNext();
            String from = formulaItem.getReap().substring(0,formulaItem.getPointAlign());
            String to = formulaItem.getReap().substring(0,formulaItem.getPointAlign()+1);
            closure.getFormulaItemList().add(formulaItem);
        }
        return closure;
    }



}
