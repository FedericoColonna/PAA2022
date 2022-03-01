import java.util.List;
import java.util.Scanner;

public class EvaluationVisitor implements NodeVisitor {

    private final Context context;
    private final Scanner scanner;

    public EvaluationVisitor(Context context) {
        this.context = context;
        scanner = new Scanner(System.in);
    }

    @Override
    public void visit(RootNode toVisit) {
        toVisit.getChild().accept(this);
    }

    @Override
    public long visit(OperatorNode toVisit) {
        long operand1 = toVisit.getOperand1().accept(this);
        long operand2 = toVisit.getOperand2().accept(this);
        switch (toVisit.getType()) {
            case ADD:
                return operand1 + operand2;
            case SUB:
                return operand1 - operand2;
            case MUL:
                return operand1 * operand2;
            case DIV:
                if (operand2 == 0) {
                    throw new RuntimeException("(ERROR in evaluator: Division by zero)");
                }
                return operand1 / operand2;
            default:
                throw new RuntimeException("Unknown operator.");
        }
    }

    @Override
    public void visit(WhileNode toVisit) {
        while (toVisit.getCondition().accept(this)) {
            toVisit.getStatement().accept(this);
        }
    }

    @Override
    public void visit(SetNode toVisit) {
        context.setVariable(
                toVisit.getVariable().accept(this),
                toVisit.getExpression().accept(this)
        );
    }

    @Override
    public boolean visit(LogicalOperatorNode toVisit) {
        final boolean operand1 = toVisit.getOperand1().accept(this);
        switch (toVisit.getType()) {
            case AND: {
                if (operand1) {
                    return operand1 && toVisit.getOperand1().accept(this);
                }
                return false;

            }
            case OR: {
                if (operand1) {
                    return true;
                }
                return operand1 || toVisit.getOperand2().accept(this);
            }
            case NOT:
                return !operand1;
            default:
                throw new RuntimeException("Unknown logical operator.");
        }
    }

    @Override
    public void visit(BlockNode toVisit) {
        final List<Node> children = toVisit.getChildren();
        for (Node child : children) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ConditionalNode toVisit) {
        if (toVisit.getCondition().accept(this)) {
            toVisit.getStatement1().accept(this);
        } else {
            toVisit.getStatement2().accept(this);
        }
    }

    @Override
    public boolean visit(RelationalOperatorNode toVisit) {
        Node<Long> operand1 = toVisit.getOperand1();
        Node<Long> operand2 = toVisit.getOperand2();
        switch (toVisit.getType()) {
            case LT:
                return operand1.accept(this) < operand2.accept(this);
            case GT:
                return operand1.accept(this) > operand2.accept(this);
            case EQ:
                return operand1.accept(this).equals(operand2.accept(this));
        }
        return true;
    }

    @Override
    public void visit(InputNode toVisit) {
        System.out.print("> ");
        long value = scanner.nextLong();
        context.setVariable(toVisit.getInputVariable().accept(this), value);
    }

    @Override
    public void visit(PrintNode toVisit) {
        System.out.println(toVisit.getValueToPrint().accept(this));
    }

    @Override
    public long visit(VariableNode toVisit) {
        String variableId = toVisit.getVariableId();
        if (!context.containVariable(variableId)) {
            throw new RuntimeException("(ERROR in evaluator: Undefined variable b)");
        }
        return context.getVariable(variableId);
    }

    @Override
    public String visit(InputVariableNode toVisit) {
        return toVisit.getVariableId();
    }

    @Override
    public long visit(NumberNode toVisit) {
        return toVisit.getNumber();
    }

    @Override
    public boolean visit(BooleanNode toVisit) {
        return toVisit.getBooleanValue();
    }
}
