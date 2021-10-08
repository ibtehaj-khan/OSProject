public class Main {
    public static void main(String[] args) throws Exception {
        char[] b = Convert.I2B((int)Math.pow(2,16));
        System.out.println((int)b[0] + " , " + (int)b[1]);
        System.out.println(Convert.B2I(b));
    }
}
