import java.util.List;

public class LogicalOperatorNode implements Node<Boolean> {
    private final Type type;
    private final Node<Boolean> operand1;
    private final Node<Boolean> operand2;

    private LogicalOperatorNode(Type type, Node<Boolean> operand1, Node<Boolean> operand2) {
        this.type = type;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Boolean accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of(operand1, operand2);
    }

    @Override
    public String toString() {
        return "LogicalOperatorNode{" +
                "type=" + type +
                '}';
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        AND,
        OR,
        NOT
    }

    public static LogicalOperatorNode build(Type type, Node<Boolean> operand1, Node<Boolean> operand2) {
        return new LogicalOperatorNode(type, operand1, operand2);
    }

    public static LogicalOperatorNode build(Type type, Node<Boolean> operand) {
        return new LogicalOperatorNode(type, operand, null);
    }

    public Node<Boolean> getOperand1() {
        return operand1;
    }

    public Node<Boolean> getOperand2() {
        return operand2;
    }
}
