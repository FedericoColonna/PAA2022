package common.nodes;

import common.visitors.NodeVisitor;

import java.util.List;

public interface Node<R> {
    R accept(NodeVisitor nodeVisitor);
    List<Node> getChildren();
}
