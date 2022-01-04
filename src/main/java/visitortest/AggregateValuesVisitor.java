package visitortest;

import java.util.ArrayList;
import java.util.List;

public class AggregateValuesVisitor implements NodeVisitor {
    private final List<Integer> values;

    public AggregateValuesVisitor() {
        this.values = new ArrayList<>();
    }

    @Override
    public void visit(Node node) {
       values.add(node.getValue());
    }

    public List<Integer> getValues() {
        return values;
    }
}
