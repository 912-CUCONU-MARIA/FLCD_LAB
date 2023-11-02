package model;

import util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Scanner {

    private final SymbolTable symbolTable = new SymbolTable(11);
    private final ArrayList<Pair<String, Pair<Integer, Integer>>> pif = new ArrayList<>();

    private String program;
    private final ArrayList<String> tokens = new ArrayList<>();

    private Integer index = 0;

    private Integer currentLine = 1;

    public Scanner() {
        try {
            readTokens();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readTokens() throws IOException {
        File file = new File("src/main/resources/token.in.txt");
        BufferedReader br = Files.newBufferedReader(file.toPath());
        String line;
        while ((line = br.readLine()) != null) {
            tokens.add(line);
        }
        // Sort tokens by length in descending order
        tokens.sort((o1, o2) -> Integer.compare(o2.length(), o1.length()));
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void scanProgram(String programFileName) {
        try {
            Path file = Path.of("src/main/resources/" + programFileName);
            setProgram(Files.readString(file));

            while (index < program.length()) {
                nextToken();
            }

            FileWriter fileWriter = new FileWriter("PIF" + programFileName.replace(".txt", ".out"));
            for (var pair : pif) {
                String formattedString = String.format("%-15s -> (%-3d, %-3d)\n",
                        pair.getFirst(),
                        pair.getSecond().getFirst(),
                        pair.getSecond().getSecond());
                fileWriter.write(formattedString);
            }

            fileWriter.close();
            fileWriter = new FileWriter("ST" + programFileName.replace(".txt", ".out"));
            fileWriter.write(symbolTable.toString());
            fileWriter.close();
            System.out.println("Lexically correct");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void nextToken() throws Exception {
        if (index == program.length()) {
            return;
        }

        // Skipping white spaces and new lines
        while (index < program.length() && Character.isWhitespace(program.charAt(index))) {
            if (program.charAt(index) == '\n') {
                 currentLine++;
            }
            index++;
        }
        if (treatStringConstant()) {
            return;
        }
        if (treatIntConstant()) {
            return;
        }
        if (treatFromTokenList()) {
            return;
        }
        if (treatIdentifier()) {
            return;
        }
        throw new ScannerException("Lexical error: invalid token " + program.charAt(index) + " at line " + currentLine);
    }

    private boolean treatIdentifier() throws Exception {
        if (!Character.isLetter(program.charAt(index))) {
            return false;
        }

        int start = index;
        index++;

        while (index < program.length() && (Character.isLetterOrDigit(program.charAt(index)))) {
            index++;
        }

        String identifier = program.substring(start, index);

        Pair<Integer, Integer> sTPosition;
        if (!symbolTable.containsIdentifier(identifier)) {
            sTPosition = symbolTable.addIdentifier(identifier);
        } else {
            sTPosition = symbolTable.getPositionIdentifier(identifier);
        }
        pif.add(new Pair<>("identifier", sTPosition));

        return true;
    }

    private boolean treatStringConstant() throws Exception {
        if (program.charAt(index) != '"') {
            return false;
        }

        int start = index;
        index++;

        while (index < program.length() && program.charAt(index) != '"') {
            index++;
        }

        if (index == program.length()) {
            throw new ScannerException("Unclosed string literal starting at index " + start);
        }

        index++; // skip closing "

        String stringConstant = program.substring(start, index);

        Pair<Integer, Integer> sTPosition;
        if (!symbolTable.containsStringConstant(stringConstant)) {
            sTPosition = symbolTable.addStringConstant(stringConstant);
        } else {
            sTPosition = symbolTable.getPositionStringConstant(stringConstant);
        }
        pif.add(new Pair<>("constant", sTPosition));

        return true;
    }

    private boolean treatIntConstant() throws Exception {
        int start = index;
        if (index < program.length() && program.charAt(index) == '-' &&
                (index + 1) < program.length() && Character.isDigit(program.charAt(index + 1))) {
            index++;
        }

        if (!Character.isDigit(program.charAt(index))) {
            return false;
        }

        index++;

        while (index < program.length() && Character.isDigit(program.charAt(index))) {
            index++;
        }

        if (index < program.length() && Character.isLetter(program.charAt(index))) {
            throw new ScannerException("Lexical error: invalid identifier at line " + currentLine + ", index " + start);
        }

        String intConstantString = program.substring(start , index);
        Integer intConstant = Integer.valueOf(intConstantString);

        Pair<Integer, Integer> sTPosition;
        if (!symbolTable.containsIntConstant(intConstant)) {
            sTPosition = symbolTable.addIntConstant(intConstant);
        } else {
            sTPosition = symbolTable.getPositionIntConstant(intConstant);
        }
        pif.add(new Pair<>("constant", sTPosition));

        return true;
    }

    private boolean treatFromTokenList() {
        for (String token : tokens) {
            if (program.startsWith(token, index)) {
                index += token.length();

                pif.add(new Pair<>(token, new Pair<>(-1, -1)));

                return true;
            }
        }
        return false;
    }

}
