package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class ConditionalNode implements Node<Void> {
    private final Node<Boolean> condition;
    private final Node<Void> statement1;
    private final Node<Void> statement2;

    private ConditionalNode(Node<Boolean> condition, Node<Void> statement1, Node<Void> statement2) {
        this.condition = condition;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
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

    public static ConditionalNode build(Node<Boolean> condition, Node<Void> statement1, Node<Void> statement2) {
        return new ConditionalNode(condition, statement1, statement2);
    }

    public Node<Boolean> getCondition() {
        return condition;
    }

    public Node<Void> getStatement1() {
        return statement1;
    }

    public Node<Void> getStatement2() {
        return statement2;
    }
}
