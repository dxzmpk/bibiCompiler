/**
 *
 */
package syntacticAnalyzer.LrOne;

import java.util.List;

public class Process {

	public interface Node {
		public String toString(int level);
		public String getKind();
	}

	public static class TreeNode implements Node {
		public final LR1.ProductionRule productionRule;
		public final List<Node> children;

		public TreeNode(LR1.ProductionRule productionRule, List<Node> children) {
			this.productionRule = productionRule;
			this.children = children;
		}

		@Override
		public String toString() {
			return this.productionRule.toString();
		}

		public String toString(int level) {
			String str = "";
			for(int i = 0; i < level; i++) {
				str += "  ";
			}

			str += this.productionRule + "\n";
			for(Node node: this.children) str += node.toString(level + 1);
			return str;
		}

		@Override
		public String getKind() {
			return this.productionRule.getLefthand();
		}
	}

	public static class LeafNode implements Node {
		public final Token token;

		public LeafNode(Token token) {
			this.token = token;
		}

		@Override
		public String toString() {
			return this.token.toString();
		}

		public String toString(int level) {
			String str = "";
			for(int i = 0; i < level; i++) {
				str += "  ";
			}

			str += this.token + "\n";
			return str;
		}

		@Override
		public String getKind() {
			return this.token.getKind();
		}
	}

	public final Node root;

	/**
	 *
	 */
	public Process(Node root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "<Process>\n" + this.root.toString(0);
	}
}
