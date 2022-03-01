import java.util.List;

public class NumberNode implements Node<Long> {
    private final long number;

    private NumberNode(long number) {
        this.number = number;
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
        return "NumberNode{" +
                "number=" + number +
                '}';
    }

    public static NumberNode build(long number) {
        return new NumberNode(number);
    }

    public long getNumber() {
        return number;
    }
}
