package common;

public enum TokenType {
    // Keywords
    BLOCK,
    INPUT,
    PRINT,
    SET,
    WHILE,
    IF,
    GT,
    LT,
    EQ,
    AND,
    OR,
    NOT,
    TRUE,
    FALSE,
    ADD,
    SUB,
    MUL,
    DIV,

    LEFT_BRACKET,
    RIGHT_BRACKET,

    NUMBER,
    VARIABLE_ID,
    EOI;

    public static TokenType fromString(String input) {
        return TokenType.valueOf(input);
    }
}