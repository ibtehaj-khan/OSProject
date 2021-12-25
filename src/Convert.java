public class Convert {

    //    Convert the 16 bit int into 2 bytes value.
    //    Throws Exception if int value is negative or greater than 2^16-1
    public static char[] I2B(int value) throws Exception{
        char[] b;
        if(value >= 0){
            if(value <= Math.pow(2,16)-1){
                b = new char[2];
                b[0] = (char) (value / 256);
                b[1] = (char) (value % 256);
            } else {
                throw new Exception("Conversion Error: Int value is greater than 2^16-1");
            }
        } else {
            throw new Exception("Conversion Error: value is negative{" + value+"}");
        }
        return b;
    }

    //    Convert 16bit Byte array into int
    public static int B2I(char[] b){
        int value;
        value = b[0] * 256;
        value = value + b[1];
        return value;
    }

    //    Convert 16bit byte array into 16bit char array
    public static char[] B2C(byte[] b){
        char[] c = new char[2];
        c[0] = (char) b[0];
        c[1] = (char) b[1];
        return c;
    }

    //    Convert 16bit char array into 16bit byte array
    public static byte[] C2B(char[] c){
        byte[] b = new byte[2];
        b[0] = (byte) c[0];
        b[1] = (byte) c[1];
        return b;
    }
}
