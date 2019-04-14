package lexicalPraser.implement;

import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.interfaces.ParserInterface;
import lexicalPraser.model.TokenItem;

import java.util.List;

public class Praser implements ParserInterface {

    @Override
    public void praseByFileName(String inputFileName, String outputFileName) {
        DfaInterface dfa = new DFA();
        FileProcessor fileProcessor =
                new FileProcessor(inputFileName);
        List<TokenItem> tokenItems = dfa.getTokenFromSentence(fileProcessor);
        for (TokenItem tokenItem : tokenItems) {
            String value =  tokenItem.getValue() == null ? " _ " :   tokenItem.getValue();
            System.out.println(tokenItem.getInitialWord() + "\t" + "<" + tokenItem.getLexicalName() + ", " + value + ">");
        }
    }

}
