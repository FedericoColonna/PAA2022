package common.visitors;

import common.Context;
import common.nodes.*;

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
    public int visit(OperatorNode toVisit) {
        switch (toVisit.getType()) {
            case ADD:
                return toVisit.getOperand1().accept(this) + toVisit.getOperand2().accept(this);
            case SUB:
                return toVisit.getOperand1().accept(this) - toVisit.getOperand2().accept(this);
            case MUL:
                return toVisit.getOperand1().accept(this) * toVisit.getOperand2().accept(this);
            case DIV:
                return toVisit.getOperand1().accept(this) / toVisit.getOperand2().accept(this);
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
        Node<Boolean> operand1 = toVisit.getOperand1();
        switch (toVisit.getType()) {
            case AND: {
                Node<Boolean> operand2 = toVisit.getOperand2();
                return operand1.accept(this) && operand2.accept(this);
            }
            case OR: {
                Node<Boolean> operand2 = toVisit.getOperand2();
                return operand1.accept(this) || operand2.accept(this);
            }
            case NOT:
                return !operand1.accept(this);
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
        Node<Integer> operand1 = toVisit.getOperand1();
        Node<Integer> operand2 = toVisit.getOperand2();
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
        int value = scanner.nextInt();
        context.setVariable(toVisit.getInputVariable().accept(this), value);
    }

    @Override
    public void visit(PrintNode toVisit) {
        System.out.println(toVisit.getValueToPrint().accept(this));
    }

    @Override
    public Integer visit(VariableNode toVisit) {
        return context.getVariable(toVisit.getVariableId());
    }

    @Override
    public String visit(InputVariableNode toVisit) {
        return toVisit.getVariableId();
    }

    @Override
    public int visit(NumberNode toVisit) {
        return toVisit.getNumber();
    }

    @Override
    public boolean visit(BooleanNode toVisit) {
        return toVisit.getBooleanValue();
    }
}