package common;

import java.util.HashMap;

public class Context {

    private final HashMap<String, Integer> symbolTable;

    public Context() {
        symbolTable = new HashMap<>();
    }

    public void setVariable(String id, int value) {
        symbolTable.put(id, value);
    }

    public int getVariable(String id) {
        return symbolTable.get(id);
    }
}