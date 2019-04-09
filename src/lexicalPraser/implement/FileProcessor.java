package lexicalPraser.implement;

import lexicalPraser.interfaces.FileProcessorInterface;

import java.io.File;

public class FileProcessor implements FileProcessorInterface {

    File file ;

    public FileProcessor(String filename) {
        this.file = new File(filename);
    }

    @Override
    public Character getNextCharacter() {
        return null;
    }

    @Override
    public boolean pushBackLastCharacter() {
        return false;
    }
}
