package lexicalPraser.interfaces;

import lexicalPraser.implement.FileProcessor;
import lexicalPraser.model.TokenItem;

import java.util.List;

public interface DfaInterface {

    /**
     * 从当前文件中得到Token
     * @param fileProcessor
     * @return List<TokenItem>
     */
    public List<TokenItem> getTokenFromSentence(FileProcessor fileProcessor);

    /**
     * 识别大于小于运算符
     * @param fileProcessor
     * @return
     */
    public TokenItem getRelop(FileProcessor fileProcessor);

    /**
     * 识别关键字和id
     * @param fileProcessor
     * @return
     */
    public TokenItem getIdentiFier(FileProcessor fileProcessor);

    /**
     * 识别定界符
     * @param fileProcessor
     * @return
     */
    public TokenItem getSymbol(FileProcessor fileProcessor);

    /**
     * 识别定界符
     * @param fileProcessor
     * @return
     */
    public TokenItem getOperator(FileProcessor fileProcessor);

    /**
     * 识别关键字note
     * @param fileProcessor
     */
    public TokenItem getNote(FileProcessor fileProcessor);

    /**
     * 判断是否为letter
     * @param c
     * @return
     */
    public boolean isLetter(char c);

    /**
     * 判断是否为digit数字
     * @param c
     * @return
     */
    public boolean isDigit(char c);

    /**
     * 识别无符号数字
     * @param fileProcessor
     * @return
     */
    public TokenItem getDigitalNumber(FileProcessor fileProcessor);

    /**
     * 错误处理函数
     * @param fileProcessor
     */
    public void fail(FileProcessor fileProcessor);

    public void errorHandler(FileProcessor fileProcessor);

    /**
     * 处理空格情况，结束当前字符的识别
     */
    public void spaceHandler();

    /**
     * 判断下一个字符是否为比较运算符
     * @param c
     */
    public boolean isRelop(char c);

    /**
     * 判断下一个字符是否为算术运算符
     * @param c
     */
    public boolean isOperator(char c);

    /**
     * 判断下一个字符是否为定界符
     * @param c
     */
    public boolean isSymbol(char c);

}
