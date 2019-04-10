package test;

import test.lexicalPraser.FileProcessorTest;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class BaseTest {
    public static Logger LOGGER = Logger.getLogger(FileProcessorTest.class.getName());

    {
        FileHandler fileHandler = null;
        try{
            fileHandler  = new FileHandler("src/test/lexicalPraser/file/loggerInfos.log");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
    }
}
