package lexicalPraser.interfaces;

public interface ParserInterface {

    /**
     * 输入文件名，得到Token序列，输出到文件中，文件名由调用者制定
     * @param inputFileName
     * @param outputFileName
     */
    public void praseByFileName(String inputFileName, String outputFileName);

}
