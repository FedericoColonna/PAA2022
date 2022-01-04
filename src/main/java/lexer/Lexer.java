package lexer;

import common.Token;
import common.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final Pattern LEFT_BRACKET_PATTERN = Pattern.compile("^([(])", Pattern.CASE_INSENSITIVE);
    private static final Pattern RIGHT_BRACKET_PATTERN = Pattern.compile("^\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern WORD_PATTERN = Pattern.compile("^([a-zA-Z]+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SPACE_PATTERN = Pattern.compile("^(\\s+)", Pattern.CASE_INSENSITIVE);

    private final String program;
    private int index;

    public Lexer(String program) {
        this.program = program.strip();
        index = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (hasNext()) {
            Token token = next();
            tokens.add(token);
            System.out.println(token);
        }
        tokens.add(Token.forType(TokenType.EOI));
        return tokens;
    }

    private boolean hasNext() {
        return index < program.length();
    }

    private Token next() {
        String subProgram = program.substring(index);
        Matcher whitespaceMatcher = SPACE_PATTERN.matcher(subProgram);
        if (whitespaceMatcher.find()) {
            index += whitespaceMatcher.end();
            subProgram = program.substring(index);
        }
        if (!hasNext()) {
            return null;
        }
        Matcher leftBracketMatcher = LEFT_BRACKET_PATTERN.matcher(subProgram);
        if (leftBracketMatcher.find()) {
            index += leftBracketMatcher.end();
            return Token.forType(TokenType.LEFT_BRACKET);
        }

        Matcher rightBracketMatcher = RIGHT_BRACKET_PATTERN.matcher(subProgram);
        if (rightBracketMatcher.find()) {
            index += rightBracketMatcher.end();
            return Token.forType(TokenType.RIGHT_BRACKET);
        }

        Matcher wordMatcher = WORD_PATTERN.matcher(subProgram);
        if (wordMatcher.find()) {
            index +=  wordMatcher.end();
            return token(wordMatcher.group());
        }

        Matcher numberMatcher = NUMBER_PATTERN.matcher(subProgram);
        if (numberMatcher.find()) {
            index +=  numberMatcher.end();
            return Token.forNumber(Integer.parseInt(numberMatcher.group()));
        }

        throw new RuntimeException("The program provided contains an unexpected character");
    }

    private Token token(String value) {
        try {
            TokenType type = TokenType.fromString(value);
            return Token.forType(type);
        } catch (IllegalArgumentException e) {
            return Token.forVariableId(value);
        }
    }
}
