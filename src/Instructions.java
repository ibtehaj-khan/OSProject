public class Instructions {

    private Memory MEMORY;
    private Registers REGISTERS;

    public Instructions(Memory memory, Registers registers){
        this.MEMORY = memory;
        this.REGISTERS = registers;
    }

    //    //////// Register - Register Instructions ////////

    //    Copy R2 Content in R1
    public void MOV(byte R1, byte R2){

    }

    //    Add R2 in R1, and save the value in R1.
    //    Set Flag Register if value overflows
    public void ADD(byte R1, byte R2){

    }

    //    Subtract R2 from R1, and save the value in R1.
    //    Set Flag Register if value is negative
    public void SUB(byte R1, byte R2){

    }

    //    Multiply R1 and R2, and save the value in R1.
    //    Set Flag Register if value overflows
    public void MUL(byte R1, byte R2){

    }

    //    R1 is divided by R2, and save the value in R1.
    //    Throw exception if R2 is zero
    public void DIV(byte R1, byte R2) throws Exception{

    }

    //    Perform Bitwise Logical AND between R2 and R1, and save the value in R1
    public void AND(byte R1, byte R2){
    //    we don't have to convert the byte into bitset, just use bitwise operator

    }

    //    Perform Bitwise Logical OR between R2 and R1, and save the value in R1
    public void OR(byte R1, byte R2){
    //    we don't have to convert the byte into bitset, just use bitwise operator

    }

    //    //////// Register - Immediate Instructions ////////

    //    Copy imm value in R1.
    //    Handle Exceptions thrown by Convert methods.
    public void MOVI(byte R1, int imm) throws Exception{
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Add imm value in R1, and save the value in R1.
    //    Set Flag Register if value overflows
    //    Handle Exceptions thrown by Convert methods.
    public void ADDI(byte R1, int imm) throws Exception{
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Subtract imm value from R1, and save the value in R1.
    //    Set Flag Register if value is negative
    //    Handle Exceptions thrown by Convert methods.
    public void SUBI(byte R1, int imm){
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Multiply R1 and imm value, and save the value in R1.
    //    Set Flag Register if value overflows
    //    Handle Exceptions thrown by Convert methods.
    public void MULI(byte R1, int imm){
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    R1 is divided by imm value, and save the value in R1.
    //    Throw exception if R2 is zero
    //    Handle Exceptions thrown by Convert methods.
    public void DIVI(byte R1, int imm) throws Exception{
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Perform Bitwise Logical AND between imm value and R1, and save the value in R1
    public void ANDI(byte R1, int imm){
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Perform Bitwise Logical OR between imm value and R1, and save the value in R1
    public void ORI(byte R1, int imm){
    //    Convert the int to byte using Convert.I2B(int value)
    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BZ(int imm){

    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BNZ(int imm){

    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BC(int imm){

    }

    //    Check the flag register and jump to the offset
    //    Throws error if base/limit is crossed
    public void BS(int imm){

    }

    //    Jump to the offset
    //    Throws error if base/limit is crossed
    public void JMP(int imm){

    }

    //    Push Program counter on stack, and jump to the offset
    //    Throws error if base/limit is crossed
    public void CALL(int imm){

    }

    //    Do the service called by imm
    public void ACT(int imm){

    }

    //    //////// Memory Instructions ////////

    //    Load the value from memory present at offset and save that into R1.
    //    Throws error if base/limit is crossed
    public void MOVL(byte R1, int offset) throws Exception{

    }

    //    store R1 value into the Memory.
    //    Throws error if base/limit is crossed
    public void MOVS(byte R1, int offset) throws Exception{

    }

    //    //////// Single Operand Instructions ////////

    //    Shift R1 value to the Left by 1 bit, and save the value into R1
    public void SHL(byte R1){

    }

    //    Shift R1 value to the Right by 1 bit, and save the value into R1
    public void SHR(byte R1){

    }

    //    Save the left most value
    //    Shift R1 value to the Left by 1 bit,
    //    store the left most value in the right most position
    //    save the value into R1
    public void RTL(byte R1){

    }

    //    Save the right most value
    //    Shift R1 value to the right by 1 bit,
    //    store the right most value in the left most position
    //    save the value into R1
    public void RTR(byte R1){

    }

    //    Increase the value of R1 by 1, and store the value in R1
    public void INC(byte R1){

    }

    //    Decrease the value of R1 by 1, and store the value in R1
    public void DEC(byte R1){

    }

    //    Push the value of R1 on stack
    public void PUSH(byte R1){

    }

    //    Pop the value from stack, and store the value in R1
    public void POP(byte R1){

    }

    //    //////// No Operand Instructions ////////

    //    Pop the Original PC from stack
    public void RETURN(){

    }

    //    No Operation
    public void NOOP(){

    }

    //    Terminate the Process
    public void END(){

    }
}
