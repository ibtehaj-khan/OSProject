import java.util.BitSet;

public class Registers {
    private char[][] gp_regs;
    private char[][] sp_regs;

    public Registers(){
        gp_regs = new char[16][2];
        sp_regs = new char[16][2];
    }

//    store a 16bit (2 chars) value to a gp_reg present @ code
    public void store_gpr(char code, char[] value) throws Exception{
        if(code < 16){
            gp_regs[code] = value;
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to store value because Register Code is larger than 4bit");
        }
    }

//    load a 16bit (2 chars) value from a gp_reg present @ code
    public char[] load_gpr(char code) throws Exception{
        char[] value;
        if(code < 16){
            value = gp_regs[code];
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to load value because Register Code is larger than 4bit");
        }
        return value;
    }



    //    //////// Methods for Code Related SP_Regs

    //    Update the value of Code Base Register
    public void set_code_base(char[] value){sp_regs[1] = value;}

    //    Fetch the current Value from Code Base Register
    public char[] get_code_base(){
        return sp_regs[1];
    }

    //    Update the Value of Code Counter Register
    public void set_code_counter(char[] value){sp_regs[2] = value;}

    //    Fetch the current Value from Code Counter Register
    public char[] get_code_counter(){
        return sp_regs[2];
    }

    //    Increase the Value of Code Counter by 1
    public void increment_code_counter() throws Exception{
        increment_code_counter(1);
    }

    //    Increase the Value of Code Counter by value present in parameter value
    public void increment_code_counter(int value) throws Exception {
        int counter = Convert.B2I(sp_regs[2]) + value;
        int limit = Convert.B2I(sp_regs[3]);

        try{
            sp_regs[2] = Convert.I2B(counter);
        }catch(Exception e){
            throw new Exception("SP_Regs Error: Code Counter Overflow");
        }

        if(limit < counter){
               throw  new Exception("Execution Error: Code Counter Exceeds Code Limit");
        }
    }

    //    Update the Value of Code Limit Register
    public void set_code_limit(char[] value){sp_regs[3] = value;}

    //    Fetch the current Value from Code Limit Register
    public char[] get_code_limit(){return sp_regs[3];}



    //    //////// Methods for Stack Related SP_Regs

    //    Update the Value of Stack Base Register
    public void set_stack_base(char[] value){sp_regs[4] = value;}

    //    Fetch the current Value from Stack Base Register
    public char[] get_stack_base(){return sp_regs[4];}

    //    Fetch the current Value from Stack Counter Register
    public char[] get_stack_counter(){return sp_regs[5];}

    //    Update the Value of Stack Counter Register
    public void set_stack_counter(char[] value){sp_regs[5] = value;}

    //    Increment the code counter by value
    public void increment_stack_counter() throws Exception{
        int counter = Convert.B2I(sp_regs[5]);
        int limit = Convert.B2I(sp_regs[6]);

        if(counter > limit+1){
            throw  new Exception("Execution Error: Stack Counter Exceeds Stack Limit");
        }

        try{
            sp_regs[5] = Convert.I2B(counter + 1);
        }catch(Exception e){
            throw new Exception("SP_Regs Error: Stack Counter Overflow");
        }
    }

    //    Decrement the code counter by value
    public void decrement_stack_counter() throws Exception{
        int counter = Convert.B2I(sp_regs[5]) - 1;
        int base = Convert.B2I(sp_regs[4]);

        try{
            sp_regs[5] = Convert.I2B(counter);
        }catch(Exception e){
            throw new Exception("SP_Regs Error: Stack Counter Overflow");
        }

        if(base > counter){
            throw  new Exception("Execution Error: Stack Counter is below Stack Base");
        }
    }

    //    Update the Value of Stack Limit Register
    public void set_stack_limit(char[] value){sp_regs[6] = value;}

    //    Fetch the current Value from Stack Limit Register
    public char[] get_stack_limit(){
        return sp_regs[6];
    }



    //    //////// Methods for Data Related SP_Regs

    //    Update the Value of Data Base Register
    public void set_data_base(char[] value){sp_regs[7] = value;}

    //    Fetch the current Value from Data Base Register
    public char[] get_data_base(){return sp_regs[7];}

    //    Update the Value of Data Limit Register
    public void set_data_limit(char[] value){sp_regs[8] = value;}

    //    Fetch the current Value from Data Limit Register
    public char[] get_fata_limit(){
        return sp_regs[8];
    }



    //    set the flag register from index bit
    public void set_flag(int index, boolean value) throws Exception{
        if(index >3){
            //  Convert char value into byte array
            byte[] b = Convert.C2B(sp_regs[9]);

            //  convert the byte array into bitset
            BitSet bits = BitSet.valueOf(b);
            bits.set(index,value);

            // convert the bitset into byte array, then store the value as char
            sp_regs[9] = Convert.B2C(bits.toByteArray());
        } else {
            throw new Exception("SP_Regs Error: IndexOutOfBound Exception @ flag register");
        }
    }

    //    get value of flag register
    public boolean get_flags(int index) throws Exception{
        boolean value;
        if(index >3){
            //  Convert char value into byte array
            byte[] b = Convert.C2B(sp_regs[9]);

            //  convert the byte array into bitset
            BitSet bits = BitSet.valueOf(b);
            value = bits.get(index);
        } else {
            throw new Exception("SP_Regs Error: IndexOutOfBound Exception @ flag register");
        }
        return value;
    }

    //    clear the values of flag register
    public void clear_flags(){
        sp_regs[9][0] = (char) 0;
        sp_regs[9][1] = (char) 0;
    }

    public void show_all(){
        System.out.println("GP Registers\t\t\t\tSP Registers");
        System.out.println("===============\t\t\t\t===============");
        for(int i = 0; i < 16; i++){
            System.out.printf("GPR[%d]\t= %5d\t\t\t\t",i,Convert.B2I(gp_regs[i]));
            System.out.printf("SPR[%d]\t= %5d\n",i,Convert.B2I(sp_regs[i]));
        }
    }

}
