package dxzmpk.impl;

import com.google.common.collect.Table;
import dxzmpk.model.Action;
import dxzmpk.model.Reduce;
import dxzmpk.model.Shift;
import lexicalPraser.implement.Lexer;
import lexicalPraser.model.TokenItem;

import java.util.*;

public class SLRParser {

    List<TokenItem> tokenItems;

    private class TransitionState{
        private TokenItem tokenItem;
        private Integer state;

        public TransitionState(TokenItem tokenItem, Integer state) {
            this.tokenItem = tokenItem;
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }


    public Process parseByFile(String fileName){
        Table<Integer, String, Action> table = TableGenerater.generateTable();
        Lexer lexer = new Lexer();
        tokenItems = lexer.lexByFileName(fileName," ");
        ListIterator<TokenItem> tokesIterator = tokenItems.listIterator();
        Stack<TransitionState> parseStack = new Stack<>();
        parseStack.push(new TransitionState(null,0));
        Stack<Process.Node> processStack = new Stack<>();

        TokenItem nextToken = tokesIterator.next();
        Action nextAction = null;
        //todo
        while (true){
            nextAction = table.get(parseStack.peek().getState(), nextToken.getLexicalName());

            if(nextAction instanceof Shift){
                Shift shift = (Shift) nextAction;
                TransitionState transitionState = new TransitionState(nextToken, shift.getInt());
                parseStack.push(transitionState);
                //是终结符（双重否定）
                if(!ProductionProcessor.isNonTermical(nextToken.getLexicalName())){
                    processStack.push(new Process.LeafNode(nextToken));
                }
                if(!tokesIterator.hasNext()) tokesIterator = tokenItems.listIterator();
                nextToken = tokesIterator.next();
            } else if(nextAction instanceof Reduce){
                Reduce reduce = (Reduce) nextAction;
                List<Process.Node> poppedNodes = new LinkedList<>();
                for(int i = 0; i < reduce.getInt(); i++) {
                    TransitionState poppedState = parseStack.pop();
                    poppedNodes.add(0, processStack.pop());
                }
                System.out.println("产生式为："+reduce.getProduction().toString());
                Process.Node treeNode = new Process.TreeNode(reduce.getProduction(), poppedNodes);
                processStack.push(treeNode);

                tokesIterator.previous();

                nextToken = new TokenItem(reduce.getProduction().left, Arrays.toString(reduce.getProduction().right));

            } else {
                String nodeStackTrace = "";
                for(Process.Node root: processStack) nodeStackTrace += root.toString(0);
                System.out.println("ERROR,此状态ACTION表为空");
            }

            Process parseTree = new Process(processStack.pop());
            return parseTree;

        }
    }
}
