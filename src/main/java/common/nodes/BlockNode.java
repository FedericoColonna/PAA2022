package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class BlockNode implements Node<Void> {
    private final List<Node> statements;

    private BlockNode(List<Node> statements) {
        this.statements = statements;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return statements;
    }

    @Override
    public String toString() {
        return "BlockNode{ }";
    }

    public static BlockNode build(Node... statementArray) {
        return new BlockNode(List.of(statementArray));
    }
}
