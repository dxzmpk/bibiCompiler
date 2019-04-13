package test.lexicalPraser;

import lexicalPraser.implement.Praser;
import org.junit.Test;
import test.BaseTest;

public class PraserTest extends BaseTest {

    Praser praser = new Praser();

    @Test
    public void praseByFileNameTest(){
        String fileName = "src/test/lexicalPraser/file/file_processor.txt";
        praser.praseByFileName(fileName," ");
    }
}
