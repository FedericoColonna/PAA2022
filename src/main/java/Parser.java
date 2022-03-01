
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String MISSING_LEFT_PARENTHESIS_AT_TOKEN_TEMPLATE = "(ERROR in parser: Missing left parenthesis at token: %s)";
    private static final String PREMATURE_EOI_ERROR_MESSAGE = "(ERROR in parser: Premature end of input)";

    private final List<Token> tokens;
    private int currentIndex;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        currentIndex = 0;
        currentToken = tokens.get(0);
    }

    public RootNode parse() {
        if (look(TokenType.EOI)) {
            throw new RuntimeException(PREMATURE_EOI_ERROR_MESSAGE);
        }
        RootNode root = RootNode.build(parseStatementBlock());
        check(TokenType.EOI, "(ERROR in parser: Expected a eoi, got: %s)");
        return root;
    }

    private Node<Void> parseStatementBlock() {
        Node<Void> syntaxTree;
        if (lookAhead(TokenType.BLOCK)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_PARENTHESIS, String.format(MISSING_LEFT_PARENTHESIS_AT_TOKEN_TEMPLATE, TokenType.prettier(currentToken.type())));
            syntaxTree = parseStatementList();
            checkAndIgnoreCurrentToken(TokenType.RIGHT_PARENTHESIS, PREMATURE_EOI_ERROR_MESSAGE);
        } else {
            syntaxTree = parseStatement();
        }
        return syntaxTree;
    }

    private Node<Void> parseStatementList() {
        //System.out.println("Parsing statement block..");
        checkAndIgnoreCurrentToken(TokenType.BLOCK, String.format("(ERROR in parser: Missing BLOCK at token: %s)", TokenType.prettier(currentToken.type())));
        List<Node<Void>> statements = new ArrayList<>();
        boolean noStatementFound = true;
        while (look(TokenType.LEFT_PARENTHESIS)) {
            noStatementFound = false;
            Node<Void> statement = parseStatement();
            statements.add(statement);
        }
        if (noStatementFound) {
            throw new RuntimeException("(ERROR in parser: Empty BLOCK statement)");
        }
        return BlockNode.build(statements.toArray(new Node[0]));
    }

    private Node<Void> parseStatement() {
        //System.out.println("Parsing statement...");
        checkAndIgnoreCurrentToken(TokenType.LEFT_PARENTHESIS, String.format(MISSING_LEFT_PARENTHESIS_AT_TOKEN_TEMPLATE, TokenType.prettier(currentToken.type())));
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
        checkAndIgnoreCurrentToken(TokenType.RIGHT_PARENTHESIS, String.format("(ERROR in parser: Missing right parenthesis at token: %s)", TokenType.prettier(currentToken.type())));
        return node;
    }


    private Node<Void> parseSet() {
        //System.out.println("Parsing SET...");
        checkAndIgnoreCurrentToken(TokenType.SET, String.format("(ERROR in parser: Missing SET at token: %s)", TokenType.prettier(currentToken.type())));
        return SetNode.build(parseInputVariableId(), parseNumExp());
    }

    private Node<String> parseInputVariableId() {
        //System.out.println("Parsing input VARIABLE_ID...");
        check(TokenType.VARIABLE, String.format("(ERROR in parser: Expected a variable, got: %s)", currentToken.type()));
        String variableId = currentToken.variableId();
        checkAndIgnoreCurrentToken(TokenType.VARIABLE, String.format("(ERROR in parser: Missing VARIABLE at token: %s)", TokenType.prettier(currentToken.type())));
        return InputVariableNode.build(variableId);
    }

    private Node<Long> parseVariableId() {
        //System.out.println("Parsing VARIABLE_ID...");
        check(TokenType.VARIABLE, String.format("(ERROR in parser: Expected a VARIABLE, got: %s)", currentToken.type()));
        String variableId = currentToken.variableId();
        checkAndIgnoreCurrentToken(TokenType.VARIABLE, String.format("(ERROR in parser: Missing VARIABLE at token: %s)", TokenType.prettier(currentToken.type())));
        return VariableNode.build(variableId);
    }

    private Node parseNumExp() {
        //System.out.println("Parsing num expr...");
        if (look(TokenType.LEFT_PARENTHESIS)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_PARENTHESIS, String.format(MISSING_LEFT_PARENTHESIS_AT_TOKEN_TEMPLATE, TokenType.prettier(currentToken.type())));
            Node<Long> operatorNode;
            if (look(TokenType.ADD) || look(TokenType.SUB) || look(TokenType.MUL) || look(TokenType.DIV)) {
                String operator = currentToken.type().toString();
                ignoreCurrentToken();
                operatorNode = OperatorNode.build(OperatorNode.Type.valueOf(operator), parseNumExp(), parseNumExp());
            } else {
                throw new RuntimeException("Syntax error. Operator needed");
            }
            checkAndIgnoreCurrentToken(TokenType.RIGHT_PARENTHESIS, String.format("(ERROR in parser: Missing right parenthesis at token: %s)", TokenType.prettier(currentToken.type())));
            return operatorNode;
        } else {
            if (look(TokenType.VARIABLE)) {
                return parseVariableId();
            } else if (look(TokenType.NUMBER)) {
                return parseNumber();
            } else {
                throw new RuntimeException(String.format("(ERROR in parser: Misplaced token '%s')", TokenType.prettier(currentToken.type())));
            }
        }
    }

    private Node<Long> parseNumber() {
        //System.out.println("Parsing NUMBER...");
        check(TokenType.NUMBER, String.format("(ERROR in parser: Expected a NUMBER, got: %s)", currentToken.type()));
        long number = currentToken.number();
        checkAndIgnoreCurrentToken(TokenType.NUMBER, String.format("(ERROR in parser: Missing NUMBER at token: %s)", TokenType.prettier(currentToken.type())));
        return NumberNode.build(number);
    }

    private Node<Void> parsePrint() {
        //System.out.println("Parsing PRINT...");
        checkAndIgnoreCurrentToken(TokenType.PRINT, String.format("(ERROR in parser: Missing PRINT at token: %s)", TokenType.prettier(currentToken.type())));
        return PrintNode.build(parseNumExp());
    }

    private Node<Void> parseInput() {
        //System.out.println("Parsing INPUT...");
        checkAndIgnoreCurrentToken(TokenType.INPUT, String.format("(ERROR in parser: Missing INPUT at token: %s)", TokenType.prettier(currentToken.type())));
        return InputNode.build(parseInputVariableId());
    }

    private Node<Void> parseIf() {
        //System.out.println("Parsing IF...");
        checkAndIgnoreCurrentToken(TokenType.IF, String.format("(ERROR in parser: Missing IF at token: %s)", TokenType.prettier(currentToken.type())));
        return ConditionalNode.build(parseBoolExpr(), parseStatementBlock(), parseStatementBlock());
    }

    private Node<Void> parseWhile() {
        //System.out.println("Parsing WHILE...");
        checkAndIgnoreCurrentToken(TokenType.WHILE, String.format("(ERROR in parser: Missing WHILE at token: %s)", TokenType.prettier(currentToken.type())));
        return WhileNode.build(parseBoolExpr(), parseStatementBlock());
    }

    private Node<Boolean> parseBoolExpr() {
        //System.out.println("Parsing bool expr...");
        if (look(TokenType.LEFT_PARENTHESIS)) {
            checkAndIgnoreCurrentToken(TokenType.LEFT_PARENTHESIS, String.format(MISSING_LEFT_PARENTHESIS_AT_TOKEN_TEMPLATE, TokenType.prettier(currentToken.type())));
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
                throw new RuntimeException(String.format("(ERROR in parser: Unrecognized operator: '%s')", currentToken.type()));
            }
            checkAndIgnoreCurrentToken(TokenType.RIGHT_PARENTHESIS, String.format("(ERROR in parser: Missing right parenthesis at token: %s)", TokenType.prettier(currentToken.type())));
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

    private void checkAndIgnoreCurrentToken(TokenType expected, String errorMessage) {
        check(expected, errorMessage);
        ignoreCurrentToken();
    }

    private void check(TokenType expected, String errorMessage) {
        if (currentToken.type() != expected) {
            throw new RuntimeException(errorMessage);
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
