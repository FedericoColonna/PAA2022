package visitortest;

public class Node {
    private final int value;
    private final Node left;
    private final Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
