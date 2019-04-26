package lexicalPraser.implement;

import lexicalPraser.interfaces.DfaInterface;
import lexicalPraser.model.LexicalNames;
import lexicalPraser.model.TokenItem;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author dxzmpk assistby houzi
 *
 */
public class DFA implements DfaInterface {

    @Override
    public List<TokenItem> getTokenFromSentence(FileProcessor fileProcessor) {
        List<TokenItem> tokenItemList = new ArrayList<>();
        char nextChar = ' ';
        int lineNum = 1;
        TokenItem tokenItem;
        while (nextChar != '$'){
            nextChar = fileProcessor.getNextCharacter();
            if(isRelop(nextChar)){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getRelop(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(isDigit(nextChar)){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getDigitalNumber(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(isLetter(nextChar)){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getIdentiFier(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(nextChar == '/'){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getNote(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(isOperator(nextChar)){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getOperator(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(isSymbol(nextChar)){
                fileProcessor.pushBackLastCharacter();
                tokenItem = getSymbol(fileProcessor);
                tokenItemList.add(tokenItem);
            } else if(nextChar == ' ' || nextChar == '\n'||nextChar == '$'){
                //continue but unnecessary;
            } else if(nextChar == '\r'){
                lineNum ++;
            } else {
                System.out.println( "非法字符" + nextChar + "出现在第" + lineNum + "行" );
            }
        }
        return tokenItemList;
    }

    @Override
    public TokenItem getRelop(FileProcessor fileProcessor) {
        TokenItem tokenItem = new TokenItem(LexicalNames.RELOP.toString());
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
                    tokenItem.setInitialWord("<=");
                    return tokenItem;
                case 3:
                    tokenItem.setValue("NE");
                    tokenItem.setInitialWord("<>");
                    return tokenItem;
                case 4:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue("LT");
                    tokenItem.setInitialWord("<");
                    return tokenItem;
                case 5:
                    c = fileProcessor.getNextCharacter();
                    if(c == '=') state = 6;
                    else state = 7;
                    break;
                case 6:
                    tokenItem.setValue("EQ");
                    tokenItem.setInitialWord("==");
                    return tokenItem;
                case 7:
                    tokenItem.setLexicalName(LexicalNames.SYMBOL.toString());
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setInitialWord("=");
                    tokenItem.setValue("AS");
                    return tokenItem;
                case 8:
                    c = fileProcessor.getNextCharacter();
                    if(c == '=')state = 9;
                    else state = 10;
                case 9:
                    tokenItem.setValue("GE");
                    tokenItem.setInitialWord(">=");
                    return tokenItem;
                case 10:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue("GT");
                    tokenItem.setInitialWord(">");
                    return tokenItem;
            }

        }
    }

    @Override
    public TokenItem getIdentiFier(FileProcessor fileProcessor) {
    	TokenItem tokenItem = new TokenItem(LexicalNames.ID.toString());
        int state =9;
        char c;
        StringBuilder value = new StringBuilder();
        while(true){
            switch (state) {
                case 9: {
                    c = fileProcessor.getNextCharacter();
                    if(isLetter(c) ) {
                        value.append(c);
                        state = 10;
                        break;
                    }
                    else fail(fileProcessor);
                }
                case 10: {
                    c = fileProcessor.getNextCharacter();
                    if(isLetter(c) || isDigit (c)) {
                        value.append(c);
                        state = 10;
                    }
                    else state = 11;
                    break;
                }

                case 11:{
                    fileProcessor.pushBackLastCharacter();
                    if(KeyWordItems.isKeyWord(value.toString().toUpperCase())){
                        tokenItem.setValue(value.toString());
                        return tokenItem;
                    } else {
                        tokenItem.setValue(value.toString());
                        tokenItem.setInitialWord(value.toString());
                        return tokenItem;
                    }
                }
            }
        }
    }

    @Override
    public TokenItem getDigitalNumber(FileProcessor fileProcessor) {
        TokenItem tokenItem = new TokenItem(LexicalNames.DIGIT.toString());
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
                        tokenItem.setInitialWord(value.toString());
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
                    tokenItem.setInitialWord(value.toString());
                    return tokenItem;
                case 20:
                    fileProcessor.pushBackLastCharacter();
                    tokenItem.setValue(value.toString());
                    tokenItem.setInitialWord(value.toString());
                    return tokenItem;
            }
        }
    }

    @Override
    public TokenItem getSymbol(FileProcessor fileProcessor) {
        char symbol = fileProcessor.getNextCharacter();
        TokenItem tokenItem = new TokenItem("" + symbol);
        String string = "" + (int)symbol;
        tokenItem.setValue(string);
        tokenItem.setInitialWord("" + symbol);
        return tokenItem;
    }

    @Override
    public TokenItem getOperator(FileProcessor fileProcessor) {
        char symbol = fileProcessor.getNextCharacter();
        TokenItem tokenItem = new TokenItem(symbol + "");
        String string = "" + symbol;
        tokenItem.setValue(string);
        tokenItem.setInitialWord(string);
        return tokenItem;
    }

    @Override
    public TokenItem getNote(FileProcessor fileProcessor) {
    	TokenItem tokenItem = new TokenItem(LexicalNames.NOTE.toString());
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
                tokenItem.setInitialWord(value.toString());
                return tokenItem;
            case 27:
            	fail(fileProcessor);
            	break;
            case 28:
            	tokenItem.setLexicalName("=");
                fileProcessor.pushBackLastCharacter();
                tokenItem.setValue(value.toString());
                tokenItem.setInitialWord(value.toString());
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

    @Override
    public void spaceHandler() {
    }

    @Override
    public boolean isRelop(char c) {
        return (c == '>' || c == '<' || c == '=');
    }

    @Override
    public boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    @Override
    public boolean isSymbol(char c) {
        return (c == '{'|| c == '}' || c == '(' || c == ')' || c == ';' );
    }
}
