package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class NumberNode implements Node<Integer> {
    private final int number;

    private NumberNode(int number) {
        this.number = number;
    }

    @Override
    public Integer accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
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

    public int getNumber() {
        return number;
    }
}
