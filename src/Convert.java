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
            throw new Exception("Conversion Error: Int value is negative");
        }
        return b;
    }

    public static int B2I(char[] b){
        int value;
        value = b[0] * 256;
        value = value + b[1];
        return value;
    }
}
