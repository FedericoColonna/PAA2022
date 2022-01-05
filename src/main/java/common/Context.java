package common;

import java.util.HashMap;

public class Context {

    private final HashMap<String, Object> symbolTable;

    public Context() {
        symbolTable = new HashMap<>();
    }

    public void setVariable(String id, Object value) {
        symbolTable.put(id, value);
    }

    public Object getVariable(String id) {
        return symbolTable.get(id);
    }
}