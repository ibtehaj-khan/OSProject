//  Group Members
//  Muhammad Ibtehaj Khan - 18077
//  Mohammad Ahsan Siddiqui - 18076
//  Abdullah Tahir - 14050

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        CU CPU = new CU();
        String dirname = "C:\\Users\\ibteh\\IdeaProjects\\OS_Project\\src\\demo_files";


        RunFilesFromDirectory(dirname, CPU);

        // Run program i.e. cpu will start processing instruction
         CPU.execute();

//        readFile("C:\\Users\\ibteh\\IdeaProjects\\OS_Project\\src\\demo_files\\noop");
    }

    public static void RunFilesFromDirectory(String dirname, CU CPU) throws Exception{

            File dir = new File(dirname);
            for (File file : dir.listFiles()) {
//                System.out.println(file.getPath());
                CPU.loadProgram(file.getPath());
//                readFile(file.getPath());

            }

    }

    public static void readFile(String filename) throws Exception {
        DataInputStream BUFFER = new DataInputStream(new BufferedInputStream(new FileInputStream( filename ) ) );
        int value = 0;
        int counter = 0;
        while(value != -1){
            value = BUFFER.read();
            System.out.println(counter++ + ")\t\t" + value);
        }
    }
}
