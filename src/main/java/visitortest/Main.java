package visitortest;

public class Main {
    public static void main(String[] args) {
        Node root = buildTree();

        visitInOrderWithVisitor(root, new PrintNodeValueVisitor());

        AggregateValuesVisitor aggregateValuesVisitor = new AggregateValuesVisitor();
        visitInOrderWithVisitor(root, aggregateValuesVisitor);
        System.out.println(aggregateValuesVisitor.getValues());

        AdditionValuesVisitor additionValuesVisitor = new AdditionValuesVisitor();
        visitInOrderWithVisitor(root, additionValuesVisitor);
        System.out.println(additionValuesVisitor.getResult());
    }

    private static Node buildTree() {
        Node node1 = new Node(1, null, null);
        Node node4 = new Node(4, null, null);
        Node node6 = new Node(6, null, null);

        Node node2 = new Node(2, node1, null);
        Node node5 = new Node(5, node4, node6);

        return new Node(3, node2, node5);
    }

    private static void visitInOrder(Node node) {
        if (node.getLeft() != null) {
            visitInOrder(node.getLeft());
        }
        System.out.println(node.getValue());
        if (node.getRight() != null) {
            visitInOrder(node.getRight());
        }
    }

    private static void visitInOrderWithVisitor(Node node, NodeVisitor nodeVisitor) {
        if (node.getLeft() != null) {
            visitInOrderWithVisitor(node.getLeft(), nodeVisitor);
        }
        node.accept(nodeVisitor);
        if (node.getRight() != null) {
            visitInOrderWithVisitor(node.getRight(), nodeVisitor);
        }
    }
}
