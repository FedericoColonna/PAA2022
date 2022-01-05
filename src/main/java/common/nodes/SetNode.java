package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class SetNode implements Node<Void> {
    private final Node<String> variable;
    private final Node<Integer> expression;

    private SetNode(Node<String> variable, Node<Integer> expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public Void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return List.of(variable, expression);
    }

    @Override
    public String toString() {
        return "SetNode{ }";
    }

    public static SetNode build(Node<String> condition, Node<Integer> statement) {
        return new SetNode(condition, statement);
    }

    public Node<String> getVariable() {
        return variable;
    }

    public Node<Integer> getExpression() {
        return expression;
    }
}
