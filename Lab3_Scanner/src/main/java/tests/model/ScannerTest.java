package tests.model;

import model.Scanner;

public class ScannerTest {

    String program1 = "p1.txt";
    String program2 = "p2.txt";
    String program3 = "p3.txt";
    String program1err = "p1err.txt";

    public void testScanner(String program){
        Scanner scanner = new Scanner();
        System.out.println("Scanning program");
        scanner.scanProgram(program);
        System.out.println();
    }

    public void testAllPrograms(){
        testScanner(program1);
        testScanner(program2);
        testScanner(program3);
        testScanner(program1err);
    }

}
