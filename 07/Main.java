import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file.vm or directory path>");
            System.exit(1);
        }

        File inputFile = new File(args[0]);
        try {
            File output;
            BufferedWriter writer;
            String fileNameWithoutExtension;

            if (inputFile.isDirectory()) {  // Output file named after the directory
                output = new File(inputFile, inputFile.getName() + ".asm");
                writer = new BufferedWriter(new FileWriter(output));
                fileNameWithoutExtension = inputFile.getName();

                File[] files = inputFile.listFiles((dir, name) -> name.endsWith(".vm")); // Process all .vm files in the directory
                if (files != null) {
                    for (File file : files) {
                        processVMFile(file, writer, fileNameWithoutExtension);
                    }
                }
            } else if (inputFile.isFile() && inputFile.getName().endsWith(".vm")) { // Output file in the same directory as the VM file
                String outputPath = inputFile.getPath().replaceAll("\\.vm$", ".asm");
                output = new File(outputPath);
                writer = new BufferedWriter(new FileWriter(output));
                fileNameWithoutExtension = getFileNameWithoutExtension(inputFile.getName());

                processVMFile(inputFile, writer, fileNameWithoutExtension);
            } else {
                System.err.println("Error: Invalid file or directory");
                System.exit(1);
                return; // Exit early
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void processVMFile(File VMFile, BufferedWriter writer, String fileNameWithoutExtension) throws IOException {
        try{
        Parser parser = new Parser(VMFile);
        CodeWriter codeWriter = new CodeWriter(writer, fileNameWithoutExtension);

            while (parser.hasMoreLines()) {  // as long as we have more lines we translate them
                parser.advance();
                switch (parser.commandType()){
                    case "C_ARITHMETIC":  // if an arithmetic command
                        codeWriter.writeArithmetic(parser.arg1());
                        break;
                    case "C_POP", "C_PUSH": // if a push or pop command
                        String segment = parser.arg1(); // the segment is the second argument 
                        switch (segment) {  // check which segment and save it as we should
                            case "local":
                                segment = "LCL";
                                break;
                            case "argument":
                                segment = "ARG";
                                break;
                            case "this":
                                segment = "THIS";
                                break;
                            case "that":
                                segment = "THAT";
                                break; 
                            }
                        
                        
                        codeWriter.WritePushPop(parser.commandType(), segment, parser.arg2()); // write the command with the segment of assembly
                        break;
                 
                }
              

            }
            codeWriter.close();
            parser.close();
        } catch (IOException e) {
            // Problem with input or output files
            e.printStackTrace();
        }
    }

    private static String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
    
    
    

