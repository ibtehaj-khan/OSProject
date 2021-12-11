import java.util.LinkedList;

public class PCB {
    private char[] PID;
    private char Priority;
    private int Size;
    private String FileName;
    private char[][] GPRegs;
    private char[][] SPRegs;
    private LinkedList<Integer> PageTable;
    private int WaitingTime;
    private int ExecutionTime;

    public PCB(char[] PID, char Priority, String FileName, int Size){
        this.PID = PID;
        this.Priority = Priority;
        this.FileName = FileName;
        this.Size = Size;
    }

    public char[] getPID() {
        return PID;
    }

    public char getPriority() {
        return Priority;
    }

    public String getFileName() {
        return FileName;
    }

    public char[][] getGPRegs() {
        return GPRegs;
    }

    public void setGPRegs(char[][] GPRegs) {
        this.GPRegs = GPRegs;
    }

    public char[][] getSPRegs() {
        return SPRegs;
    }

    public void setSPRegs(char[][] SPRegs) {
        this.SPRegs = SPRegs;
    }

    public LinkedList<Integer> getPageTable() {
        return PageTable;
    }

    public void AddPage(int page) {
        PageTable.add(page);
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        WaitingTime = waitingTime;
    }

    public int getExecutionTime() {

        return ExecutionTime;
    }

    public void setExecutionTime(int executionTime) {
        ExecutionTime = executionTime;
    }
}
