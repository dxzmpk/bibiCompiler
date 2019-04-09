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
    public List<TokenItem> getTokenFromSentence(FileProcessorInterface fileProcessor);

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

}
