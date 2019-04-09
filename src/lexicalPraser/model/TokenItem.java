package lexicalPraser.model;

public class TokenItem {

    LexicalNames lexicalName;

    String value;

    public TokenItem(LexicalNames lexicalName) {
        this.lexicalName = lexicalName;
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

}
