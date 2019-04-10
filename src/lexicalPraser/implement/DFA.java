package lexicalPraser.implement;

import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.interfaces.FileProcessorInterface;
import lexicalPraser.model.LexicalNames;
import lexicalPraser.model.TokenItem;

import java.util.List;
/**
 * 
 * @author dxzmpk assistby houzi
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
        char c ;
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
    	TokenItem tokenItem = new TokenItem(LexicalNames.ID);
        int state =9;
        char c;
        StringBuilder value = new StringBuilder();
        while(true){
            switch (state) {
            case 9:
            	c = fileProcessor.getNextCharacter();
            	if(isLetter(c) ) {
            	    value.append(c);
            	    state = 10;
                }
            	else fail(fileProcessor);
            	break;
            case 10:
            	c = fileProcessor.getNextCharacter();
            	if(isLetter(c) || isDigit (c)) {
                    value.append(c);
            	    state = 10;
                }
            	else state = 11;
            	break;
            case 11:
                if(KeyWordItems.isKeyWord(value.toString())){
                    tokenItem.setLexicalName(LexicalNames.valueOf(value.toString()));
                } else {
                    tokenItem.setValue(value.toString());
                }
                return tokenItem;
            }
        }
    }

    @Override
    public TokenItem getDigitalNumber(FileProcessor fileProcessor) {
        TokenItem tokenItem = new TokenItem(LexicalNames.DIGIT);
        int state = 12;
        StringBuilder value = new StringBuilder();
        char c;
        while(true){
            switch (state){
                case 12:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)) {
                        state = 13;
                        value.append(c);
                    }
                    else fail(fileProcessor);
                case 13:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)){
                        state = 13;
                        value.append(c);
                    } else if(c == '.'){
                        state = 14;
                        value.append(c);
                    } else if(c == 'e' || c == 'E') {
                        state = 16;
                        value.append(c);
                    } else {
                        state = 20;
                    }
                    break;
                case 14:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)){
                        state = 15;
                        value.append(c);
                    } else {
                        errorHandler(fileProcessor);
                    }
                    break;
                case 15:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)){
                        state = 15;
                        value.append(c);
                    } else if(c == 'e' || c == 'E') {
                        state = 16;
                        value.append(c);
                    } else {
                        state = 20;
                    }
                    break;
                case 16:
                    c = fileProcessor.getNextCharacter();
                    if(c == '+' || c == '-'){
                        state = 17;
                        value.append(c);
                    } else if(isDigit(c)){
                        state = 18;
                        value.append(c);
                    } else {
                        fileProcessor.pushBackLastCharacter();
                        tokenItem.setValue(value.toString());
                        return tokenItem;
                    }
                    break;
                case 17:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)){
                        state = 18;
                        value.append(c);
                    } else {
                        errorHandler(fileProcessor);
                    }
                    break;
                case 18:
                    c = fileProcessor.getNextCharacter();
                    if(isDigit(c)){
                        state = 18;
                        value.append(c);
                    } else {
                        state = 19;
                    }
                    break;
                case 19:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue(value.toString());
                    return tokenItem;
                case 20:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue(value.toString());
                    return tokenItem;
            }
        }
    }

    @Override
    public TokenItem getNote(FileProcessor fileProcessor) {
    	TokenItem tokenItem = new TokenItem(LexicalNames.NOTE);
        int state =22;
        StringBuilder value = new StringBuilder();
        char c;
        while(true){
            switch (state) {
            case 22:
            	c = fileProcessor.getNextCharacter();
            	if(c == '/') {
            	    state = 23;
                    value.append(c);
                }
            	else state = 27;
            	break;
            case 23:
            	c = fileProcessor.getNextCharacter();
            	if(c == '*') {
            	    state = 24;
                    value.append(c);
            }
            	else state = 28;
            	break;
            case 24:
            	c = fileProcessor.getNextCharacter();
            	if(c == '*') {
            	    state = 25;
                    value.append(c);
                }
            	else if(isLetter(c) || isDigit (c)) {
                    value.append(c);
            	    state = 24;
                }
            	else state = 29;
            	break;
            case 25:
            	c = fileProcessor.getNextCharacter();
            	if(c == '/') {
            	    state = 26;
                    value.append(c);
                }
            	else state = 29;
            	break;
            case 26:
            	tokenItem.setValue(value.toString());
                return tokenItem;
            case 27:
            	fail(fileProcessor);
            	break;
            case 28:
            	tokenItem.setLexicalName(LexicalNames.OPERATOR);
                fileProcessor.pushBackLastCharacter();
                tokenItem.setValue(value.toString());
                return tokenItem;
            case 29:
            	errorHandler(fileProcessor);
            	break;
            }
        }
    }

    /**
     * 辅助用方法
     */

    /**
     * 判断是否为字母或者下划线
     * @param c
     * @return
     */
    @Override
    public boolean isLetter(char c) {
        return Character.isLetter(c)||c=='_';
    }

    @Override
    public boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    @Override
    public void fail(FileProcessor fileProcessor) {

    }

    @Override
    public void errorHandler(FileProcessor fileProcessor) {

    }
}
