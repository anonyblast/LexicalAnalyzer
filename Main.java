public class Main {
    public static void main(String[] args) {
        String filePath = "./example_code.txt";
        LexicalAnalyzer lexer = new LexicalAnalyzer(filePath);

        Token token;
        while ((token = lexer.getNextToken()) != null) {
            System.out.println(token);
        }
    }
}