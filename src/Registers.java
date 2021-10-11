import java.util.BitSet;

public class Registers {
    private char[][] gp_regs;
    private char[][] sp_regs;

    public Registers(){
        gp_regs = new char[16][2];
        sp_regs = new char[16][2];
    }

//    store a 16bit (2 chars) value to a gp_reg present @ code
    public void store_gpr(byte code, char[] value) throws Exception{
        if(code < 16){
            gp_regs[code] = value;
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to store value because Register Code is larger than 4bit");
        }
    }

//    load a 16bit (2 chars) value from a gp_reg present @ code
    public char[] load_gpr(byte code) throws Exception{
        char[] value;
        if(code < 16){
            value = gp_regs[code];
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to load value because Register Code is larger than 4bit");
        }
        return value;
    }



    public void set_code_base(char[] value){

    }

    public char[] get_code_base(){
        return sp_regs[1];
    }

    public void set_code_counter(char[] value){

    }

    public char[] get_code_counter(){
        return sp_regs[2];
    }

    public void increment_code_counter(){
        increment_code_counter(1);
    }

    public void increment_code_counter(int value){

    }

    public void set_code_limit(char[] value){

    }

    public char[] get_code_limit(){
        return sp_regs[3];
    }



    public void set_stack_base(char[] value){

    }

    public char[] get_stack_base(){
        return sp_regs[4];
    }

    public char[] get_stack_counter(){
        return sp_regs[5];
    }

    public void increment_stack_counter(){

    }

    public void decrement_stack_counter(){

    }

    public void set_stack_limit(char[] value){

    }

    public char[] get_stack_limit(){
        return sp_regs[6];
    }



    public void set_data_base(char[] value){

    }

    public char[] get_data_base(){
        return sp_regs[7];
    }

    public void set_data_limit(char[] value){

    }

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
    public BitSet get_flags(){
        //  Convert char value into byte array
        byte[] b = Convert.C2B(sp_regs[9]);

        return BitSet.valueOf(b);
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
