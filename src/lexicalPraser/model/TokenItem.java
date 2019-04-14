package lexicalPraser.model;

public class TokenItem {

    public String initialWord;

    LexicalNames lexicalName;

    String value;

    public TokenItem(LexicalNames lexicalName) {
        this.lexicalName = lexicalName;
    }

    public TokenItem() {
    }

    public String getInitialWord() {
        return initialWord;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public LexicalNames getLexicalName() {
        return lexicalName;
    }

    public void setLexicalName(LexicalNames lexicalName) {
        this.lexicalName = lexicalName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TokenItem{" +
                "lexicalName=" + lexicalName +
                ", value='" + value + '\'' +
                '}';
    }
}
