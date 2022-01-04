package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public class VariableNode implements Node {
    private String variableId;

    private VariableNode(String variableId) {
        this.variableId = variableId;
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
        return "VariableNode{" +
                "variableId='" + variableId + '\'' +
                '}';
    }

    public static VariableNode build(String variableId) {
        return new VariableNode(variableId);
    }
}
