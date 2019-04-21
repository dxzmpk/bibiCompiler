package syntacticAnalyzer.LrOne;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LR1Parser {
	private static final Logger logger = Main.getLogger(LR1Parser.class);
	private final LR1 lr1;

	public static class ParseException extends Exception {
		public ParseException(String string) {
			super(string);
		}
	}

	private class TransitionState {
		private Token token;
		private int state;

		public TransitionState(Token token, int state) {
			this.state = state;
			this.token = token;
		}

		public int getState() {
			return state;
		}

		public String toString() {
			return "<TransitionState> " + String.valueOf(this.state) + " | " + this.token;
		}
	}

	public LR1Parser(LR1 lr1) {
		logger.setLevel(Level.WARNING);
		this.lr1 = lr1;
	}

	public Process parseTokens(List<Token> tokens) throws Exception {
		String startSymbol = lr1.getStartSymbol();
		ListIterator<Token> tokensIterator = tokens.listIterator();
		Stack<TransitionState> parseStack = new Stack<TransitionState>();
		parseStack.push(new TransitionState(null, 0));

		Stack<Process.Node> processStack = new Stack<Process.Node>();

		Token nextToken = tokensIterator.next();
		LR1.Action nextAction = null;
		while (!nextToken.getKind().equals(startSymbol)) {

			nextAction = lr1.actionFor(parseStack.peek().getState(), nextToken.getKind());

			if (nextAction instanceof LR1.ActionShift) {
				LR1.ActionShift shift = (LR1.ActionShift) nextAction;
				TransitionState transitionState = new TransitionState(nextToken, shift.getToState());
				parseStack.push(transitionState);
				if(lr1.isTerminalSymbol(nextToken.getKind())) {
					processStack.push(new Process.LeafNode(nextToken));
				}
				// 遍历到下一个token
				if(!tokensIterator.hasNext()) tokensIterator = tokens.listIterator();
				nextToken = tokensIterator.next();
			}
			else if (nextAction instanceof LR1.ActionReduce){
				LR1.ActionReduce reduce = (LR1.ActionReduce) nextAction;
				logger.fine("" + reduce);

				List<Process.Node> poppedNodes = new LinkedList<Process.Node>();

				// Pop the stack according to the length of the production rule's RHS
				for (int i = 0; i < reduce.getInt(); i++) {
					TransitionState poppedState = parseStack.pop();
					poppedNodes.add(0, processStack.pop());
				}

				// Push the production rule as a tree node
				Process.Node treeNode = new Process.TreeNode(reduce.getProductionRule(), poppedNodes);
				processStack.push(treeNode);

				// 遍历到前一个token
				tokensIterator.previous();

				nextToken = new Token(reduce.getProductionRule().getLefthand(), Arrays.toString(reduce.getProductionRule().getRighthand()));
			}
			else {
				String nodeStackTrace = "";
				for(Process.Node root: processStack) nodeStackTrace += root.toString(0);
				logger.severe("ERROR! 此状态没有对应的ACTION分析表动作！ " + parseStack.peek().getState() + " + " + nextToken + "\n" + nodeStackTrace);
				throw new ParseException("ERROR！句法分析发生错误！");
			}
		}


		assert processStack.size() == 1 : "ERROR!  无法识别的字符！ ";
		Process parseTree = new Process(processStack.pop());
		return parseTree;
	}
}
