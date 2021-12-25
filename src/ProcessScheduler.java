
import java.util.LinkedList;

public class ProcessScheduler {
    private MemoryManager MemoryManager;
    private LinkedList<PCB> HPReadyQueue;
    private LinkedList<PCB> LPReadyQueue;
    private LinkedList<PCB> RunningQueue;
    private Registers REGISTERS;
    private int lastQuanta = 0;

    public ProcessScheduler(MemoryManager MemoryManager, Registers registers) {
        this.MemoryManager = MemoryManager;

        HPReadyQueue = new LinkedList<PCB>();
        LPReadyQueue = new LinkedList<PCB>();
        RunningQueue = new LinkedList<PCB>();

        this.REGISTERS = registers;

    }


    public void AddProcess(PCB pcb){

        char Priority = pcb.getPriority();
        int index = 0;

        if(Priority >  15){
            // PCB in High Priority queue are High priority first
            for(int i = 0; i < HPReadyQueue.size();i++){
                // compare priority
                if(HPReadyQueue.get(i).getPriority() > Priority){
                    index = i;
                }
            }
            HPReadyQueue.add(index,pcb);
        }
        else {
            // PC in Low Priority queue are FIFO
            LPReadyQueue.addLast(pcb);
        }
    }


    public int NextProcess(){

        int Hquanta = 8;
        int Lquanta = 1;
        int quanta;
        PCB pcb;
        if(!RunningQueue.isEmpty()){
            // store the registers to pcb and move the pcb to it's queue
            pcb = RunningQueue.removeFirst();
            REGS2PCB(pcb);

            // set accounting information
            pcb.setExecutionTime(lastQuanta);
            AddWaitingTime(lastQuanta);

            // add process to ready queue
            AddProcess(pcb);
        }

        if(!HPReadyQueue.isEmpty()){
            // Priority Algorithm for High Priorities
            pcb = HPReadyQueue.removeFirst();

            lastQuanta = Hquanta;
            quanta = Hquanta;

        } else if(!LPReadyQueue.isEmpty()){
            // Round Robin for Low Priorities
            pcb = LPReadyQueue.removeFirst();

            lastQuanta = Lquanta;
            quanta = Lquanta;

        } else {
            // no process left to schedule, so stop the cpu
            return -1;
        }
        // move the registers to cpu from pcb
        PCB2REGS(pcb);

        // return time slice to run
        return quanta;
    }


    public void TerminateProcess(int timeSpent){
        // remove pcb from Running Queue
        PCB pcb = RunningQueue.removeFirst();
        REGS2PCB(pcb);

        // set accounting information
        pcb.setExecutionTime(timeSpent);
        AddWaitingTime(timeSpent);

        // deallocate memory pages
        LinkedList<Integer> PageTable = pcb.getPageTable();

        // print pcb

    }


    private void AddWaitingTime(int time){
        for(int i = 0; i < HPReadyQueue.size();i++){
            HPReadyQueue.get(i).setWaitingTime(time);
        }

        for(int i = 0; i < LPReadyQueue.size();i++){
            LPReadyQueue.get(i).setWaitingTime(time);
        }
    }


    private void PCB2REGS(PCB pcb){

        char [][] CPU_GPREGS = new char[16][2];
        char [][] CPU_SPREGS = new char[16][2];

        char [][] PCB_GPREGS = pcb.getGPRegs();
        char [][] PCB_SPREGS = pcb.getSPRegs();

        for(int i = 0; i<16; i++){
            CPU_GPREGS[i][0] = PCB_GPREGS[i][0];
            CPU_GPREGS[i][1] = PCB_GPREGS[i][1];

            CPU_SPREGS[i][0] = PCB_SPREGS[i][0];
            CPU_SPREGS[i][1] = PCB_SPREGS[i][1];
        }

        REGISTERS.setGp_regs(CPU_GPREGS);
        REGISTERS.setSp_regs(CPU_SPREGS);
    }

    private void REGS2PCB(PCB pcb){

        //  Registers Snapshots = new Registers();

        char [][] PCB_GPREGS = new char[16][2];
        char [][] PCB_SPREGS = new char[16][2];

        char [][] CPU_GPREGS = REGISTERS.getGp_regs();
        char [][] CPU_SPREGS = REGISTERS.getSp_regs();

        for(int i = 0; i<16; i++){
            PCB_GPREGS[i][0] = CPU_GPREGS[i][0];
            PCB_GPREGS[i][1] = CPU_GPREGS[i][1];

            PCB_SPREGS[i][0] = CPU_SPREGS[i][0];
            PCB_SPREGS[i][1] = CPU_SPREGS[i][1];
        }

        pcb.setGPRegs(PCB_GPREGS);
        pcb.setSPRegs(PCB_SPREGS);
    }
}
