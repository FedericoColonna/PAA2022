import java.util.List;

public class InputNode implements Node<Void> {
    private final Node<String> inputVariable;

    private InputNode(Node<String> inputVariable) {
        this.inputVariable = inputVariable;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(inputVariable);
    }

    @Override
    public String toString() {
        return "InputNode{ }";
    }

    public static InputNode build( Node<String> inputVariable) {
        return new InputNode(inputVariable);
    }

    public Node<String> getInputVariable() {
        return inputVariable;
    }
}
