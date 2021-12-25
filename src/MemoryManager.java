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

    public LinkedList<Integer> getPages(int n) throws Exception{
        LinkedList<Integer> pages = new LinkedList<Integer>();
        for(int i = 0; i < n; i++) pages.add(getPage());
        return pages;
    }

    public void clearPage(int page){
        FreeTable.addLast(page);
    }

    public void clearPages(LinkedList<Integer> pages){
        FreeTable.addAll(pages);
    }
}
