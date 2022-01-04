package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class NumberNode implements Node {
    private int number;

    private NumberNode(int number) {
        this.number = number;
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
        return "NumberNode{" +
                "number=" + number +
                '}';
    }

    public static NumberNode build(int number) {
        return new NumberNode(number);
    }
}
