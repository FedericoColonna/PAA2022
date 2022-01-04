package visitortest;

public class AdditionValuesVisitor implements NodeVisitor {
    private int result;

    public AdditionValuesVisitor() {
        result = 0;
    }

    @Override
    public void visit(Node node) {
       result += node.getValue();
    }

    public int getResult() {
        return result;
    }
}
