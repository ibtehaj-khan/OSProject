public class Main {
    public static void main(String[] args) throws Exception {
        CU CU = new CU();

        // program name
        String filename = "C:\\Users\\ibteh\\IdeaProjects\\OS_Project\\src\\p1.txt";

        // address where program should start
        int base = 5000;

        // load program in memory, and return the address
        int limit = CU.load_program_in_memory(filename,base);

        // when program is in memory, then registers should have data for the program
        CU.load_data_in_registers(base,limit);

        // Run program i.e. cpu will start processing instruction
        CU.run_program();
    }
}
