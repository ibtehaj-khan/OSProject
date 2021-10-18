public class ArithmeticUnit {
    private final int MAX_VALUE_16 = (int) Math.pow(2,16);

    //    Add Two 16bit (2 bytes) value and return an int
    public int ADD(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 + val2;
    }

    //    Subtract Two 16bit (2 bytes) value and return an int
    public int SUBTRACT(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        int result = val1 - val2;

        // if result is negative
        if(result < 0){
            result = result + MAX_VALUE_16;
        }

        return result;
    }

    //    Multiply Two 16bit (2 bytes) value and return an int
    public int MULTIPLY(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 * val2;
    }

    //    Divide Two 16bit (2 bytes) value and return an int
    public int DIVIDE(char[] n1,char[] n2) throws Exception{
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        // If n2 is zero
        if(val2 == 0){
            throw new Exception("Arithmetic Error: Can't Divide by zero");
        }

        return val1 / val2;
    }

    //    AND Operator between Two 16bit (2 bytes) value and return an int
    public int AND(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 & val2;
    }

    //    OR Operator between Two 16bit (2 bytes) value and return an int
    public int OR(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 | val2;
    }

    //    Left Shift the 16 bit value
    public int SHIFT_LEFT(char[] n1){
        int val1 = Convert.B2I(n1);

        return val1 << 1;
    }

    //    Right Shift the 16 bit value
    public int SHIFT_RIGHT(char[] n1){
        int val1 = Convert.B2I(n1);

        return val1 >> 1;
    }

    //    Left Shift & Rotate the 16 bit value
    public int ROTATE_LEFT(char[] n1){
        int val1 = Convert.B2I(n1);

        // take the left most bit
        int msb = val1 / (Short.MAX_VALUE + 1);
        int result = val1 << 1;
        return result + msb;
    }

    //    Right Shift & Rotate the 16 bit value
    public int ROTATE_RIGHT(char[] n1){
        int val1 = Convert.B2I(n1);

        // take the left most bit
        int lsb = (val1 % 2) * (Short.MAX_VALUE + 1);
        int result = val1 >> 1;

        return lsb + result;
    }
}
