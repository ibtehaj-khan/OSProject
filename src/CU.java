import java.util.Date;

public class CU {
    // Classes simulating Hardware Components inside a CPU
    private PMMU MemoryUnit;
    private Registers REGISTERS;
    private Instructions INSTRUCTIONS;

    // Classes simulating OS Components
    private ProgramLoader ProgramLoader;
    private MemoryManager MemoryManager;
    private ProcessScheduler ProcessScheduler;

    public CU(){
        this.MemoryUnit = new PMMU();
        this.REGISTERS = new Registers();
        this.INSTRUCTIONS = new Instructions(this.MemoryUnit,this.REGISTERS);

        this.MemoryManager = new MemoryManager();
        this.ProcessScheduler = new ProcessScheduler(this.MemoryUnit,this.MemoryManager,this.REGISTERS);
        this.ProgramLoader = new ProgramLoader(this.MemoryUnit,this.MemoryManager,this.ProcessScheduler);
    }

    //    Start loading and Executing instructions
    public void execute() {
        char opcode;
        char[] counter;
        boolean stopCPU = false;
        while(!stopCPU){
            int quanta = ProcessScheduler.NextProcess();
            long timeToStop = System.currentTimeMillis() + quanta;

            if(quanta == -1){
                stopCPU = true;
            }

            while(timeToStop <= System.currentTimeMillis()){
                try{
                    // get the program counter
                    counter = REGISTERS.get_code_counter();

                    // load the opcode from memory
                    opcode = MemoryUnit.read_8bit(counter);

                    // Execute the instruction
                    decode_instruction(opcode);
                }
                // terminate process if got any error or request
                catch(Exception e){
                    int timeSpent = quanta - (int)(timeToStop - System.currentTimeMillis());
                    System.out.println(e.getMessage());
                    ProcessScheduler.TerminateProcess(timeSpent);
                    break;
                }
            }
        }
        REGISTERS.show_all();
    }

    public void loadProgram(String filename) throws Exception{
        ProgramLoader.loadFile(filename);
    }

    //    decode the instruction from opcode, then run it and update the program counter
    public void decode_instruction(char opcode) throws Exception{
        // get address from program counter
        int address = Convert.B2I(REGISTERS.get_code_counter());

        // Register - Register Instructions
        if(opcode / 16 == 1){
            // Fetch Values from Memory
            char R1 = MemoryUnit.read_8bit(address + 1);
            char R2 = MemoryUnit.read_8bit(address + 2);

            // Update the Program Counter
            REGISTERS.increment_code_counter(3);

            // Run Instruction
            run(opcode, R1, R2);
        }

        // Register - Immediate Instructions
        else if(opcode / 16 == 3){
            // Fetch Values from Memory
            char R1 = MemoryUnit.read_8bit(address + 1);
            char[] imm = MemoryUnit.read_16bit(address + 2);

            // Update the Program Counter
            REGISTERS.increment_code_counter(4);

            if(opcode %16 >= 0 && opcode %16 < 7){
                // Run Register-Immediate Instruction
                run(opcode, R1, imm);
            } else {
                // Run Jump Instructions
                run(opcode, imm);
            }
        }

        // Memory Instructions
        else if(opcode / 16 == 5){
            // Fetch Values from Memory
            char R1 = MemoryUnit.read_8bit(address + 1);
            char[] imm = MemoryUnit.read_16bit(address + 2);

            // Update the Program Counter
            REGISTERS.increment_code_counter(4);

            // Run Instruction
            runM(opcode, R1, imm);
        }

        // Single Operand Instructions
        else if(opcode / 16 == 7){
            // Fetch Values from Memory
            char R1 = MemoryUnit.read_8bit(address + 1);

            // Update the Program Counter
            REGISTERS.increment_code_counter(2);

            // Run Instruction
            run(opcode, R1);
        }

        // No Operand Instructions
        else if(opcode / 16 == 15){
            // Update the Program Counter
            REGISTERS.increment_code_counter(1);

            // Run Instruction
            run(opcode);
        }
        else {
            throw new Exception("CPU Error: Couldn't execute opcode " + (int)opcode);
        }
    }

