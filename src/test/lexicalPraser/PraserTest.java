package test.lexicalPraser;

import lexicalPraser.implement.Lexer;
import lexicalPraser.model.TokenItem;
import org.junit.Test;
import test.BaseTest;

import java.util.List;

public class PraserTest extends BaseTest {

    Lexer praser = new Lexer();

    @Test
    public void praseByFileNameTest(){
        String fileName = "src/test/lexicalPraser/file/file_processor.txt";
        List<TokenItem> tokenItems = praser.lexByFileName(fileName," ");
        System.out.println(tokenItems);
    }
}
