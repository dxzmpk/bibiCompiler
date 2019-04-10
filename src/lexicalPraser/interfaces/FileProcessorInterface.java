package lexicalPraser.interfaces;

public interface FileProcessorInterface {

    /**
     * 从文件中读取下一个字符
     * @return Character
     */
    public Character getNextCharacter();

    /**
     * 将最后一个未匹配字符推回输入流
     * @return 成功返回true 失败返回false
     */
    public void pushBackLastCharacter();

}
