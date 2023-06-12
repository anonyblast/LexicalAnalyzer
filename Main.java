/** 
 *  TO DO: Esse projeto consiste na implementação de um Analisador Léxico para o estudo dos AFDs 
 *  (Autômato Finito Deterministico), escolhendo uma linguagem qualquer como referência (ou criar uma do zero) 
 *  para ser analisada.
 * 
 *  Linguagem escolhida para a análise : JAVA
 *  Linguagem escolhida para ser analisada : JAVA
 * 
 *  A partir de um arquivo fonte, o código faz a leitura de cada linha individualmente, percorrendo-a e eliminando
 *  os tokens válidos encontrados segundo um Enum (onde estão armazenados os tokens - Token.java).
 * 
 *  Date : June, 29 2023
 *  Author : Gustavo Iafelix
 * 
 * */

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
        System.out.println("Valor\t|\tTipo");
        System.out.println("-----------------------------------\n");
        for (Token t : tokens) {
            System.out.println(t.getLexeme() + "\t|\t" + t.getType());
        }
    }
}