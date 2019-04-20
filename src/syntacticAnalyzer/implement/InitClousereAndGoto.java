package syntacticAnalyzer.implement;

import syntacticAnalyzer.model.Closure;

public class InitClousereAndGoto {

    public Closure getClousure(){
        FileProcessor fileProcessor = new FileProcessor("src/grammerParser/file/grammar.txt");
        String line = fileProcessor.getNextLine();
        return new Closure();
    }
}
