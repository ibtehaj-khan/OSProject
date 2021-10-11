public class CU {

    private Memory MEMORY;
    private Registers REGISTERS;
    private Instructions INSTRUCTIONS;

    public CU(){
        this.MEMORY = new Memory();
        this.REGISTERS = new Registers();
        this.INSTRUCTIONS = new Instructions(this.MEMORY,this.REGISTERS);
    }

    public void load_program(String filename, int address) throws Exception {

    }
}
