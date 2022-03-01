import java.util.List;

public class PrintNode implements Node<Void> {
    private final Node<Long> valueToPrint;

    private PrintNode(Node<Long> valueToPrint) {
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

    public static PrintNode build(Node<Long> valueToPrint) {
        return new PrintNode(valueToPrint);
    }

    public Node<Long> getValueToPrint() {
        return valueToPrint;
    }
}
