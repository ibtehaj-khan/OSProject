import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.LinkedList;

public class ProgramLoader {
    private PMMU MemoryUnit;
    private MemoryManager MemoryManager;
    private ProcessScheduler ProcessScheduler;

    public ProgramLoader(PMMU memoryUnit, MemoryManager MemoryManager,ProcessScheduler ProcessScheduler) {
        this.MemoryUnit = memoryUnit;
        this.MemoryManager = MemoryManager;
        this.ProcessScheduler = ProcessScheduler;
    }

    public void loadFile(String filename) throws Exception {
        //  read file, or else throw a file not found exception
        try{
            // clear paging history from PMMU
            MemoryUnit.clearPageTable();

            File FileObj = new File(filename);
            FileReader READER = new FileReader(FileObj);
            BufferedReader BUFFER = new BufferedReader(READER);

            //  read meta data to create a process
            char priority = (char) BUFFER.read();
            if(priority > 0 && priority < 31){
                throw new Exception("Invalid Priority");
            }

            char[] PID = new char[2];
            PID[0] = (char) BUFFER.read();
            PID[1] = (char) BUFFER.read();

            PCB pcb = new PCB(PID,priority,filename);

            char[] data = new char[2];
            data[0] = (char) BUFFER.read();
            data[1] = (char) BUFFER.read();
            int dataSize = Convert.B2I(data);

            //  Allocate Pages to store data
            LinkedList<Integer> dataPages = MemoryManager.getPages(dataSize/128);
            MemoryUnit.addPages(dataPages);

            char bytevalue;
            int byteseeker, codeSize = 0;
            int data_base, data_limit;
            data_base = 0;

            //  for loop to read data segment
            for(byteseeker = 0; byteseeker < dataSize; byteseeker++){
                bytevalue = (char) BUFFER.read();
                MemoryUnit.write_8bit(byteseeker,bytevalue);
            }
            data_limit = (dataSize * 128) - 1;

            int code_base, code_counter,code_limit;
            code_base = data_limit + 1;

            byteseeker = code_base;
            bytevalue = (char) BUFFER.read();

            LinkedList<Integer> codePages = new LinkedList<Integer>();
            //  while loop to read code segment
            while(bytevalue != -1){
                if(byteseeker % 128 == 0){

                    int page = MemoryManager.getPage();

                    MemoryUnit.addPage(page);
                    codePages.addLast(page);
                }

                codeSize++;
                MemoryUnit.write_8bit(byteseeker++,bytevalue);
                bytevalue = (char) BUFFER.read();
            }
            code_counter = code_base;
            code_limit = (codePages.size()*128) - 1;

            // create stack
            int stack_base,stack_counter, stack_limit, stackSize = 50;
            stack_base = byteseeker;

            if((code_limit - stack_base) < stackSize){
                int page = MemoryManager.getPage();
                MemoryUnit.addPage(page);
                codePages.addLast(page);
                code_limit += 128;
            }

            stack_counter = stack_base;
            stack_limit = stack_base + stackSize;

            char[][] SP_REGS = new char[16][2];
            SP_REGS[0] = Convert.I2B(0);

            SP_REGS[1] = Convert.I2B(code_base);
            SP_REGS[2] = Convert.I2B(code_counter);
            SP_REGS[3] = Convert.I2B(code_limit);

            SP_REGS[4] = Convert.I2B(stack_base);
            SP_REGS[5] = Convert.I2B(stack_counter);
            SP_REGS[6] = Convert.I2B(stack_limit);

            SP_REGS[7] = Convert.I2B(data_base);
            SP_REGS[8] = Convert.I2B(data_limit);

            SP_REGS[9] = Convert.I2B(0);

            pcb.setGPRegs(new char[16][2]);
            pcb.setSPRegs(SP_REGS);
            pcb.setSize(dataSize + codeSize + stackSize);
            pcb.addPages(dataPages);
            pcb.addPages(codePages);

            ProcessScheduler.AddProcess(pcb);

        } catch(Exception e){
            throw new Exception("File Not Found");
        }
    }
}
