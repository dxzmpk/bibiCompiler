package test.lexicalPraser;

import lexicalPraser.implement.FileProcessor;
import test.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileProcessorTest extends BaseTest {


    @Test
    public void getNextCharacterTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/file_processor.txt");
        char c = fileProcessor.getNextCharacter();
        String result = "" + c;
        System.out.println(result);
    }

    @Test
    public void pushBackLastCharacter(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/file_processor.txt");
        char c = fileProcessor.getNextCharacter();
        char c1 = fileProcessor.getNextCharacter();
        char c2 = fileProcessor.getNextCharacter();
        fileProcessor.pushBackLastCharacter();
        char c3 = fileProcessor.getNextCharacter();
        System.out.println("字符依次为 ：" + c + " " + c1 + " " + c2 + " " + c3);
    }
}
