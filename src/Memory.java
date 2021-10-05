import java.lang.Math;

public class Memory {
    private byte[] memory;

    public Memory(){
        memory = new byte[(int) Math.pow(2, 16)];
    }

    //    store a 8bit value in memory @ address
    public void store_8bit(int address, byte value){
        memory[address] = value;
    }

    //    store a 16bit value in memory.
    //    first byte will be stored @ address,
    //    and second byte will be stored @ address+1
    public void store_16bit(int address, byte[] value){
        memory[address] = value[0];
        memory[address+1] = value[1];
    }

    //    return a 8bit value from the given address
    public byte load_8bit(int address){
        byte value = memory[address];
        return value;
    }

    //    return a 16bit value from the given address.
    //    first byte will be loaded from address,
    //    and second byte will be loaded from address+1
    public byte[] load_16bit(int address){
        byte[] value = new byte[2];
        value[0] = memory[address];
        value[1] = memory[address+1];
        return value;
    }
}
