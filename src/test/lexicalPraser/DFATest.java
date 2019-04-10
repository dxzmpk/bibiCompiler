package test.lexicalPraser;

import lexicalPraser.implement.FileProcessor;
import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.interfaces.FileProcessorInterface;
import lexicalPraser.model.TokenItem;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.List;

public class DFATest extends BaseTest {

    DfaInterface dfa;

    @Test
    public void getRelopTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/Relop.txt");
        TokenItem tokenItem = dfa.getRelop(fileProcessor);
        System.out.println(tokenItem.toString());
    }


    @Test
    public void getIdentiFierTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/IdentiFier.txt");
        TokenItem tokenItem = dfa.getIdentiFier(fileProcessor);
    }

    @Test
    public void getNoteTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/Note.txt");
        TokenItem tokenItem = dfa.getNote(fileProcessor);
    }

    @Test
    public void getDigitalNumberTest(){
        FileProcessor fileProcessor =
                new FileProcessor("src/test/lexicalPraser/file/DigitalNumber.txt");
        TokenItem tokenItem = dfa.getDigitalNumber(fileProcessor);
    }

}
