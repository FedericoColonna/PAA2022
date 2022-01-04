package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class LogicalOperatorNode implements Node {
    private Type type;
    private Node operand1;
    private Node operand2;

    private LogicalOperatorNode(Type type, Node operand1, Node operand2) {
        this.type = type;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
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

    public enum Type {
        AND,
        OR,
        NOT
    }

    public static LogicalOperatorNode build(Type type, Node operand1, Node operand2) {
        return new LogicalOperatorNode(type, operand1, operand2);
    }

    public static LogicalOperatorNode build(Type type, Node operand) {
        return new LogicalOperatorNode(type, operand, null);
    }
}
