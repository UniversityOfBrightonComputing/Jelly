package uk.ac.brighton.ab607.jelly.debug;

public class Debug {
    private static String lastMessage = "";
    
    public static void msg(String text) {
        lastMessage = text;
        System.out.println(text);
    }
    
    public static String getLastMessage() {
        return lastMessage;
    }
}
