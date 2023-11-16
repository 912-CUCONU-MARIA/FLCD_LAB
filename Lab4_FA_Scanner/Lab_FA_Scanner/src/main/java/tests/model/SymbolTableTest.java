package tests.model;

import model.SymbolTable;
import util.Pair;

public class SymbolTableTest {

    private static SymbolTable symbolTable = new SymbolTable(101);

    public static void testSymbolTable(){
        try {
            addIdentifier("abc");
            assertPosition("abc", symbolTable.getPositionIdentifier("abc"), new Pair<>(92, 0));

            addIdentifier("c");
            assertPosition("c", symbolTable.getPositionIdentifier("c"), new Pair<>(99, 0));

            addIdentifier("bc");
            assertPosition("bc", symbolTable.getPositionIdentifier("bc"), new Pair<>(96, 0));

            addIdentifier("cb");
            assertPosition("cb", symbolTable.getPositionIdentifier("cb"), new Pair<>(96, 1));

            addIntConstant(2);
            assertPosition(2, symbolTable.getPositionConstant("2"), new Pair<>(2, 0));

            addIntConstant(3);
            assertPosition(3, symbolTable.getPositionConstant("3"), new Pair<>(3, 0));

            addIntConstant(104);
            assertPosition(104, symbolTable.getPositionConstant("104"), new Pair<>(3, 1));

            addIntConstant(401);
            assertPosition(401, symbolTable.getPositionConstant("401"), new Pair<>(98, 0));

            addStringConstant("ana");
            assertPosition("ana", symbolTable.getPositionConstant("ana"), new Pair<>(1, 0));

            addStringConstant("are");
            assertPosition("are", symbolTable.getPositionConstant("are"), new Pair<>(9, 0));

            addStringConstant("naa");
            assertPosition("naa", symbolTable.getPositionConstant("naa"), new Pair<>(1, 1));


            // Additional assertions for non-present keys
            assertPosition("aaaa", symbolTable.getPositionIdentifier("aaaa"), new Pair<>(-1, -1));
            assertPosition(22, symbolTable.getPositionConstant("22"), new Pair<>(-1, -1));

            addIdentifier("abc"); // is duplicate - should throw exception

            assertContainsIdentifier("abc", true);
            assertContainsIdentifier("abcd", false);

            assertContainsIntConstant(2, true);
            assertContainsIntConstant(22, false);

            assertContainsStringConstant("ana", true);
            assertContainsStringConstant("anana", false);

        } catch (Exception e) {
            System.out.println("--Error: " + e.getMessage());
        }

        System.out.println(symbolTable);
    }


    private static void addIdentifier(String identifier) throws Exception {
        System.out.println(identifier + " -> " + symbolTable.addIdentifier(identifier));
    }

    private static void addIntConstant(Integer value) throws Exception {
        System.out.println(value + " -> " + symbolTable.addConstant(String.valueOf(value)));
    }

    private static void addStringConstant(String value) throws Exception {
        System.out.println(value + " -> " + symbolTable.addConstant(value));
    }

    private static void assertContainsIdentifier(String identifier, boolean expected) {
        try {
            boolean result = symbolTable.containsIdentifier(identifier);
            assert result == expected : "Identifier " + identifier + (expected ? " not found." : " found unexpectedly.");
        } catch (AssertionError e) {
            System.out.println("--Assertion Error: " + e.getMessage());
        }
    }

    private static void assertContainsIntConstant(Integer value, boolean expected) {
        try {
            boolean result = symbolTable.containsConstant(String.valueOf(value));
            assert result == expected : "Integer Constant " + value + (expected ? " not found." : " found unexpectedly.");
        } catch (AssertionError e) {
            System.out.println("--Assertion Error: " + e.getMessage());
        }
    }

    private static void assertContainsStringConstant(String value, boolean expected) {
        try {
            boolean result = symbolTable.containsConstant(value);
            assert result == expected : "String Constant " + value + (expected ? " not found." : " found unexpectedly.");
        } catch (AssertionError e) {
            System.out.println("--Assertion Error: " + e.getMessage());
        }
    }

    private static <T> void assertPosition(T key, Pair<Integer, Integer> actual, Pair<Integer, Integer> expected) {
        try {
            assert actual.equals(expected) : key + " does not have expected position " + expected;
        } catch (AssertionError e) {
            System.out.println("--Assertion Error: " + e.getMessage());
        }
    }

}
