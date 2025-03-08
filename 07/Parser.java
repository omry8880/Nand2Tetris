import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class Parser -
 * Parses each line of a given source Hack VM language file
 */
public class Parser {

    private BufferedReader reader;
    private String command;
    private String arg1;
    private int arg2;

    /**
     * Creates a new instance of Parser
     * 
     * @param source .vm file to read from
     * @throws IOException if couldn't open new reader from file
     */
    public Parser(File source) throws IOException {
        this.reader = new BufferedReader(new FileReader(source));
    }

    /**
     * Checks if source file still has more lines to read
     * 
     * @return true if still has more lines to read, false otherwise
     * @throws IOException if a problem with reader occurs
     */
    public boolean hasMoreLines() throws IOException {
        return reader.ready();
    }

    /**
     * Reads next line from the source file and parses it to the 3 components
     * that builds the Virtual Machine commands
     * 
     * @throws IOException if reader couldn't read next line from file
     */
    public void advance() throws IOException {
        String line = reader.readLine();
        String[] arr = line.trim().replaceAll("( )+", " ").split(" "); // Split the string to it's whitespace seperated
                                                                       // components

        switch (arr[0]) {  // chech first argumen in line : push,pop or an arithmetic command
            case "push": // if push command the second argument argument will be the segment and thirs will be the calue
                command = "C_PUSH";
                arg1 = arr[1];
                arg2 = Integer.parseInt(arr[2]);
                break;
            case "pop":
                command = "C_POP"; // if pop command the second argument argument will be the segment and thirs will be the calue
                arg1 = arr[1];
                arg2 = Integer.parseInt(arr[2]);
                break;
            default:
                command = "C_ARITHMETIC";  // if arithemtic , only 2 args , command and value
                arg1 = arr[0];
        }
    }

    /**
     * Returns the current VM command type
     * 
     * @return Command instance representing the current VM command type
     */
    public String commandType() {
        return command;
    }

    /**
     * Returns the current VM command first argument
     * 
     * @return the first argument of the VM command (segment or arithmetic
     *         operation)
     */
    public String arg1() {
        return arg1;
    }

    /**
     * Returns tclly the index of a push/pop VM command)
     * 
     * @return the second argument of the VM command (Or NULL if not exist)
     */
    public int arg2() {
        return arg2;
    }

    /**
     * Closes the File Reader
     * 
     * @throws IOException iç couldn't close the reader
     */
    public void close() throws IOException {
        reader.close();
    }
}
