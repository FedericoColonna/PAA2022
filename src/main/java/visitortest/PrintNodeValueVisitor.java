package visitortest;

public class PrintNodeValueVisitor implements NodeVisitor {
    @Override
    public void visit(Node node) {
        System.out.println(node.getValue());
    }
}
