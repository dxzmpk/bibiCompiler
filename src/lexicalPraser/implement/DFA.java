package lexicalPraser.implement;

import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.interfaces.FileProcessorInterface;
import lexicalPraser.model.LexicalNames;
import lexicalPraser.model.TokenItem;

import java.util.List;
/**
 * 
 * @author HOU
 *
 */
public class DFA implements DfaInterface {

    @Override
    public List<TokenItem> getTokenFromSentence(FileProcessorInterface fileProcessor) {
        return null;
    }

    @Override
    public TokenItem getRelop(FileProcessor fileProcessor) {
        TokenItem tokenItem = new TokenItem(LexicalNames.RELOP);
        int state = 0;
        char c;
        while(true){
            switch (state) {
                case 0:
                    c = fileProcessor.getNextCharacter();
                    if(c == '<') state = 1;
                    else if(c == '=') state = 5;
                    else if(c == '>') state = 8;
                    else fail(fileProcessor);
                    break;
                case 1:
                    c = fileProcessor.getNextCharacter();
                    if(c == '=') state = 2;
                    else if(c == '>') state = 3;
                    else state = 4;
                    break;
                case 2:
                    tokenItem.setValue("LE");
                    return tokenItem;
                case 3:
                    tokenItem.setValue("NE");
                    return tokenItem;
                case 4:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue("LT");
                    return tokenItem;
                case 5:
                    c = fileProcessor.getNextCharacter();
                    if(c == '=') state = 6;
                    else state = 7;
                    break;
                case 6:
                    tokenItem.setValue("EQ");
                    return tokenItem;
                case 7:
                    tokenItem.setLexicalName(LexicalNames.SYMBOL);
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue("AS");
                case 8:
                    c = fileProcessor.getNextCharacter();
                    if(c == '=')state = 9;
                    else state = 10;
                case 9:
                    tokenItem.setValue("GE");
                    return tokenItem;
                case 10:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue("GT");
                    return tokenItem;
            }

        }
    }

    @Override
    public TokenItem getIdentiFier(FileProcessor fileProcessor) {
        return null;
    }

    @Override
    public TokenItem getDigitalNumber(FileProcessor fileProcessor) {
        return null;
    }

    @Override
    public TokenItem getNote(FileProcessor fileProcessor) {
    	TokenItem tokenItem = new TokenItem(LexicalNames.NOTE);
        int state =22;
        char c;
        while(true){
            switch (state) {
            case 22:
            	c = fileProcessor.getNextCharacter();
            	if(c == '/') state = 23;
            	else fail(fileProcessor);
            	break;
            case 23:
            	c = fileProcessor.getNextCharacter();
            	if(c == '*') state = 24;
            	else state = 28;
            	break;
            case 24:
            	c = fileProcessor.getNextCharacter();
            	if(c == '*') state = 25;
            	else if(isLetter(c) == true || isDigit (c))
            case 28:
            	tokenItem.setLexicalName(LexicalNames.OPERATOR);
                fileProcessor.pushBackLastCharacter();
                tokenItem.setValue("value");
            }
    }

    /**
     * 辅助用方法
     */

    @Override
    public boolean isLetter(char c) {
        return false;
    }

    @Override
    public boolean isDigit(char c) {
        return false;
    }

    @Override
    public void fail(FileProcessor fileProcessor) {

    }
}
