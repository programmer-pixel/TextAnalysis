public class quiz {
    public static void main(String[] args) {
        String msg = "0123456789";
        while(msg.length() > 1){
            msg = change(msg);
            System.out.println(msg);
        }
    }

    private static String change(String p) {
        return p.substring(p.length()/2);
    }
}
