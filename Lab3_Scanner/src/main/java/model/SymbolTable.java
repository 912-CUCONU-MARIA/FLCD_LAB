package model;

import util.Pair;

public class SymbolTable {

    private Integer size;
    private HashTable<String> identifiers;
    private HashTable<Integer> intConstants;
    private HashTable<String> stringConstants;

    public SymbolTable(Integer size) {
        this.size = size;
        this.identifiers=new HashTable<>(size);
        this.intConstants=new HashTable<>(size);
        this.stringConstants=new HashTable<>(size);
    }

    public Pair<Integer, Integer> addIdentifier(String identifier) throws Exception {
        return identifiers.add(identifier);
    }

    public Pair<Integer, Integer> addIntConstant(Integer constant) throws Exception {
        return intConstants.add(constant);
    }

    public Pair<Integer, Integer> addStringConstant(String constant) throws Exception {
        return stringConstants.add(constant);
    }

    public Pair<Integer, Integer> getPositionIdentifier(String name) {
        return identifiers.getPosition(name);
    }

    public Pair<Integer, Integer> getPositionIntConstant(Integer constant) {
        return intConstants.getPosition(constant);
    }

    public Pair<Integer, Integer> getPositionStringConstant(String constant) {
        return stringConstants.getPosition(constant);
    }

    public boolean containsIdentifier(String name) {
        return identifiers.contains(name);
    }

    public boolean containsIntConstant(Integer constant) {
        return intConstants.contains(constant);
    }

    public boolean containsStringConstant(String constant) {
        return stringConstants.contains(constant);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("identifiers\n");
        sb.append("-----------------------------------------\n");
        sb.append(identifiers.toString());

        sb.append("\nint constants\n");
        sb.append("-----------------------------------------\n");
        sb.append(intConstants.toString());

        sb.append("\nstring constants\n");
        sb.append("-----------------------------------------\n");
        sb.append(stringConstants.toString());

        return sb.toString();
    }


}
