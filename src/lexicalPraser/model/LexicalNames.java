package lexicalPraser.model;

public enum LexicalNames {

    /**
     * symbol代表定界符号 生成式为：symbol --> ;|=|{|}|(|)
     */
    RELOP, ID, DIGIT, SYMBOL, NOTE, OPERATOR, IF, ELSE, DO, WHILE, INT, FLOAT, BOOLEAN, LOG;
    //> < , letter, digit, ; , / , + - * /
}
