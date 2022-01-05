package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class RootNode implements Node<Void> {
    private final Node<Void> child;

    private RootNode(Node<Void> child) {
        this.child = child;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(child);
    }

    @Override
    public String toString() {
        return "RootNode{ }";
    }

    public static RootNode build(Node<Void> child) {
        return new RootNode(child);
    }

    public Node<Void> getChild() {
        return child;
    }
}