    //    Run the Register - Register Instructions
    public void run(char opcode, char R1, char R2) throws Exception {
        switch(opcode % 16){
            case 6:
                INSTRUCTIONS.MOV(R1,R2);
                break;
            case 7:
                INSTRUCTIONS.ADD(R1,R2);
                break;
            case 8:
                INSTRUCTIONS.SUB(R1,R2);
                break;
            case 9:
                INSTRUCTIONS.MUL(R1,R2);
                break;
            case 10:
                INSTRUCTIONS.DIV(R1,R2);
                break;
            case 11:
                INSTRUCTIONS.AND(R1,R2);
                break;
            case 12:
                INSTRUCTIONS.OR(R1,R2);
                break;
            default:
                break;
        }
    }

    //    Run the Register - Immediate Instructions
    public void run(char opcode, char R1, char[] imm) throws Exception {
        switch(opcode%16){
            case 0:
                INSTRUCTIONS.MOVI(R1,imm);
                break;
            case 1:
                INSTRUCTIONS.ADDI(R1,imm);
                break;
            case 2:
                INSTRUCTIONS.SUBI(R1,imm);
                break;
            case 3:
                INSTRUCTIONS.MULI(R1,imm);
                break;
            case 4:
                INSTRUCTIONS.DIVI(R1,imm);
                break;
            case 5:
                INSTRUCTIONS.ANDI(R1,imm);
                break;
            case 6:
                INSTRUCTIONS.ORI(R1,imm);
                break;
            default:
                break;
        }
    }

    //    Run the Jump Instructions
    public void run(char opcode, char[] imm) throws Exception {
        switch(opcode%16){
            case 7:
                INSTRUCTIONS.BZ(imm);
                break;
            case 8:
                INSTRUCTIONS.BNZ(imm);
                break;
            case 9:
                INSTRUCTIONS.BC(imm);
                break;
            case 10:
                INSTRUCTIONS.BS(imm);
                break;
            case 11:
                INSTRUCTIONS.JMP(imm);
                break;
            case 12:
                INSTRUCTIONS.CALL(imm);
                break;
            case 13:
                INSTRUCTIONS.ACT(imm);
                break;
            default:
                break;
        }
    }

    //    Run the Memory Instructions
    public void runM(char opcode, char R1, char[] imm) throws Exception {
        switch(opcode%16){
            case 1:
                INSTRUCTIONS.MOVL(R1,imm);
                break;
            case 2:
                INSTRUCTIONS.MOVS(R1,imm);
                break;
            default:
                break;
        }
    }

    //    Run the Single Operand Instructions
    public void run(char opcode, char R1) throws Exception {
        switch(opcode%16){
            case 1:
                INSTRUCTIONS.SHL(R1);
                break;
            case 2:
                INSTRUCTIONS.SHR(R1);
                break;
            case 3:
                INSTRUCTIONS.RTL(R1);
                break;
            case 4:
                INSTRUCTIONS.RTR(R1);
                break;
            case 5:
                INSTRUCTIONS.INC(R1);
                break;
            case 6:
                INSTRUCTIONS.DEC(R1);
                break;
            case 7:
                INSTRUCTIONS.PUSH(R1);
                break;
            case 8:
                INSTRUCTIONS.POP(R1);
                break;
            default:
                break;
        }
    }

    //    Run the No Operand Instructions
    public void run(char opcode) throws Exception {
        switch(opcode%16){
            case 1:
                INSTRUCTIONS.RETURN();
                break;
            case 2:
                INSTRUCTIONS.NOOP();
                break;
            case 3:
                INSTRUCTIONS.END();
                break;
            default:
                break;
        }
    }
}
