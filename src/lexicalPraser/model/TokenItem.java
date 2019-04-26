package lexicalPraser.model;

public class TokenItem {

    public String initialWord;

    String lexicalName;

    String value;

    public TokenItem(String lexicalName) {
        this.lexicalName = lexicalName;
    }

    public TokenItem() {
    }

    public TokenItem(String initialWord, String lexicalName) {
        this.initialWord = initialWord;
        this.lexicalName = lexicalName;
    }

    public String getInitialWord() {
        return initialWord;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public String getLexicalName() {
        return lexicalName;
    }

    public void setLexicalName(String lexicalName) {
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
