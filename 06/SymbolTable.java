import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Integer> symbolAddressMap;
    private static int Data_Address = 16;
    private static int Data_Ending_Address = 16384;

    public SymbolTable() {
        this.initializeSymbolTable();
    }

    public void addEntry(String symbol, int address) { // Adds <symbol, address> to the table
        this.symbolAddressMap.put(symbol, Integer.valueOf(address));
    } 

    public boolean contains(String symbol) { // Checks if symbol exists in the table
        return this.symbolAddressMap.containsKey(symbol);
    }

    public int getAddress(String symbol) { // Returns the address associated with symbol
        if (!contains(symbol)) {
            addEntry(symbol, Data_Address);
            if (Data_Address < Data_Ending_Address) {
                Data_Address++;
            }
        }
        return this.symbolAddressMap.get(symbol);
    }

    private void initializeSymbolTable() {
        this.symbolAddressMap = new HashMap<String, Integer>();
        this.symbolAddressMap.put("SP", Integer.valueOf(0));
        this.symbolAddressMap.put("LCL", Integer.valueOf(1));
        this.symbolAddressMap.put("ARG", Integer.valueOf(2));
        this.symbolAddressMap.put("THIS", Integer.valueOf(3));
        this.symbolAddressMap.put("THAT", Integer.valueOf(4));
        this.symbolAddressMap.put("R0", Integer.valueOf(0));
        this.symbolAddressMap.put("R1", Integer.valueOf(1));
        this.symbolAddressMap.put("R2", Integer.valueOf(2));
        this.symbolAddressMap.put("R3", Integer.valueOf(3));
        this.symbolAddressMap.put("R4", Integer.valueOf(4));
        this.symbolAddressMap.put("R5", Integer.valueOf(5));
        this.symbolAddressMap.put("R6", Integer.valueOf(6));
        this.symbolAddressMap.put("R7", Integer.valueOf(7));
        this.symbolAddressMap.put("R8", Integer.valueOf(8));
        this.symbolAddressMap.put("R9", Integer.valueOf(9));
        this.symbolAddressMap.put("R10", Integer.valueOf(10));
        this.symbolAddressMap.put("R11", Integer.valueOf(11));
        this.symbolAddressMap.put("R12", Integer.valueOf(12));
        this.symbolAddressMap.put("R13", Integer.valueOf(13));
        this.symbolAddressMap.put("R14", Integer.valueOf(14));
        this.symbolAddressMap.put("R15", Integer.valueOf(15));
        this.symbolAddressMap.put("SCREEN", Integer.valueOf(16384));
        this.symbolAddressMap.put("KBD", Integer.valueOf(24576));
    }
}
