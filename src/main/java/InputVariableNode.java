import java.util.List;

public class InputVariableNode implements Node<String> {
    private final String variableId;

    private InputVariableNode(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public String accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return "VariableNode{" +
                "variableId='" + variableId + '\'' +
                '}';
    }

    public static InputVariableNode build(String variableId) {
        return new InputVariableNode(variableId);
    }

    public String getVariableId() {
        return variableId;
    }
}
