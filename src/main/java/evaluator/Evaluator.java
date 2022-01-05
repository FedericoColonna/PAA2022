package evaluator;

import common.Context;
import common.nodes.Node;

public class Evaluator {
    private final Node syntaxTree;
    private final Context context;

    public Evaluator(Node sintaxTree) {
        this.syntaxTree = sintaxTree;
        context = new Context();
    }

    public Object evaluate(Object...input) {
        return null;
    }
}
