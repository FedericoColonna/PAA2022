package common;

import java.util.Locale;

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

    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,

    NUMBER,
    VARIABLE,
    EOI;

    public static TokenType fromString(String input) {
        return TokenType.valueOf(input);
    }

    public static String prettier(TokenType type) {
        if (type == RIGHT_PARENTHESIS) {
            return ")";
        } else if (type == LEFT_PARENTHESIS) {
            return "(";
        }
        //return type.toString().toLowerCase(Locale.ROOT).replaceAll("_", " ");
        return type.toString().replaceAll("_", " ");
    }
}