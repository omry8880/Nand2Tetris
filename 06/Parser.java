import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Parser {
    private HashMap<Integer, String> hm;
    private int currentIndex;
    
    public Parser(File file) {
        this.hm = new HashMap<Integer, String>(); // initialize the HashMap
        this.currentIndex = 1; // no instruction read yet
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineNumber = 0;
            String line;
            while ((line = br.readLine()) != null) {
                line = line.strip(); // remove spaces
                if (!line.isBlank() && !line.startsWith("/")) { // checks that current line is not empty and is not a comment
                    this.hm.put(++lineNumber, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasMoreLines() {
        return this.currentIndex - 1 < this.hm.size(); // checks that next line is still smaller than the total number of lines, if not there are no more lines

    }

    public String advance() {
        if (hasMoreLines()) {
            this.currentIndex++;
            String instruction = this.hm.get(currentIndex);
            if (instruction != null) {
                return instruction;
            }
        }
        return null;
    }

    public String instructionType() { 
        String instruction = this.hm.get(currentIndex);

        if (instruction == null) { // for debugging: checks that instruction is indeed valid
            throw new IllegalStateException("No valid instruction at current index: " + currentIndex);
        }

        if (instruction.charAt(0) == '@' && instruction.length() > 1) { // starts with '@' -> A instruction
            return "A_INSTRUCTION";
        }
        else if (instruction.startsWith("(") && instruction.endsWith(")")) { // starts with '(' and ends with ')'
            return "L_INSTRUCTION";
        }
        return "C_INSTRUCTION"; // dest=comp;jump
    }

    public String symbol() { // (label), @xxx
        String instruction = this.hm.get(currentIndex);
        String res = null;
        switch (instructionType()) {
            case "L_INSTRUCTION":
                res = instruction.substring(1, instruction.length() - 1);
                break;
            case "A_INSTRUCTION":
                res = instruction.substring(1);
                break;
            default:
                break;
        }
        return res;
    }

    public String dest() {
        String instruction = this.hm.get(currentIndex);
        String res = null;
        if (instructionType().equals("C_INSTRUCTION")) {
            int eqIndex = instruction.indexOf("=");
            if (eqIndex != -1) {
                // Return the substring up to the "=" character
                res = instruction.substring(0, eqIndex);
                return res;
            }
        }
        return res;
    }

    public String comp() { //M=6
        String instruction = this.hm.get(currentIndex);
        String res = "";
        if (instructionType().equals("C_INSTRUCTION")) {
            if (!instruction.contains(";")) {
                res = instruction.substring(instruction.indexOf("=") + 1);
            } else {
                res = instruction.substring(instruction.indexOf("=") + 1, instruction.indexOf(";"));
            }
        }
        return res;
    }

    public String jump() {
        String instruction = this.hm.get(currentIndex);
        String res = null; // if there is no jump, return null
        if (instructionType().equals("C_INSTRUCTION")) {
            if (instruction.contains(";")) {
                res = instruction.substring(instruction.indexOf(";") + 1);
            }
        }
        return res;
    }
}




