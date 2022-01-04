package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class RelationalOperatorNode implements Node {
    private Type type;
    private Node operand1;
    private Node operand2;

    private RelationalOperatorNode(Type type, Node operand1, Node operand2) {
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
        return "RelationalOperatorNode{" +
                "type=" + type +
                '}';
    }

    public enum Type {
        LT,
        GT,
        EQ
    }

    public static RelationalOperatorNode build(Type type, Node operand1, Node operand2) {
        return new RelationalOperatorNode(type, operand1, operand2);
    }
}