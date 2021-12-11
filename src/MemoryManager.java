import java.util.LinkedList;

public class MemoryManager {
    // Table containing Free Frames to provide Pages
    private LinkedList<Integer> FreeTable;

    public MemoryManager() {
        this.FreeTable = new LinkedList<Integer>();
        // Initialize FreeTable
        for(int i = 0; i < 512; i++){
            FreeTable.add(i);
        }
    }

    public int getPage() throws Exception{
        int page;
        try{
           page = FreeTable.removeFirst();
        } catch (Exception e){
            throw new Exception("No Free Pages left to allocate");
        }
        return page;
    }

    public void clearPage(int page){
        FreeTable.addLast(page);
    }
}
