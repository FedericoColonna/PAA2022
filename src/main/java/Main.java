
import common.Context;
import common.Token;
import common.nodes.RootNode;
import common.visitors.EvaluationVisitor;
import evaluator.Evaluator;
import lexer.Lexer;
import parser.Parser;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing input file");
        }
        String program = readString(args[0]);
        //System.out.println(program);

        Lexer lexer = new Lexer(program);
        List<Token> tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);
        RootNode syntaxTree = parser.parse();
        Evaluator evaluator = new Evaluator(syntaxTree, new EvaluationVisitor(new Context()));
        evaluator.evaluate();
    }

    private static String readString(String filename) {
        File file = readFileFromResource(filename);
        StringBuilder retval = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                retval.append(line).append("\n");
            }
            return retval.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File readFileFromResource(String filename) {
        URL resource = Main.class.getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
