import java.lang.Math;

public class Memory {
    private final int MAX_16BIT_VALUE = (int)Math.pow(2, 16);
    private char[] memory;

    public Memory(){
        memory = new char[MAX_16BIT_VALUE];
    }

    //    store a 8bit (char) value in memory @ address
    public void store_8bit(int address, char value) throws Exception{

        if(address < MAX_16BIT_VALUE){
            memory[address] = value;
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
    }

    public void store_8bit(char[] address, char value) throws Exception{
        store_8bit(Convert.B2I(address),value);
    }



    //    store a 16bit (2 bytes) value in memory.
    //    first byte will be stored @ address,
    //    and second byte will be stored @ address+1
    public void store_16bit(int address, char[] value) throws Exception{
        if((address < MAX_16BIT_VALUE) && (address+1 < MAX_16BIT_VALUE)){
            memory[address] = value[0];
            memory[address+1] = value[1];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
    }

    public void store_16bit(char[] address, char[] value) throws Exception{
        store_16bit(Convert.B2I(address),value);
    }

    //    return a 8bit (char) value from the given address
    public char load_8bit(int address) throws Exception{
        char value;
        if(address < MAX_16BIT_VALUE){
            value = memory[address];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
        return value;
    }

    public char load_8bit(char[] address) throws Exception{
        return load_8bit(Convert.B2I(address));
    }

    //    return a 16bit (2 chars) value from the given address.
    //    first byte will be loaded from address,
    //    and second byte will be loaded from address+1
    public char[] load_16bit(int address) throws Exception{
        char[] value = new char[2];
        if((address < MAX_16BIT_VALUE) && (address+1 < MAX_16BIT_VALUE)) {
            value[0] = memory[address];
            value[1] = memory[address + 1];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
        return value;
    }

    public char[] load_16bit(char[] address) throws Exception{
        return load_16bit(Convert.B2I(address));
    }
}
