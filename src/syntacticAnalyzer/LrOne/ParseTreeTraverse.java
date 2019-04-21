/**
 * 
 */
package syntacticAnalyzer.LrOne;

import java.util.List;
import java.util.Stack;


public class ParseTreeTraverse {

	public static interface Traverser {
		public List<ParseTree.Node> processTreeNode(ParseTree.TreeNode treeNode) throws Exception;

		public void processLeafNode(ParseTree.LeafNode leafNode) throws Exception;
	}

	private Traverser traverser;

	/**
	 * @return the traverser
	 */
	public Traverser getTraverser() {
		return traverser;
	}

	/**
	 * @param traverser the traverser to set
	 */
	public void setTraverser(Traverser traverser) {
		this.traverser = traverser;
	}

	public ParseTreeTraverse(Traverser traverser) {
		this.traverser = traverser;
	}

	public void traverse(ParseTree.Node root) throws Exception {
		Stack<ParseTree.Node> nodeStack = new Stack<ParseTree.Node>();
		if (root != null)
			nodeStack.push(root);

		while (!nodeStack.isEmpty()) {
			ParseTree.Node node = nodeStack.pop();

			if (node instanceof ParseTree.TreeNode) {
				ParseTree.TreeNode treeNode = (ParseTree.TreeNode) node;
				List<ParseTree.Node> offer = this.traverser.processTreeNode(treeNode);
				if (offer != null) {
				}
			} else if (node instanceof ParseTree.LeafNode) {
				ParseTree.LeafNode leafNode = (ParseTree.LeafNode) node;
				this.traverser.processLeafNode(leafNode);
			}
		}
	}
}
