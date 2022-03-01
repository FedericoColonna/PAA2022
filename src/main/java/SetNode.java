import java.util.List;

public class SetNode implements Node<Void> {
    private final Node<String> variable;
    private final Node<Long> expression;

    private SetNode(Node<String> variable, Node<Long> expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(variable, expression);
    }

    @Override
    public String toString() {
        return "SetNode{ }";
    }

    public static SetNode build(Node<String> condition, Node<Long> statement) {
        return new SetNode(condition, statement);
    }

    public Node<String> getVariable() {
        return variable;
    }

    public Node<Long> getExpression() {
        return expression;
    }
}
