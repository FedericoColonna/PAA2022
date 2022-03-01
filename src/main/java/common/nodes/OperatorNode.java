package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class OperatorNode implements Node<Long> {
    private final Type type;
    private final Node<Long> operand1;
    private final Node<Long> operand2;

    private OperatorNode(Type type, Node<Long> operand1, Node<Long> operand2) {
        this.type = type;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Long accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of(operand1, operand2);
    }

    @Override
    public String toString() {
        return "OperatorNode{" +
                "type=" + type +
                '}';
    }

    public Type getType() {
        return type;
    }

    public Node<Long> getOperand1() {
        return operand1;
    }

    public Node<Long> getOperand2() {
        return operand2;
    }

    public enum Type {
        ADD,
        SUB,
        MUL,
        DIV
    }

    public static OperatorNode build(Type type, Node<Long> operand1, Node<Long> operand2) {
        return new OperatorNode(type, operand1, operand2);
    }
}
