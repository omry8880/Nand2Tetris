public abstract class Operations {
    
    public static String repeat(String s, int num){
        StringBuilder result = new StringBuilder();
        for(int i=0; i < num; i++){
            result.append(s);
        }
        return result.toString();
    }

    /**
     * Returns a String with its leading and trailing white spaces removed.
     * 
     * @param String
     * @return A string which its leading and trailing white space removed.
     */
    public static String strip(String s) {
        s = stripLeading(s);
        return stripTrailing(s);
    }

    /**
     * Returns a String with its leading white spaces removed.
     * 
     * @param String
     * @return A string which its leading white space removed.
     */
    public static String stripLeading(String s) {
        StringBuilder result = new StringBuilder();
        boolean hasStarted = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                hasStarted = true;
            }
            if (hasStarted) {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Returns a String with its leading white spaces removed.
     * 
     * @param String
     * @return A string which its trailing white space removed.
     */
    public static String stripTrailing(String s) {
        StringBuilder result = new StringBuilder();
        boolean hasStarted = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                hasStarted = true;
            }
            if (hasStarted) {
                result.append(c);
            }
        }
        result.reverse();
        return result.toString();
    }
}
