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

    //    store a 16bit (2 chars) value to a sp_reg present @ code
    public void store_spr(byte code, char[] value) throws Exception {
        if (code < 16) {
            if (code == 0) {
                if (code > 10) {
                    if(code == 9){
                        sp_regs[code] = value;
                    } else {
                        throw new Exception("SP_Regs Error: Flag Register can't be used for storing values");
                    }
                } else {
                    throw new Exception("SP_Regs Error: R1" + code % 10 + " can't be accessed");
                }
            } else {
                throw new Exception("SP_Regs Error: R0 can't be used for storing value");
            }
        } else {
            throw new Exception("SP_Regs Error: can't access SPR to store value because Register Code is larger than 4bit");
        }
    }
    //    load a 16bit (2 chars) value from a gp_reg present @ code
    public char[] load_spr(byte code) throws Exception{
        char[] value;
        if(code < 16){
            if(code > 10){
                if(code == 9){
                    value = sp_regs[code];
                } else {
                    throw new Exception("SP_Regs Error: Flag Register can't be used for loading values");
                }

            } else {
                throw new Exception("SP_Regs Error: R1" + code%10+" can't be accessed");
            }
        } else {
            throw new Exception("SP_Regs Error: can't access SPR to load value because Register Code is larger than 4bit");
        }
        return value;
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

    //    set the flag register from bitset
    public void set_flag(BitSet bits) throws Exception{
        if(bits.length() >4){
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

}
