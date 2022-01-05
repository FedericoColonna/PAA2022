package parser;

import common.Token;
import common.TokenType;
import common.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentIndex;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        currentIndex = 0;
        currentToken = tokens.get(0);
    }

    public RootNode parse() {
        RootNode root = RootNode.build(parseStatementBlock());
        check(TokenType.EOI);
        return root;
    }

    private Node<Void> parseStatementBlock() {
        Node<Void> syntaxTree;
        if (lookAhead(TokenType.BLOCK)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_BRACKET);
            syntaxTree = parseStatementList();
            checkAndIgnoreCurrentToken(TokenType.RIGHT_BRACKET);
        } else {
            syntaxTree = parseStatement();
        }
        return syntaxTree;
    }

    private Node<Void> parseStatementList() {
        System.out.println("Parsing statement block..");
        checkAndIgnoreCurrentToken(TokenType.BLOCK);
        List<Node<Void>> statements = new ArrayList<>();
        while (look(TokenType.LEFT_BRACKET)) {
            Node<Void> statement = parseStatement();
            statements.add(statement);
        }
        return BlockNode.build(statements.toArray(new Node[0]));
    }

    private Node<Void> parseStatement() {
        System.out.println("Parsing statement...");
        checkAndIgnoreCurrentToken(TokenType.LEFT_BRACKET);
        Node<Void> node;
        switch (currentToken.type()) {
            case SET:
                node = parseSet();
                break;
            case PRINT:
                node = parsePrint();
                break;
            case INPUT:
                node = parseInput();
                break;
            case IF:
                node = parseIf();
                break;
            case WHILE:
                node = parseWhile();
                break;
            default:
                throw new RuntimeException(String.format("Syntax error! Found %s, but something among SET, PRINT, INPUT, IF, and WHILE is expected.", currentToken));
        }
        checkAndIgnoreCurrentToken(TokenType.RIGHT_BRACKET);
        return node;
    }


    private Node<Void> parseSet() {
        System.out.println("Parsing SET...");
        checkAndIgnoreCurrentToken(TokenType.SET);
        return SetNode.build(parseInputVariableId(), parseNumExp());
    }

    private Node<String> parseInputVariableId() {
        System.out.println("Parsing input VARIABLE_ID...");
        check(TokenType.VARIABLE_ID);
        String variableId = currentToken.variableId();
        checkAndIgnoreCurrentToken(TokenType.VARIABLE_ID);
        return InputVariableNode.build(variableId);
    }

    private Node<Integer> parseVariableId() {
        System.out.println("Parsing VARIABLE_ID...");
        check(TokenType.VARIABLE_ID);
        String variableId = currentToken.variableId();
        checkAndIgnoreCurrentToken(TokenType.VARIABLE_ID);
        return VariableNode.build(variableId);
    }

    private Node parseNumExp() {
        System.out.println("Parsing num expr...");
        if (look(TokenType.LEFT_BRACKET)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_BRACKET);
            Node<Integer> operatorNode;
            if (look(TokenType.ADD) || look(TokenType.SUB) || look(TokenType.MUL) || look(TokenType.DIV)) {
                String operator = currentToken.type().toString();
                ignoreCurrentToken();
                operatorNode = OperatorNode.build(OperatorNode.Type.valueOf(operator), parseNumExp(), parseNumExp());
            } else {
                throw new RuntimeException("Syntax error. Operator needed");
            }
            checkAndIgnoreCurrentToken(TokenType.RIGHT_BRACKET);
            return operatorNode;
        } else {
            if (look(TokenType.VARIABLE_ID)) {
                return parseVariableId();
            } else if (look(TokenType.NUMBER)) {
                return parseNumber();
            } else {
                throw new RuntimeException("Syntax error. Variable id or number needed.");
            }
        }
    }

    private Node<Integer> parseNumber() {
        System.out.println("Parsing NUMBER...");
        check(TokenType.NUMBER);
        int number = currentToken.number();
        checkAndIgnoreCurrentToken(TokenType.NUMBER);
        return NumberNode.build(number);
    }

    private Node<Void> parsePrint() {
        System.out.println("Parsing PRINT...");
        checkAndIgnoreCurrentToken(TokenType.PRINT);
        return PrintNode.build(parseNumExp());
    }

    private Node<Void> parseInput() {
        System.out.println("Parsing INPUT...");
        checkAndIgnoreCurrentToken(TokenType.INPUT);
        return InputNode.build(parseInputVariableId());
    }

    private Node<Void> parseIf() {
        return null;
    }

    private Node<Void> parseWhile() {
        System.out.println("Parsing WHILE...");
        checkAndIgnoreCurrentToken(TokenType.WHILE);
        return WhileNode.build(parseBoolExpr(), parseStatementBlock());
    }

    private Node<Boolean> parseBoolExpr() {
        System.out.println("Parsing bool expr...");
        if (look(TokenType.LEFT_BRACKET)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_BRACKET);
            Node<Boolean> logicalOperatorNode;
            if (look(TokenType.AND) || look(TokenType.OR)) {
                String operator = currentToken.type().toString();
                ignoreCurrentToken();
                logicalOperatorNode = LogicalOperatorNode.build(LogicalOperatorNode.Type.valueOf(operator), parseBoolExpr(), parseBoolExpr());
            } else if (look(TokenType.GT) || look(TokenType.LT) || look(TokenType.EQ)){
                String operator = currentToken.type().toString();
                ignoreCurrentToken();
                logicalOperatorNode = RelationalOperatorNode.build(RelationalOperatorNode.Type.valueOf(operator), parseNumExp(), parseNumExp());
            } else if (look(TokenType.NOT)){
                ignoreCurrentToken();
                logicalOperatorNode = LogicalOperatorNode.build(LogicalOperatorNode.Type.NOT, parseBoolExpr());
            } else {
                throw new RuntimeException("Syntax error. Boole Operator, Relational Operator or NOT needed");
            }
            checkAndIgnoreCurrentToken(TokenType.RIGHT_BRACKET);
            return logicalOperatorNode;
        } else {
            if (look(TokenType.TRUE) || look(TokenType.FALSE)) {
                String booleanValue = currentToken.type().toString();
                ignoreCurrentToken();
                return BooleanNode.build(Boolean.parseBoolean(booleanValue));
            } else {
                throw new RuntimeException("Syntax error. TRUE or FALSE is needed.");
            }
        }    }

    private void checkAndIgnoreCurrentToken(TokenType expected) {
        if (currentToken.type() == expected) {
            ignoreCurrentToken();
        } else {
            throw new RuntimeException(String.format("Syntax error, found token %s, but token type %s is expected", currentToken, expected));
        }
    }

    private void check(TokenType expected) {
        if (currentToken.type() != expected) {
            throw new RuntimeException(String.format("Syntax error, found token %s, but token type %s is expected", currentToken, expected));
        }
    }

    private boolean look(TokenType type) {
        return tokens.get(currentIndex).type() == type;
    }

    private boolean lookAhead(TokenType type) {
        nextIndexIsNotInRange();
        return tokens.get(currentIndex + 1).type() == type;
    }

    private void ignoreCurrentToken() {
        nextIndexIsNotInRange();
        currentToken = tokens.get(++currentIndex);
    }

    private void nextIndexIsNotInRange() {
        if (currentIndex + 1 >= tokens.size()) {
            throw new IllegalStateException(String.format("current index is %s, but total tokens are %s.", currentIndex + 1, tokens.size()));
        }
    }
}
