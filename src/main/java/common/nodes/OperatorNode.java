package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class OperatorNode implements Node {
    private Type type;
    private Node operand1;
    private Node operand2;

    private OperatorNode(Type type, Node operand1, Node operand2) {
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
        return "OperatorNode{" +
                "type=" + type +
                '}';
    }

    public enum Type {
        ADD,
        SUB,
        MUL,
        DIV
    }

    public static OperatorNode build(Type type, Node operand1, Node operand2) {
        return new OperatorNode(type, operand1, operand2);
    }
}
