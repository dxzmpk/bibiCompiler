package lexicalPraser.test;

import lexicalPraser.implement.FileProcessor;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessorTest extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(FileProcessorTest.class.getName());


    @Test
    public void getNextCharacterTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/lexicalPraser/test/file_processor.txt");
        char c = fileProcessor.getNextCharacter();
        String result = "" + c;
        LOGGER.info(result);
    }

    @Test
    public void pushBackLastCharacter(){
        FileProcessor fileProcessor =
                new FileProcessor("src/lexicalPraser/test/file_processor.txt");
        char c = fileProcessor.getNextCharacter();
        char c1 = fileProcessor.getNextCharacter();
        char c2 = fileProcessor.getNextCharacter();
        fileProcessor.pushBackLastCharacter();
        char c3 = fileProcessor.getNextCharacter();
        LOGGER.info("字符依次为 ：" + c + " " + c1 + " " + c2 + " " + c3);
    }
}
