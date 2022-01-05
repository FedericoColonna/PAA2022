package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class PrintNode implements Node<Void> {
    private final Node<Integer> valueToPrint;

    private PrintNode(Node<Integer> valueToPrint) {
        this.valueToPrint = valueToPrint;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(valueToPrint);
    }

    @Override
    public String toString() {
        return "PrintNode{ }";
    }

    public static PrintNode build(Node<Integer> valueToPrint) {
        return new PrintNode(valueToPrint);
    }

    public Node<Integer> getValueToPrint() {
        return valueToPrint;
    }
}
