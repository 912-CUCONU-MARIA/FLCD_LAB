package main;

import model.FA;
import tests.model.FATest;
import tests.model.ScannerTest;

public class Main {

    public static void main(String[] args) {
        //SymbolTableTest.testSymbolTable();
        //ScannerTest scannerTest = new ScannerTest();
        //scannerTest.testAllPrograms();
        //FATest faTest=new FATest();
        //faTest.testIdentifierFA();
        //faTest.testIntegerFA();
        String file_integer_in_txt="file_integer.in.txt";
        String file_identifier_in_txt="file_identifier.in.txt";
        mainFA(file_identifier_in_txt);

    }


    public static void mainFA(String fileName) {
        FA FA = new FA(fileName);
        System.out.println("1. Print states");
        System.out.println("2. Print alphabet");
        System.out.println("3. Print final states");
        System.out.println("4. Print initial states");
        System.out.println("5. Print transitions");
        System.out.println("6. Check word");
        System.out.println("0. Exit");

        System.out.println("> ");
        int option = new java.util.Scanner(System.in).nextInt();
        while (option!=0) {

            switch (option) {
                case 1 -> System.out.println(FA.getAll_states());
                case 2 -> FA.printAlphabet();
                case 3 -> System.out.println(FA.getFinalStates());
                case 4 -> System.out.println(FA.getInitialStates());
                case 5 -> FA.printTransitions();
                case 6 -> {
                    System.out.println("Enter word: ");
                    var word = new java.util.Scanner(System.in).nextLine();
                    System.out.println(FA.checkAccepted(word));
                }
                default -> System.out.println("Invalid option");
            }
            option = new java.util.Scanner(System.in).nextInt();

        }
    }

}