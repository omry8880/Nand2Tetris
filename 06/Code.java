import java.util.HashMap;

public class Code {
    private HashMap<String, String> destMap;
    private HashMap<String, String> compMap;
    private HashMap<String, String> jumpMap;

    public Code() {
        this.destMap = new HashMap<String, String>();
        populateDestMap();

        this.compMap = new HashMap<String, String>();
        populateCompMap();

        this.jumpMap = new HashMap<String, String>();
        populateJumpMap();
    }

    public void populateDestMap() {
		this.destMap.put("null", "000");
		this.destMap.put("M", "001");
		this.destMap.put("D", "010");
		this.destMap.put("MD", "011");
		this.destMap.put("A", "100");
		this.destMap.put("AM", "101");
		this.destMap.put("AD", "110");
		this.destMap.put("ADM", "111");
    }

    public void populateCompMap() {
		this.compMap.put("0", "0101010");
		this.compMap.put("1", "0111111");
		this.compMap.put("-1", "0111010");
		this.compMap.put("D", "0001100");
		this.compMap.put("A", "0110000");
		this.compMap.put("M", "1110000");
		this.compMap.put("!D", "0001101");
		this.compMap.put("!A", "0110001");
		this.compMap.put("!M", "1110001");
		this.compMap.put("-D", "0001111");
		this.compMap.put("-A", "0110011");
		this.compMap.put("-M", "1110011");
		this.compMap.put("D+1", "0011111");
		this.compMap.put("A+1", "0110111");
		this.compMap.put("M+1", "1110111");
		this.compMap.put("D-1", "0001110");
		this.compMap.put("A-1", "0110010");
		this.compMap.put("M-1", "1110010");
		this.compMap.put("D+A", "0000010");
		this.compMap.put("D+M", "1000010");
		this.compMap.put("D-A", "0010011");
		this.compMap.put("D-M", "1010011");
		this.compMap.put("A-D", "0000111");
		this.compMap.put("M-D", "1000111");
		this.compMap.put("D&A", "0000000");
		this.compMap.put("D&M", "1000000");
		this.compMap.put("D|A", "0010101");
		this.compMap.put("D|M", "1010101");
    }

    public void populateJumpMap() {
		this.jumpMap.put("null", "000");
		this.jumpMap.put("JGT", "001");
		this.jumpMap.put("JEQ", "010");
		this.jumpMap.put("JGE", "011");
		this.jumpMap.put("JLT", "100");
		this.jumpMap.put("JNE", "101");
		this.jumpMap.put("JLE", "110");
		this.jumpMap.put("JMP", "111");
    }

    public String dest(String str) {
        if (str == null || str.isEmpty()) {
            str = "null"; // Return null for invalid or empty inputs
        }
        return this.destMap.get(str);
    }

    public String comp(String str) {
        return this.compMap.get(str);
    }

    public String jump(String str) {
        if (str == null || str.isEmpty()) {
            str = "null"; // Return null for invalid or empty inputs
        }
        return this.jumpMap.get(str);
    }
}
