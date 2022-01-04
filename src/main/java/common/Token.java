package common;

public class Token {
    private final TokenType type;
    private final String variableId;
    private final Integer number;

    public static Token forVariableId(String variableId) {
        return new Token(TokenType.VARIABLE_ID, variableId, null);
    }

    public static Token forNumber(int number) {
        return new Token(TokenType.NUMBER, null, number);
    }

    public static Token forType(TokenType type) {
        return new Token(type, null, null);
    }

    private Token(TokenType type, String variableId, Integer number) {
        this.type = type;
        this.variableId = variableId;
        this.number = number;
    }

    public TokenType type() {
        return type;
    }

    public String variableId() {
        if (type() != TokenType.VARIABLE_ID) {
            throw new IllegalStateException("This token is not a VARIABLE_ID type.");
        }
        return variableId;
    }

    public int number() {
        if (type() != TokenType.NUMBER) {
            throw new IllegalStateException("This token is not a NUMBER type.");
        }
        return number;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", variableId='" + variableId + '\'' +
                ", number=" + number +
                '}';
    }
}
