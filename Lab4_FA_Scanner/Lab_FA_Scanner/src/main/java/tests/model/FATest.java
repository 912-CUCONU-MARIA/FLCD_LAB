package tests.model;

import model.FA;

public class FATest {

    public void testIntegerFA(){
        FA FA = new FA("file_integer.in.txt");
        System.out.println("10" + " " + FA.checkAccepted("10"));
        System.out.println("+10" + " " + FA.checkAccepted("+10"));
        System.out.println("-10" + " " + FA.checkAccepted("-10"));
        System.out.println("-1a" + " " + FA.checkAccepted("-1a"));
        System.out.println("a" + " " + FA.checkAccepted("a"));
        System.out.println("" + " " + FA.checkAccepted(""));
        System.out.println("01" + " " + FA.checkAccepted("01"));
        System.out.println("-" + " " + FA.checkAccepted("-"));
        System.out.println("-0" + " " + FA.checkAccepted("-0"));
        System.out.println("0" + " " + FA.checkAccepted("0"));
        System.out.println("1" + " " + FA.checkAccepted("1"));
        System.out.println("19" + " " + FA.checkAccepted("19"));
    }

    public void testIdentifierFA(){
        FA FA = new FA("file_identifier.in.txt");
        System.out.println("-a" + " " + FA.checkAccepted("-a"));
        System.out.println("_a" + " " + FA.checkAccepted("_a"));
        System.out.println("0a" + " " + FA.checkAccepted("0a"));
        System.out.println("1" + " " + FA.checkAccepted("1"));
        System.out.println("a12" + " " + FA.checkAccepted("a12"));
        System.out.println("12a" + " " + FA.checkAccepted("12a"));
        System.out.println("aA!" + " " + FA.checkAccepted("aA!"));
        System.out.println("a123" + " " + FA.checkAccepted("a123"));
        System.out.println("AA" + " " + FA.checkAccepted("AA"));
        System.out.println("___A123" + " " + FA.checkAccepted("___A123"));
        System.out.println("" + " " + FA.checkAccepted(""));
    }
}
