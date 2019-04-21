/**
 * 
 */
package syntacticAnalyzer.LrOne;

import java.util.List;
import java.util.Stack;


public class ParseTreeTraverse {

	public static interface Traverser {
		public List<Process.Node> processTreeNode(Process.TreeNode treeNode) throws Exception;

		public void processLeafNode(Process.LeafNode leafNode) throws Exception;
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

	public void traverse(Process.Node root) throws Exception {
		Stack<Process.Node> nodeStack = new Stack<Process.Node>();
		if (root != null)
			nodeStack.push(root);

		while (!nodeStack.isEmpty()) {
			Process.Node node = nodeStack.pop();

			if (node instanceof Process.TreeNode) {
				Process.TreeNode treeNode = (Process.TreeNode) node;
				List<Process.Node> offer = this.traverser.processTreeNode(treeNode);
				if (offer != null) {
				}
			} else if (node instanceof Process.LeafNode) {
				Process.LeafNode leafNode = (Process.LeafNode) node;
				this.traverser.processLeafNode(leafNode);
			}
		}
	}
}
