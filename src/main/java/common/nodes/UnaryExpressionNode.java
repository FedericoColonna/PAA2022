package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class UnaryExpressionNode implements Node {
    private Type type;
    private Node expression;

    private UnaryExpressionNode(Type type, Node expression) {
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of(expression);
    }

    @Override
    public String toString() {
        return "UnaryExpressionNode{" +
                "type=" + type +
                '}';
    }

    public enum Type {
        INPUT,
        PRINT
    }

    public static UnaryExpressionNode build(Type type, Node expression) {
        return new UnaryExpressionNode(type, expression);
    }
}
