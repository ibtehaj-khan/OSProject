import java.util.Arrays;
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

    public PCB(char[] PID, char Priority, String FileName){
        this.PID = PID;
        this.Priority = Priority;
        this.FileName = FileName;
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
        PageTable.addLast(page);
    }

    public void addPages(LinkedList<Integer> pages){
        PageTable.addAll(pages);
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

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    @Override
    public String toString() {
        String output = "PCB{" +
                "PID=" + Arrays.toString(PID) +
                ", Priority=" + Priority +
                ", Size=" + Size +
                ", FileName='" + FileName + '\'' +
                ", PageTable=" + PageTable +
                ", WaitingTime=" + WaitingTime +
                ", ExecutionTime=" + ExecutionTime +
                ", GPRegs=" + Arrays.toString(GPRegs) +
                ", SPRegs=" + Arrays.toString(SPRegs) +
                '}';
        return output;
    }
}
