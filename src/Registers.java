public class Registers {
    private byte[][] gp_regs;
    private byte[][] sp_regs;

    public Registers(){
        gp_regs = new byte[16][2];
        sp_regs = new byte[16][2];
    }

//    store a 16bit value to a gp_reg present @ code
    public void store_gpr(byte code, byte[] value) throws Exception{
        if(code < 16){
            gp_regs[code] = value;
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to store value because Register Code is larger than 4bit");
        }
    }

//    load a 16bit value from a gp_reg present @ code
    public byte[] load_gpr(byte code) throws Exception{
        byte[] value;
        if(code < 16){
            value = gp_regs[code];
        } else {
            throw new Exception("GP_Regs Error: Can't access GPR to load value because Register Code is larger than 4bit");
        }
        return value;
    }

    //    store a 16bit value to a sp_reg present @ code
    public void store_spr(byte code, byte[] value) throws Exception {
        if (code < 16) {
            if (code == 0) {
                if (code > 10) {
                    sp_regs[code] = value;
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
    //    load a 16bit value from a gp_reg present @ code
    public byte[] load_spr(byte code) throws Exception{
        byte[] value;
        if(code < 16){
            if(code == 0) {
                if(code > 10){
                    value = sp_regs[code];
                } else {
                    throw new Exception("SP_Regs Error: R1" + code%10+" can't be accessed");
                }
            } else {
                throw new Exception("SP_Regs Error: R0 can't be used for storing value");
            }
        } else {
            throw new Exception("SP_Regs Error: can't access SPR to store value because Register Code is larger than 4bit");
        }
        return value;
    }
}
