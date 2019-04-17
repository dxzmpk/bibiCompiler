package syntacticAnalyzer.implement;


import syntacticAnalyzer.interfaces.FileProcessorInterface;

import java.io.*;

public class FileProcessor implements FileProcessorInterface {

    private static Reader reader;

    private static char c;

    private static BufferedReader bufferedReader;

    /**
     * 构造方法，新建pushbackReader
     * @param filename
     */
    public FileProcessor(String filename) {
        try {
            reader = new FileReader(filename);
            bufferedReader = new BufferedReader(reader);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    @Override
    public String getNextLine() {
        try {
            String line = bufferedReader.readLine();
            if(line == null) {
                System.out.println("到达文件末尾");
                return null;
            } else {
                return line;
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }
}
