import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

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
    public int load_program_in_memory(String filename, int address) throws Exception {
        // File path is passed as parameter
        try{
            File file = new File(filename);

            // Note:  Double back quote is to avoid compiler interpret words
            // like \test as \t (ie. as a escape sequence)

            // Creating an object of BufferedReader class
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Declaring a string variable
            String st;
            // Condition holds true till there is character in a string
            int i = address;
            while ((st = br.readLine()) != null){

                // Read int values as string from file
                StringTokenizer defaultTokenizer = new StringTokenizer(st);


                while(defaultTokenizer.hasMoreTokens()){
                    //MEMORY.store_8bit(defaultTokenizer.nextToken(), 0);
                    String token = defaultTokenizer.nextToken();
                    //  System.out.println(token);
                    int  Token_int = Integer.parseInt(token);
                    char Token_ascii = (char) Token_int;

                    MEMORY.store_8bit(i,Token_ascii);
                    System.out.print(MEMORY.load_8bit(i));
                    i++;
                }
            }
        // return value of limit
        return i-1;
        } catch(Exception e){
            System.out.println("File "+filename+"not Found");
        }
        return -1;
    }

    // when program is in memory, and set up the registers' data.
    // runs only once when program is stored in memory
    public void load_data_in_registers(int base, int limit) throws Exception{

        char[]code_limit =  Convert.I2B(limit);
        char[]code_base =  Convert.I2B(base);

        char[]stack_limit =  Convert.I2B(limit+50);
        char[]stack_base =  Convert.I2B(limit+1);

        REGISTERS.set_code_limit(code_limit);
        REGISTERS.set_code_base(code_base);
        REGISTERS.set_code_counter(code_base);

        REGISTERS.set_stack_base(stack_base);
        REGISTERS.set_stack_limit(stack_limit);
        REGISTERS.set_stack_counter(stack_base);
    }

    //    Start loading and Executing instructions
    public void run_program() throws Exception {
        char opcode;
        char[] counter;
        while(true){
            // get the program counter
            counter = REGISTERS.get_code_counter();

            // load the opcode from memory
            opcode = MEMORY.load_8bit(counter);

            // Check if opcode is for terminate program
            if(opcode == 243){
               break;
            }

            // Execute the instruction
            decode_instruction(opcode);
        }
        REGISTERS.show_all();
    }

    //    decode the instruction from opcode, then run it and update the program counter
    public void decode_instruction(char opcode) throws Exception{
        // get address from program counter
        int address = Convert.B2I(REGISTERS.get_code_counter());

        // Register - Register Instructions
        if(opcode / 16 == 1){
            // Fetch Values from Memory
            char R1 = MEMORY.load_8bit(address + 1);
            char R2 = MEMORY.load_8bit(address + 2);

            // Update the Program Counter
            REGISTERS.increment_code_counter(3);

            // Run Instruction
            run(opcode, R1, R2);
        }

        // Register - Immediate Instructions
        else if(opcode / 16 == 3){
            // Fetch Values from Memory
            char R1 = MEMORY.load_8bit(address + 1);
            char[] imm = MEMORY.load_16bit(address + 2);

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
            char R1 = MEMORY.load_8bit(address + 1);
            char[] imm = MEMORY.load_16bit(address + 2);

            // Update the Program Counter
            REGISTERS.increment_code_counter(4);

            // Run Instruction
            runM(opcode, R1, imm);
        }

        // Single Operand Instructions
        else if(opcode / 16 == 7){
            // Fetch Values from Memory
            char R1 = MEMORY.load_8bit(address + 1);

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
            throw new Exception("CPU Error: Couldn't execute opcode " + opcode);
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
            default:
                break;
        }
    }
}
