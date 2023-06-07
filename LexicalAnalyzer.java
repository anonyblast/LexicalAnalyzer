import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class LexicalAnalyzer {
    private BufferedReader reader;
    private String currentLine;
    private int lineNumber;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    public LexicalAnalyzer(String filePath) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
            lineNumber = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Token getNextToken() {        
        if (reader == null) {
            return null;
        }

        try {
            while (true) {
                if (currentLine == null || currentLine.isEmpty()) {
                    currentLine = reader.readLine();
                    lineNumber++;
                    if (currentLine == null) {
                        reader.close();
                        return null;
                    }
                }
                
                currentLine = currentLine.trim();
                
                // System.out.println(ANSI_RED_BACKGROUND + currentLine + ANSI_RESET);
                System.out.println("\nINPUT:\t" + currentLine);

                // regex for variables names
                String regex = "(\\w+)(?=\\s*[=;(){}<>])";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(currentLine);

                // regex for string values
                String regexOnlyStringValue = "\"([^\"]*)\"";
                Pattern patternOnlyStringValue = Pattern.compile(regexOnlyStringValue);
                Matcher matcherOnlyStringValue = patternOnlyStringValue.matcher(currentLine);

                // regex for numeric values
                String regexOnlyNumericValue = "-?\\d+(\\.\\d+)?";
                Pattern patternOnlyNumericValue = Pattern.compile(regexOnlyNumericValue);

                // Check for different token types
                if (currentLine.startsWith("//")) {
                    currentLine = null;
                } else if (currentLine.isEmpty()) {
                    currentLine = null;
                } else if (currentLine.toUpperCase().startsWith("PUBLIC")) {
                    currentLine = currentLine.substring(6).trim();
                    return new Token(Token.TokenType.PUBLIC, "public", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("PRIVATE")) {
                    currentLine = currentLine.substring(7).trim();
                    return new Token(Token.TokenType.PRIVATE, "private", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("CLASS")) {
                    currentLine = currentLine.substring(5).trim();
                    return new Token(Token.TokenType.CLASS, "class", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("STATIC")) {
                    currentLine = currentLine.substring(6).trim();
                    return new Token(Token.TokenType.STATIC, "static", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("VOID")) {
                    currentLine = currentLine.substring(4).trim();
                    return new Token(Token.TokenType.VOID, "void", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("INT")) {
                    currentLine = currentLine.substring(3).trim();
                    return new Token(Token.TokenType.INT, "int", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("FLOAT")) {
                    currentLine = currentLine.substring(5).trim();
                    return new Token(Token.TokenType.FLOAT, "float", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("CHAR")) {
                    currentLine = currentLine.substring(4).trim();
                    return new Token(Token.TokenType.CHAR, "char", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("BOOLEAN")) {
                    currentLine = currentLine.substring(7).trim();
                    return new Token(Token.TokenType.BOOLEAN, "boolean", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("STRING")) {
                    currentLine = currentLine.substring(6).trim();
                    return new Token(Token.TokenType.STRING, "String", lineNumber);
                } else if (currentLine.startsWith("=") || currentLine.startsWith("{") || currentLine.startsWith("}") ||
                           currentLine.startsWith("(") || currentLine.startsWith(")") || 
                           currentLine.startsWith("[") || currentLine.startsWith("]") || 
                           currentLine.startsWith(";")) {
                    String symbol = currentLine.substring(0, 1);
                    currentLine = currentLine.substring(1).trim();
                    return new Token(Token.TokenType.SYMBOL, symbol, lineNumber);
                } else if (currentLine.toUpperCase().startsWith("IMPORT")) {
                    currentLine = currentLine.substring(6).trim();
                    return new Token(Token.TokenType.IMPORT, "import", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("IF")) {
                    currentLine = currentLine.substring(2).trim();
                    return new Token(Token.TokenType.IF, "if", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("ELSE")) {
                    currentLine = currentLine.substring(4).trim();
                    return new Token(Token.TokenType.ELSE, "else", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("TRUE")){
                    currentLine = currentLine.substring(4).trim();
                    return new Token(Token.TokenType.BOOLEAN, "Boolean:value", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("FALSE")){
                    currentLine = currentLine.substring(5).trim();
                    return new Token(Token.TokenType.BOOLEAN, "Boolean:value", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("WHILE")) {
                    currentLine = currentLine.substring(5).trim();
                    return new Token(Token.TokenType.WHILE, "while", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("FOR")) {
                    currentLine = currentLine.substring(3).trim();
                    return new Token(Token.TokenType.FOR, "for", lineNumber);
                } else if (currentLine.toUpperCase().startsWith("RETURN")) {
                    currentLine = currentLine.substring(6).trim();
                    return new Token(Token.TokenType.RETURN, "return", lineNumber);
                } else if (matcher.find()){
                    currentLine = currentLine.substring(matcher.group().length()).trim();
                    Matcher matcherOnlyNumericValue = patternOnlyNumericValue.matcher(matcher.group());
                    if (matcherOnlyNumericValue.find()) 
                        return new Token(Token.TokenType.NUMBER, matcherOnlyNumericValue.group(), lineNumber);
                    return new Token(Token.TokenType.IDENTIFIER, matcher.group(), lineNumber);
                } else if (matcherOnlyStringValue.find()){
                    currentLine = currentLine.substring(matcherOnlyStringValue.group(1).length() + 2).trim();
                    return new Token(Token.TokenType.STRING, "String:value", lineNumber);
                } else {
                    System.err.println("Lexical Error: Invalid token at line " + lineNumber + "\t" + currentLine);
                    currentLine = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
