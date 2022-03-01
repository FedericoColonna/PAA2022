import java.util.HashMap;

public class Context {

    private final HashMap<String, Long> symbolTable;

    public Context() {
        symbolTable = new HashMap<>();
    }

    public void setVariable(String id, long value) {
        symbolTable.put(id, value);
    }

    public long getVariable(String id) {
        return symbolTable.get(id);
    }

    public boolean containVariable(String id) {
        return symbolTable.containsKey(id);
    }
}