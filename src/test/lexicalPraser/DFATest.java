package test.lexicalPraser;

import lexicalPraser.implement.DFA;
import lexicalPraser.implement.FileProcessor;
import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.model.TokenItem;
import org.junit.Test;
import test.BaseTest;

public class DfaTest extends BaseTest {

    DfaInterface dfa = new DFA();

    @Test
    public void getRelopTest(){
        FileProcessor fileProcessor = new FileProcessor("src/test/lexicalPraser/file/Relop.txt");
        TokenItem tokenItem = dfa.getRelop(fileProcessor);
        System.out.println(tokenItem.toString());
    }

    @Test
    public void getIdentiFierTest(){
        FileProcessor fileProcessor = new FileProcessor("src/test/lexicalPraser/file/IdentiFier.txt");
        TokenItem tokenItem = dfa.getIdentiFier(fileProcessor);
        System.out.println(tokenItem.toString());
    }

    @Test
    public void getNoteTest(){
        FileProcessor fileProcessor = new FileProcessor("src/test/lexicalPraser/file/Note.txt");
        TokenItem tokenItem = dfa.getNote(fileProcessor);
        System.out.println(tokenItem.toString());
    }

    @Test
    public void getDigitalNumberTest(){
        FileProcessor fileProcessor = new FileProcessor("src/test/lexicalPraser/file/DigitalNumber.txt");
        TokenItem tokenItem = dfa.getDigitalNumber(fileProcessor);
        System.out.println(tokenItem.toString());
    }

}
