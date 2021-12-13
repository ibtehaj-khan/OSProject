import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramLoader {
    private MemoryManager MemoryManager;
    private ProcessScheduler ProcessScheduler;

    public ProgramLoader(MemoryManager MemoryManager,ProcessScheduler ProcessScheduler) {
        this.MemoryManager = MemoryManager;
        this.ProcessScheduler = ProcessScheduler;
    }

    public void loadFile(String filename) throws FileNotFoundException {
        //  read file, or else throw a file not found exception
        File FileObj = new File(filename);
        Scanner scan = new Scanner(FileObj);

        //  read meta data to create a process
        char priority;
        char[] PID, dataSize;
        //  for loop to read data segment
        //  for loop to read code segment
    }
}
