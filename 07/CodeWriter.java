import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    BufferedWriter bw;
    int contCounter;
    String fileName;


    final String push = "D=M\n" +   //assembly coode for the push command
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n" +
                        "@SP\n" +
                        "M=M+1\n";

    final String pop = "D=D+A\n" +    //assembly code for the pop command
                        "@addr\n"+
                        "M=D\n" +
                        "@SP\n" +
                        "M=M-1\n" +
                        "A=M\n" +
                        "D=M\n" +
                        "@addr\n" +
                        "A=M\n" +
                        "M=D\n";
    final String popFirst = "@SP\n" +     //assemply code for popping firstly and then for calculation commands
                        "M=M-1\n" +
                        "A=M\n" +
                        "D=M\n";
            
    final String popSecond = "@SP\n" +
                        "M=M-1\n" +
                        "A=M\n";



    final String add = "M=D+M\n";

    final String sub = "M=M-D\n";

    final String neg = "M=-M\n";

    final String eq = "D;JEQ\n";

    final String gt = "D;JGT\n";

    final String lt = "D;JLT\n";

    final String and = "M=D&M\n";

    final String or = "M=D|M\n";

    final String not = "M=!M\n";

    final String incrementSP = "@SP\n" +
                        "M=M+1\n";

    final String compute = "D=M-D\n" +
                        "@TRUE";
    final String writeTrueFalse = "@0\n" +     // for the boolean commands
                        "D=A\n" +
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n" +
                        "@CONT\n" +
                        "0;JMP\n" +
                        "(TRUE)\n" +
                        "@1\n" +
                        "D=A\n" +
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n";
    final String writeFalse = "@0\n" +
                        "D=A\n" +
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n";
            
    final String writeTrue = "@1\n" +
                        "A=-A\n" +
                        "D=A\n" +
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n";
                        

    private String getCompareCommand(String comp) {  //compare , by the command recieved
        return popFirst + popSecond + compute + contCounter + "\n" + comp + writeFalse + "@CONT" + contCounter
                                    + "\n0;JMP\n(TRUE" + contCounter + ")\n" + writeTrue + "(CONT" + (contCounter++)
                                    + ")\n@SP\nM=M+1\n";
                        }                   


    public CodeWriter(BufferedWriter bw, String fileName){
        this.bw = bw;  
        this.fileName = fileName;
        this.contCounter = 0;
    }

    public void writeArithmetic(String command) throws IOException{
        switch (command) {  // check which arithmetic command we recieved 
            case "add":
                bw.write("//add\n" + popFirst + popSecond + add + incrementSP);
                break;
        
            case "sub":
                bw.write("//sub\n"+ popFirst + popSecond + sub + incrementSP);
                break;

            case "neg":
                bw.write("//neg\n" + popFirst + neg + incrementSP);
                break;
            
            case "eq":
                bw.write("//eq\n" + getCompareCommand(eq));
                break;

            case "gt":
                bw.write("//gt\n" + getCompareCommand(gt));
                break;

            case "lt":
                bw.write("//lt\n" + getCompareCommand(lt));
                break;

            case "and":
                bw.write("//and\n" + popFirst + popSecond + and + incrementSP);
                break;

            case "or":
                bw.write("//or\n" + popFirst + popSecond + or + incrementSP);
                break;

            case "not":
                bw.write("//not\n" + popFirst + not + incrementSP);
                break;

        }


    }

    public void WritePushPop(String command, String segment , int index) throws IOException{
        String line = "";
        String address = "";
        boolean stp = true;

        switch (segment) {   // check for the specific segment in push/pop commands
            case "LCL","ARG","THIS","THAT": 
                stp = false;
                line = "@" + segment + "\nD=M\n" + "@" + index + "\n";
                if (command == "C_POP") {
                    line = line + pop;
                } else 
                    line = line + "A=D+A\n" + push;

                break;
            
            case "constant":
                stp = false;
                line = "@" +  index + "\n" + "D=A" + push.substring(3);
                break;

            case "static":
                address = fileName + index;

                break;

            case "temp":
                address = "" + (5 + index);
                break;
        
            case "pointer":
                if (index==0){
                    address = "THIS";
                }
                else{
                    address = "THAT";
                }
                break;

        }
        if (stp){
            if (command == "C_POP") { // if it's not this, that, arg or local - we add the address
                line = popFirst + "@" + address + "\nM=D\n";
            } else
                line = "@" + address + "\n" + push;
        }
        bw.write("//" + command + " " + segment + " " + index + "\n" + line); // write the command in assembly after editing the values based on vm command
    }

    public void close() throws IOException {   // close the file for w
        bw.close();
    }


}