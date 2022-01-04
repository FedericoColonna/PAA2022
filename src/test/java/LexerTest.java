import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LexerTest {
    private static final String LEFT_BRACKET_REGEX_1 = "\\(";
    private static final String LEFT_BRACKET_REGEX_2 = "[(]";

    @BeforeEach
    void setUp() {
    }

    @Test
    void itTests() {
        Pattern pattern = Pattern.compile(LEFT_BRACKET_REGEX_1, Pattern.CASE_INSENSITIVE);
        assertThat(pattern.matcher("(pippo)").find()).isTrue();
        //assertThat("(p".matches(LEFT_BRACKET_REGEX_1)).isTrue();
        //assertThat("(PIPPO)".matches(LEFT_BRACKET_REGEX_2)).isTrue();
    }
}