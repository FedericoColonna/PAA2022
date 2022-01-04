package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class ConditionalNode implements Node {
    private Node condition;
    private Node statement1;
    private Node statement2;

    private ConditionalNode(Node condition, Node statement1, Node statement2) {
        this.condition = condition;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of(statement1, statement2);
    }

    @Override
    public String toString() {
        return "ConditionalNode{" +
                "condition=" + condition +
                '}';
    }

    public static ConditionalNode build(Node condition, Node statement1, Node statement2) {
        return new ConditionalNode(condition, statement1, statement2);
    }
}
