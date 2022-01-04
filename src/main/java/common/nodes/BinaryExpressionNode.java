package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class BinaryExpressionNode implements Node {
    private Type type;
    private Node expression1;
    private Node expression2;

    private BinaryExpressionNode(Type type, Node expression1, Node expression2) {
        this.type = type;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of(expression1, expression2);
    }

    @Override
    public String toString() {
        return "BinaryExpressionNode{" +
                "type=" + type +
                '}';
    }

    public enum Type {
        SET,
        WHILE
    }

    public static BinaryExpressionNode build(Type type, Node expression1, Node expression2) {
        return new BinaryExpressionNode(type, expression1, expression2);
    }
}
