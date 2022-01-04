import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
//    private static final List<String> TOKENS = List.of(
//            "(", "BLOCK", "(", "INPUT", "n", ")",
//            "(", "SET", "result", "1", ")", "(",
//            "WHILE", "(", "GT", "n", "0", ")",
//            "(", "BLOCK", "(", "SET", "result",
//            "(", "MUL", "result", "n", ")", ")",
//            "(", "SET", "n", "(", "SUB", "n", "1",
//            ")", ")", ")", ")", "(", "PRINT",
//            "result", ")", ")"
//    );
//
//    @Test
//    void itGivesCorrectIndexClosingBracket() {
//        final parser.Parser parser = new parser.Parser(TOKENS);
//
//        assertThat(parser.indexClosingBracket(0)).isEqualTo(44);
//        assertThat(parser.indexClosingBracket(2)).isEqualTo(5);
//        assertThat(parser.indexClosingBracket(11)).isEqualTo(39);
//    }
//
//    @Test
//    void itThrowsIfIndexNotPointingToOpeningBracket() {
//        final parser.Parser parser = new parser.Parser(TOKENS);
//
//        assertThatThrownBy(() -> parser.indexClosingBracket(1))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @Test
//    void itThrowsIfMissingClosingBracket() {
//        final parser.Parser parser = new parser.Parser(TOKENS.subList(0, TOKENS.size() - 1));
//
//        assertThatThrownBy(() -> parser.indexClosingBracket(0))
//                .isInstanceOf(IllegalStateException.class);
//    }
}