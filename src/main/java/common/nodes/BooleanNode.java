package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class BooleanNode implements Node<Boolean> {
    private final boolean booleanValue;

    private BooleanNode(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @Override
    public Boolean accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return "BooleanNode{" +
                "booleanValue=" + booleanValue +
                '}';
    }

    public static BooleanNode build(boolean booleanValue) {
        return new BooleanNode(booleanValue);
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }
}
