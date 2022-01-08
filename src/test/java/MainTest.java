import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }
    
    @Test
    public void itFailsDivisionByZero(){
        Main.process("samples/input/FAIL_DivisionByZero.txt");
        String expected = Main.readString("samples/output/FAIL_DivisionByZero.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsEmptyBlock(){
        Main.process("samples/input/FAIL_EmptyBlock.txt");
        String expected = Main.readString("samples/output/FAIL_EmptyBlock.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsEmptyProgram(){
        Main.process("samples/input/FAIL_EmptyProgram.txt");
        String expected = Main.readString("samples/output/FAIL_EmptyProgram.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsLexicalError(){
        Main.process("samples/input/FAIL_LexicalError.txt");
        String expected = Main.readString("samples/output/FAIL_LexicalError.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMismatchedParenthesis1(){
        Main.process("samples/input/FAIL_MismatchedParenthesis1.txt");
        String expected = Main.readString("samples/output/FAIL_MismatchedParenthesis1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMismatchedParenthesis2(){
        Main.process("samples/input/FAIL_MismatchedParenthesis2.txt");
        String expected = Main.readString("samples/output/FAIL_MismatchedParenthesis2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMisplacedBool(){
        Main.process("samples/input/FAIL_MisplacedBool.txt");
        String expected = Main.readString("samples/output/FAIL_MispacedBool.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMissingElse(){
        Main.process("samples/input/FAIL_MissingElse.txt");
        String expected = Main.readString("samples/output/FAIL_MissingElse.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMissingGuard(){
        Main.process("samples/input/FAIL_MissingGuard.txt");
        String expected = Main.readString("samples/output/FAIL_MissingGuard.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMissingOperand(){
        Main.process("samples/input/FAIL_MissingOperand.txt");
        String expected = Main.readString("samples/output/FAIL_MissingOperand.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMultipleErrors1(){
        Main.process("samples/input/FAIL_MultipleErrors1.txt");
        String expected = Main.readString("samples/output/FAIL_MultipleErrors1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsMultipleErrors2(){
        Main.process("samples/input/FAIL_MultipleErrors2.txt");
        String expected = Main.readString("samples/output/FAIL_MultipleErrors2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsUndeclaredVariable(){
        Main.process("samples/input/FAIL_UndeclaredVariable.txt");
        String expected = Main.readString("samples/output/FAIL_UndeclaredVariable.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsWrongIdentifier1(){
        Main.process("samples/input/FAIL_WrongIdentifier1.txt");
        String expected = Main.readString("samples/output/FAIL_WrongIdentifier1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itFailsWrongIdentifier2(){
        Main.process("samples/input/FAIL_WrongIdentifier2.txt");
        String expected = Main.readString("samples/output/FAIL_WrongIdentifier2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesBoolExpr(){
        Main.process("samples/input/PASS_BoolExpr.txt");
        String expected = Main.readString("samples/output/PASS_BoolExpr.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesExponential(){
        Main.process("samples/input/PASS_Exponential.txt");
        String expected = Main.readString("samples/output/PASS_Exponential.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesFactorial(){
        Main.process("samples/input/PASS_Factorial.txt");
        String expected = Main.readString("samples/output/PASS_Factorial.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesFibonacci1(){
        Main.process("samples/input/PASS_Fibonacci1.txt");
        String expected = Main.readString("samples/output/PASS_Fibonacci1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesFibonacci2(){
        Main.process("samples/input/PASS_Fibonacci2.txt");
        String expected = Main.readString("samples/output/PASS_Fibonacci2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesFibonacci3(){
        Main.process("samples/input/PASS_Fibonacci3.txt");
        String expected = Main.readString("samples/output/PASS_Fibonacci3.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesLogarithm1(){
        Main.process("samples/input/PASS_Logarithm1.txt");
        String expected = Main.readString("samples/output/PASS_Logarithm1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesLogarithm2(){
        Main.process("samples/input/PASS_Logarithm2.txt");
        String expected = Main.readString("samples/output/PASS_Logarithm2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesLogarithm3(){
        Main.process("samples/input/PASS_Logarithm3.txt");
        String expected = Main.readString("samples/output/PASS_Logarithm3.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesLogarithm4(){
        Main.process("samples/input/PASS_Logarithm4.txt");
        String expected = Main.readString("samples/output/PASS_Logarithm4.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesManyPrimes(){
        Main.process("samples/input/PASS_ManyPrimes.txt");
        String expected = Main.readString("samples/output/PASS_ManyPrimes.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesModulo(){
        Main.process("samples/input/PASS_Modulo.txt");
        String expected = Main.readString("samples/output/PASS_Modulo.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesPolyRoots1(){
        Main.process("samples/input/PASS_PolyRoots1.txt");
        String expected = Main.readString("samples/output/PASS_PolyRoots1.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesPolyRoots2(){
        Main.process("samples/input/PASS_PolyRoots2.txt");
        String expected = Main.readString("samples/output/PASS_PolyRoots2.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesPrime(){
        Main.process("samples/input/PASS_Prime.txt");
        String expected = Main.readString("samples/output/PASS_Prime.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesShortCircuit(){
        Main.process("samples/input/PASS_ShortCircuit.txt");
        String expected = Main.readString("samples/output/PASS_ShortCircuit.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    //@Test
    public void itPassesSimpleInput(){
        String data = "15\n3\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Main.process("samples/input/PASS_SimpleInput.txt");
            String expected = Main.readString("samples/output/PASS_SimpleInput.out");
            assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void itPassesSimpleSequence(){
        Main.process("samples/input/PASS_SimpleSequence.txt");
        String expected = Main.readString("samples/output/PASS_SimpleSequence.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }

    @Test
    public void itPassesSimpleStatement(){
        Main.process("samples/input/PASS_SimpleStatement.txt");
        String expected = Main.readString("samples/output/PASS_SimpleStatement.out");
        assertThat(byteArrayOutputStream.toString()).isEqualTo(expected);
    }
}