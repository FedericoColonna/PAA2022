
public class Evaluator {
    private final RootNode syntaxTree;
    private final EvaluationVisitor evaluationVisitor;

    public Evaluator(RootNode syntaxTree, EvaluationVisitor evaluationVisitor) {
        this.syntaxTree = syntaxTree;
        this.evaluationVisitor = evaluationVisitor;
    }

    public void evaluate() {
        evaluationVisitor.visit(syntaxTree);
    }
}
