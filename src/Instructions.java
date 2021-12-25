public class Instructions {

    private PMMU MemoryUnit;
    private Registers REGISTERS;
    private ArithmeticUnit ALU;
    private final int MAX_VALUE_16 = (int) Math.pow(2,16);


    public Instructions(PMMU MemoryUnit, Registers registers){
        this.MemoryUnit = MemoryUnit;
        this.REGISTERS = registers;
        this.ALU = new ArithmeticUnit();
    }

    //    //////// Register - Register Instructions ////////

    //    Copy R2 Content in R1
    public void MOV(char R1, char R2) throws Exception {
        // load value from R2
        char[] value = REGISTERS.load_gpr(R2);

        // Store value in R1
        REGISTERS.store_gpr(R1, value);
    }

    //    Add R2 in R1, and save the value in R1.
    //    Set Flag Register if value overflows
    public void ADD(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.ADD(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (check if left most bit is set)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));

    }

    //    Subtract R2 from R1, and save the value in R1.
    //    Set Flag Register if value is negative
    public void SUB(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.SUBTRACT(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Multiply R1 and R2, and save the value in R1.
    //    Set Flag Register if value overflows
    public void MUL(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.MULTIPLY(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    R1 is divided by R2, and save the value in R1.
    //    Throw exception if R2 is zero
    public void DIV(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.DIVIDE(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Perform Bitwise Logical AND between R2 and R1, and save the value in R1
    public void AND(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.AND(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Perform Bitwise Logical OR between R2 and R1, and save the value in R1
    public void OR(char R1, char R2) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);
        char [] val2 = REGISTERS.load_gpr(R2);

        int result = ALU.OR(val1,val2);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    //////// Register - Immediate Instructions ////////

    //    Copy imm value in R1.
    //    Handle Exceptions thrown by Convert methods.
    public void MOVI(char R1, char[] imm) throws Exception{
        REGISTERS.store_gpr(R1, imm);
    }

    //    Add imm value in R1, and save the value in R1.
    //    Set Flag Register if value overflows
    //    Handle Exceptions thrown by Convert methods.
    public void ADDI(char R1, char[] imm) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.ADD(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (check if left most bit is set)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Subtract imm value from R1, and save the value in R1.
    //    Set Flag Register if value is negative
    //    Handle Exceptions thrown by Convert methods.
    public void SUBI(char R1, char[] imm) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.SUBTRACT(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Multiply R1 and imm value, and save the value in R1.
    //    Set Flag Register if value overflows
    //    Handle Exceptions thrown by Convert methods.
    public void MULI(char R1, char[] imm) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.MULTIPLY(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    R1 is divided by imm value, and save the value in R1.
    //    Throw exception if R2 is zero
    //    Handle Exceptions thrown by Convert methods.
    public void DIVI(char R1, char[] imm) throws Exception{

        // Load Values of R1 in val1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.DIVIDE(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Perform Bitwise Logical AND between imm value and R1, and save the value in R1
    public void ANDI(char R1, char[] imm) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.AND(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Perform Bitwise Logical OR between imm value and R1, and save the value in R1
    public void ORI(char R1, char[] imm) throws Exception{

        // Load Values of R1 and R2 in val1 and val2
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.OR(val1,imm);

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BZ(char[] imm) throws Exception{
        boolean zero = REGISTERS.get_flags(1);
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                if(zero){
                    REGISTERS.set_code_counter(imm);
                }
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BNZ(char[] imm) throws Exception{
        boolean zero = REGISTERS.get_flags(1);
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                if(!zero){
                    REGISTERS.set_code_counter(imm);
                }
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BC(char[] imm) throws Exception{
        boolean carry = REGISTERS.get_flags(0);
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                if(carry){
                    REGISTERS.set_code_counter(imm);
                }
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BS(char[] imm) throws Exception{
        boolean sign = REGISTERS.get_flags(2);
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                if(sign){
                    REGISTERS.set_code_counter(imm);
                }
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Jump to the offset
    //    Throws error if base/limit is crossed
    public void JMP(char[] imm) throws Exception{
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                REGISTERS.set_code_counter(imm);
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Push Program counter on stack, and jump to the offset
    //    Throws error if base/limit is crossed
    public void CALL(char[] imm) throws Exception{
        int base = Convert.B2I(REGISTERS.get_code_base());
        int limit = Convert.B2I(REGISTERS.get_code_limit());
        int offset = Convert.B2I(imm);

        if((base + offset) < MAX_VALUE_16){
            if((base + offset) <= limit){
                // Push value here
                char[] counter = REGISTERS.get_code_counter();
                char[] address = REGISTERS.get_stack_counter();
                MemoryUnit.write_16bit(address,counter);
                REGISTERS.increment_stack_counter();

                // jump to the value
                REGISTERS.set_code_counter(imm);
            } else {
                throw new Exception("Execution Error: Code Counter Exceeds Code Limit");
            }
        } else {
            throw new Exception("Execution Error: address called is beyond addressable memory");
        }
    }

    //    Do the service called by imm
    public void ACT(char[] imm){

    }

    //    //////// Memory Instructions ////////

    //    Load the value from memory present at offset and save that into R1.
    //    Throws error if base/limit is crossed
    public void MOVL(char R1, char[] imm) throws Exception{
        int base = Convert.B2I(REGISTERS.get_data_base());
        int limit = Convert.B2I(REGISTERS.get_data_limit());
        int offset = Convert.B2I(imm);

        int address = base + offset;

        if(address > limit){
            throw new Exception("Data address out of bound");
        }

        char[] value = MemoryUnit.read_16bit(address);

        REGISTERS.store_gpr(R1,value);
    }

    //    store R1 value into the Memory.
    //    Throws error if base/limit is crossed
    public void MOVS(char R1, char[] imm) throws Exception{
        int base = Convert.B2I(REGISTERS.get_data_base());
        int limit = Convert.B2I(REGISTERS.get_data_limit());
        int offset = Convert.B2I(imm);

        int address = base + offset;

        if(address > limit){
            throw new Exception("Data address out of bound");
        }

        char[] value = REGISTERS.load_gpr(R1);

        MemoryUnit.write_16bit(address, value);
    }

    //    //////// Single Operand Instructions ////////

    //    Shift R1 value to the Left by 1 bit, and save the value into R1
    public void SHL(char R1) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.SHIFT_LEFT(val1);

        // clear the flag register
        REGISTERS.clear_flags();

        // set carry bit
        if(Convert.B2I(val1) > Short.MAX_VALUE){
            REGISTERS.set_flag(0,true);
        }

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Shift R1 value to the Right by 1 bit, and save the value into R1
    public void SHR(char R1) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.SHIFT_RIGHT(val1);

        // clear the flag register
        REGISTERS.clear_flags();

        // set carry bit
        if(Convert.B2I(val1) > Short.MAX_VALUE){
            REGISTERS.set_flag(0,true);
        }

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Save the left most value
    //    Shift R1 value to the Left by 1 bit,
    //    store the left most value in the right most position
    //    save the value into R1
    public void RTL(char R1) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.ROTATE_LEFT(val1);

        // clear the flag register
        REGISTERS.clear_flags();

        // set carry bit
        if(Convert.B2I(val1) > Short.MAX_VALUE){
            REGISTERS.set_flag(0,true);
        }

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Save the right most value
    //    Shift R1 value to the right by 1 bit,
    //    store the right most value in the left most position
    //    save the value into R1
    public void RTR(char R1) throws Exception{

        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.ROTATE_RIGHT(val1);

        // clear the flag register
        REGISTERS.clear_flags();

        // set carry bit
        if(Convert.B2I(val1) > Short.MAX_VALUE){
            REGISTERS.set_flag(0,true);
        }

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (registers are not using 2's complement)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Increase the value of R1 by 1, and store the value in R1
    public void INC(char R1) throws Exception{
        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.ADD(val1, Convert.I2B(1));

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (check if left most bit is set)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Decrease the value of R1 by 1, and store the value in R1
    public void DEC(char R1) throws Exception{
        // Load Values of R1
        char [] val1 = REGISTERS.load_gpr(R1);

        int result = ALU.SUBTRACT(val1, Convert.I2B(1));

        // clear the flag register
        REGISTERS.clear_flags();

        // set zero bit
        if(result == 0){
            REGISTERS.set_flag(1,true);
        }

        // set sign bit (check if left most bit is set)
        if(result > Short.MAX_VALUE){
            REGISTERS.set_flag(2,true);
        }

        // set overflow bit
        if(result > MAX_VALUE_16-1){
            REGISTERS.set_flag(3,true);
            // reduce the value to store in register
            result = result % MAX_VALUE_16;
        }

        // store value in R1
        REGISTERS.store_gpr(R1,Convert.I2B(result));
    }

    //    Push the value of R1 on stack
    public void PUSH(char R1) throws Exception{
        char[] value = REGISTERS.load_gpr(R1);  // get value from register
        char[] address = REGISTERS.get_stack_counter(); // get current address from stack

        try{
            REGISTERS.increment_stack_counter();    // check if stack has space, else throw error
            MemoryUnit.write_16bit(address,value);  // store value @ address we got from stack
        } catch(Exception e){
            throw new Exception("Execution Error: Stack Overflow");
        }
    }

    //    Pop the value from stack, and store the value in R1
    public void POP(char R1) throws Exception{
        char[] value;
        char[] address = REGISTERS.get_stack_counter(); // get current address from stack
        try{
            REGISTERS.decrement_stack_counter();    // check if stack has data, else throw error
            value = MemoryUnit.read_16bit(address); // load value from address we got from stack
            REGISTERS.store_gpr(R1,value);  // load value in register
        } catch(Exception e){
            throw new Exception("Execution Error: Stack is Empty");
        }
    }

    //    //////// No Operand Instructions ////////

    //    Pop the Original PC from stack
    public void RETURN() throws Exception{
        char[] value;
        char[] address = REGISTERS.get_stack_counter(); // get current address from stack
        try{
            REGISTERS.decrement_stack_counter();    // check if stack has data, else throw error
            value = MemoryUnit.read_16bit(address); // load value from address we got from stack
            REGISTERS.set_code_counter(value);  // load value in code counter
        } catch(Exception e){
            throw new Exception("Execution Error: Stack is Empty");
        }
    }

    //    No Operation
    public void NOOP(){

    }

    //    End the program
    public void END() throws Exception{
        throw new Exception("Program Call to Terminate");
    }
}
