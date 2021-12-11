public class PCB {
    //  each parameter must be pointing towards memory
    private char[] PID;
    private char[] Priority;
    private char[] Size;
    private char[] FileName;
    private char[] GPRegs;
    private char[] SPRegs;
    private char[] CodePageTable;
    private char[] DataPageTable;
    private char[] WaitingTime;
    private char[] ExecutionTime;

    public PCB(char[] PID, char[] Priority, char[] FileName, char[] Size){
        this.PID = PID;
        this.Priority = Priority;
        this.FileName = FileName;
        this.Size = Size;
    }

    public char[] getPID() {
        return PID;
    }

    public char[] getPriority() {
        return Priority;
    }

    public char[] getFileName() {
        return FileName;
    }

    public char[] getGPRegs() {
        return GPRegs;
    }

    public void setGPRegs(char[] GPRegs) {
        this.GPRegs = GPRegs;
    }

    public char[] getSPRegs() {
        return SPRegs;
    }

    public void setSPRegs(char[] SPRegs) {
        this.SPRegs = SPRegs;
    }

    public char[] getCodePageTable() {
        return CodePageTable;
    }

    public void setCodePageTable(char[] codePageTable) {
        CodePageTable = codePageTable;
    }

    public char[] getDataPageTable() {
        return DataPageTable;
    }

    public void setDataPageTable(char[] dataPageTable) {
        DataPageTable = dataPageTable;
    }

    public char[] getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(char[] waitingTime) {
        WaitingTime = waitingTime;
    }

    public char[] getExecutionTime() {
        return ExecutionTime;
    }

    public void setExecutionTime(char[] executionTime) {
        ExecutionTime = executionTime;
    }
}
