package lexicalPraser.implement;

import lexicalPraser.interfaces.FileProcessorInterface;

import java.io.*;

public class FileProcessor implements FileProcessorInterface {

    private static Reader reader;

    private static char c;

    private static PushbackReader pushbackReader ;

    /**
     * 构造方法，新建pushbackReader
     * @param filename
     */
    public FileProcessor(String filename) {
        try {
            reader = new FileReader(filename);
            pushbackReader = new PushbackReader(reader);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    @Override
    public Character getNextCharacter()
    {
        try {
            int readInt = pushbackReader.read();
            if(readInt != -1){
                c = (char) readInt;
            } else {
                pushbackReader.close();
                System.err.print("到达文档末尾，词法分析完成");
                //$代表文档末尾
                c = '$';
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return c;
    }

    @Override
    public void pushBackLastCharacter() {
        try {
            pushbackReader.unread(c);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
