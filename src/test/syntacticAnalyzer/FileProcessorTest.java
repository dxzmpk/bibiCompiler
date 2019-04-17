package test.syntacticAnalyzer;

import org.junit.Test;
import syntacticAnalyzer.implement.FileProcessor;
import test.BaseTest;

public class FileProcessorTest extends BaseTest {


    @Test
    public void getNextLine(){
        FileProcessor fileProcessor = new FileProcessor("src/test/syntacticAnalyzer/file/file_processor.txt");
        String line = fileProcessor.getNextLine();
        String line1 = fileProcessor.getNextLine();
        System.out.println(line);
        System.out.println(line1);
    }
}
