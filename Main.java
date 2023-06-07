import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "./example_code.txt";
        LexicalAnalyzer lexer = new LexicalAnalyzer(filePath);
        List<Token> tokens = new ArrayList<>();

        Token token;
        while ((token = lexer.getNextToken()) != null) {
            tokens.add(token);
            System.out.println(token);
        }
        System.out.println("\n\n\n------- Tabela de simbolos: -------");
        for (Token t : tokens) {
            System.out.println(t.getType());
        }
    }
}