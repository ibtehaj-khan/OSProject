public class CU {

    private Memory MEMORY;
    private Registers REGISTERS;
    private Instructions INSTRUCTIONS;

    public CU(){
        this.MEMORY = new Memory();
        this.REGISTERS = new Registers();
        this.INSTRUCTIONS = new Instructions(this.MEMORY,this.REGISTERS);
    }

    //    load file data into memory Array and start from the address
    public void load_program(String filename, int address) throws Exception {

    }

    //    process the opcode to find the type of instruction, then run the instruction
    public void execute_instruction(char opcode){

    }

    //    Run the No Operand Instructions
    public void run(char opcode){

    }

    //    Run the Single Operand Instructions
    public void run(char opcode, char R1){

    }

    //    Run the Register - Register Instructions
    public void run(char opcode, char R1, char R2){

    }

    //    Run the Register - Immediate and Memory Instructions
    public void run(char opcode, char R1, char[] imm){

    }
}
