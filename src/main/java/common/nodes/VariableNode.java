package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class VariableNode implements Node<Long> {
    private final String variableId;

    private VariableNode(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public Long accept(NodeVisitor nodeVisitor) {
        return nodeVisitor.visit(this);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return "VariableNode{" +
                "variableId='" + variableId + '\'' +
                '}';
    }

    public static VariableNode build(String variableId) {
        return new VariableNode(variableId);
    }

    public String getVariableId() {
        return variableId;
    }
}
