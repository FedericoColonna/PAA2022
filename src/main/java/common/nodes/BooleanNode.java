package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class BooleanNode implements Node {
    private boolean value;

    private BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return "BooleanNode{" +
                "value=" + value +
                '}';
    }

    public static BooleanNode build(boolean value) {
        return new BooleanNode(value);
    }
}
