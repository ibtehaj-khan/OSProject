public class ArithmeticUnit {

    //    Add Two 16bit (2 bytes) value and return an int
    public int ADD(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 + val2;
    }

    //    Add 16bit int value in 16bit (2 bytes) value and return an int
    public int ADD(char[] n1,int n2){
        int val1 = Convert.B2I(n1);

        return val1 + n2;
    }

    //    Subtract Two 16bit (2 bytes) value and return an int
    public int SUBTRACT(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 - val2;
    }

    //    Subtract 16bit int value in 16bit (2 bytes) value and return an int
    public int SUBTRACT(char[] n1,int n2){
        int val1 = Convert.B2I(n1);

        return val1 - n2;
    }

    //    Multiply Two 16bit (2 bytes) value and return an int
    public int MULTIPLY(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 * val2;
    }

    //    Multiply 16bit int value and 16bit (2 bytes) value, and return an int
    public int MULTIPLY(char[] n1,int n2){
        int val1 = Convert.B2I(n1);

        return val1 * n2;
    }

    //    Divide Two 16bit (2 bytes) value and return an int
    public int DIVIDE(char[] n1,char[] n2){
        int val1 = Convert.B2I(n1);
        int val2 = Convert.B2I(n2);

        return val1 / val2;
    }

    //    Divide 16bit int value by 16bit (2 bytes) value, and return an int
    public int DIVIDE(char[] n1,int n2){
        int val1 = Convert.B2I(n1);

        return val1 / n2;
    }
}
