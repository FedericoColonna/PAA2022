package common;

import java.util.List;

public class SyntaxTreeNode {
    private final List<SyntaxTreeNode> children;
    private final Tag tag;
    private final String stringValue;
    private final Integer number;

    public static SyntaxTreeNode createBlockNode(SyntaxTreeNode...nodes) {
        return new SyntaxTreeNode(Tag.BLOCK, null, null, nodes);
    }

    public static SyntaxTreeNode createWhileNode(SyntaxTreeNode boolExpr, SyntaxTreeNode stmtBlock) {
        return new SyntaxTreeNode(Tag.WHILE, null, null, boolExpr, stmtBlock);
    }

    public static SyntaxTreeNode createIfNode(SyntaxTreeNode condition, SyntaxTreeNode body, SyntaxTreeNode elseBody) {
        return new SyntaxTreeNode(Tag.IF, null, null, condition, body, elseBody);
    }

    public static SyntaxTreeNode createInputNode(SyntaxTreeNode variableId) {
        return new SyntaxTreeNode(Tag.INPUT, null, null, variableId);
    }

    public static SyntaxTreeNode createSetNode(SyntaxTreeNode variableId, SyntaxTreeNode numExpr) {
        return new SyntaxTreeNode(Tag.SET, null, null, variableId, numExpr);
    }

    public static SyntaxTreeNode createPrintNode(SyntaxTreeNode variableId) {
        return new SyntaxTreeNode(Tag.PRINT, null, null, variableId);
    }

    public static SyntaxTreeNode createBooleanOperatorNode(String operator, SyntaxTreeNode boolExpr1, SyntaxTreeNode boolExpr2) {
        return new SyntaxTreeNode(Tag.BOOLEAN_OPERATOR, operator, null, boolExpr1, boolExpr2);
    }

    public static SyntaxTreeNode createNotOperatorNode(SyntaxTreeNode boolExpr) {
        return new SyntaxTreeNode(Tag.BOOLEAN_OPERATOR, "NOT", null, boolExpr);}

    public static SyntaxTreeNode createRelationalOperatorNode(String operator, SyntaxTreeNode boolExpr1, SyntaxTreeNode boolExpr2) {
        return new SyntaxTreeNode(Tag.RELATIONAL_OPERATOR, operator, null, boolExpr1, boolExpr2);
    }

    public static SyntaxTreeNode createOperatorNode(String operator, SyntaxTreeNode operand1, SyntaxTreeNode operand2) {
        return new SyntaxTreeNode(Tag.OPERATOR, operator, null, operand1, operand2);
    }

    public static SyntaxTreeNode createAtomNode(String stringValue) {
        return new SyntaxTreeNode(Tag.ATOM, stringValue, null);
    }

    public static SyntaxTreeNode createAtomNode(int number) {
        return new SyntaxTreeNode(Tag.ATOM, null, number);
    }

    private SyntaxTreeNode(Tag tag, String stringValue, Integer number, SyntaxTreeNode... nodesArray) {
        this.tag = tag;
        this.stringValue = stringValue;
        this.number = number;
        children = List.of(nodesArray);
    }

    public List<SyntaxTreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "SyntaxTreeNode{" +
                "tag=" + tag +
                ", stringValue='" + stringValue + '\'' +
                ", number=" + number +
                '}';
    }

    private enum Tag {
        BLOCK,
        WHILE,
        IF,
        PRINT,
        INPUT,
        SET,
        OPERATOR,
        BOOLEAN_OPERATOR,
        RELATIONAL_OPERATOR,
        ATOM
    }
}
