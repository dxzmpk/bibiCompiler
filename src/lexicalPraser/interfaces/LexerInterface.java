package lexicalPraser.interfaces;

import lexicalPraser.model.TokenItem;

import java.util.List;

public interface LexerInterface {

    /**
     * 输入文件名，得到Token序列，输出到文件中，文件名由调用者制定
     * @param inputFileName
     * @param outputFileName
     */
    public List<TokenItem> lexByFileName(String inputFileName, String outputFileName);

}
