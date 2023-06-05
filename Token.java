public class Token {
    public enum TokenType {
        ABSTRACT,
        ASSERT,
        BOOLEAN, // DONE
        BREAK,
        BYTE,
        CASE,
        CATCH,
        CHAR, // DONE
        CLASS, // DONE
        CONST,
        CONTINUE,
        COMMENTS, // DONE
        DEFAULT,
        DO,
        DOUBLE,
        ELSE, // DONE
        ENUM,
        EXTENDS,
        FINAL,
        FINALLY,
        FLOAT, // DONE
        FOR, // DONE
        GOTO,
        IF, // DONE
        IMPLEMENTS,
        IMPORT, // DONE
        INSTANCEOF,
        INT, // DONE
        INTERFACE,
        LONG,
        NATIVE,
        NEW,
        NUMBER, // DONE
        OPERATORS, // DONE
        PACKAGE,
        PRIVATE, // DONE
        PROTECTED,
        PUBLIC, // DONE
        RETURN, // DONE
        SHORT,
        STATIC, // DONE
        STRICTFP,
        STRING, // DONE
        SUPER,
        SWITCH,
        SYNCHRONIZED,
        THIS,
        THROW,
        THROWS,
        TRANSIENT,
        TRY,
        VOID, // DONE
        VOLATILE,
        WHILE, // DONE
        WHITESPACE, // DONE
        IDENTIFIER, // DONE
        SYMBOL // DONE
    }  

    private TokenType type;
    private String lexeme;
    private int lineNumber;
    
    public Token(TokenType type, String lexeme, int lineNumber) {
        this.type = type;
        this.lexeme = lexeme;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "[" + type + ", '" + lexeme + "', Line " + lineNumber + "]";
    }
}