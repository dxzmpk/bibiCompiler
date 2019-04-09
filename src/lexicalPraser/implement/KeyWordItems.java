package lexicalPraser.implement;

import lexicalPraser.model.LexicalNames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyWordItems {

    static List<String> ids = new ArrayList<>();

    private static KeyWordItems ourInstance = new KeyWordItems();

    public static KeyWordItems getInstance() {
        return ourInstance;
    }

    private KeyWordItems() {
        LexicalNames[] lexicalNames = LexicalNames.values();
        for (LexicalNames lexicalName : lexicalNames
             ) {
            ids.add(lexicalName.name());
        }
    }

    protected static boolean isKeyWord(String id) {
        return ids.contains(id);
    }
}
