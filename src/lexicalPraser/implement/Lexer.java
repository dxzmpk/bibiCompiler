package lexicalPraser.implement;

import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.interfaces.LexerInterface;
import lexicalPraser.model.TokenItem;

import java.util.List;

public class Lexer implements LexerInterface {

    @Override
    public List<TokenItem> lexByFileName(String inputFileName, String outputFileName) {
        DfaInterface dfa = new DFA();
        FileProcessor fileProcessor =
                new FileProcessor(inputFileName);
        List<TokenItem> tokenItems = dfa.getTokenFromSentence(fileProcessor);
        return tokenItems;
    }

}
