import java.util.List;

public class WhileNode implements Node<Void> {
    private final Node<Boolean> condition;
    private final Node<Void> statement;

    private WhileNode(Node<Boolean> condition, Node<Void> statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(condition, statement);
    }

    @Override
    public String toString() {
        return "WhileNode{ }";
    }

    public static WhileNode build(Node<Boolean> condition, Node<Void> statement) {
        return new WhileNode(condition, statement);
    }

    public Node<Boolean> getCondition() {
        return condition;
    }

    public Node<Void> getStatement() {
        return statement;
    }
}
