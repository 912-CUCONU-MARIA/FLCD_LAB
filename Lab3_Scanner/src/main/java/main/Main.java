package main;

import tests.model.ScannerTest;
import tests.model.SymbolTableTest;

public class Main {

    public static void main(String[] args) {
        ScannerTest scannerTest= new ScannerTest();
        scannerTest.testAllPrograms();
    }
}