import java.lang.Math;

public class Memory {
    private final int MAX_8BIT_VALUE = (int)Math.pow(2, 8);
    private final int MAX_16BIT_VALUE = (int)Math.pow(2, 16);
    private byte[] memory;

    public Memory(){
        memory = new byte[MAX_16BIT_VALUE];
    }

    //    store a 8bit (byte) value in memory @ address
    public void store_8bit(int address, byte value) throws Exception{

        if(address < MAX_16BIT_VALUE){
            memory[address] = value;
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
    }

    //    store a 16bit (byte) value in memory.
    //    first byte will be stored @ address,
    //    and second byte will be stored @ address+1
    public void store_16bit(int address, byte[] value) throws Exception{
        if((address < MAX_16BIT_VALUE) && (address+1 < MAX_16BIT_VALUE)){
            memory[address] = value[0];
            memory[address+1] = value[1];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
    }

    //    return a 8bit value from the given address
    public byte load_8bit(int address) throws Exception{
        byte value;
        if(address < MAX_16BIT_VALUE){
            value = memory[address];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
        return value;
    }

    //    return a 16bit value from the given address.
    //    first byte will be loaded from address,
    //    and second byte will be loaded from address+1
    public byte[] load_16bit(int address) throws Exception{
        byte[] value = new byte[2];
        if((address < MAX_16BIT_VALUE) && (address+1 < MAX_16BIT_VALUE)) {
            value[0] = memory[address];
            value[1] = memory[address + 1];
        } else {
            throw new Exception("Memory Error: IndexOutOfBoundException");
        }
        return value;
    }
}
