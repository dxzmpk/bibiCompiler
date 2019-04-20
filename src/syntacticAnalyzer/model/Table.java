package syntacticAnalyzer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    int status;
    List<Map<Character,String>> maps = new ArrayList<Map<Character,String>>();

    public Table buildTable(){
        Map<Character,String> moves = new HashMap<Character,String>();
        moves.put(' ',"");
        maps.add(moves);
        return this;
    }

    public String getActionByMaps(char nextChar){
        return maps.get(status).get(nextChar);
    }

}
