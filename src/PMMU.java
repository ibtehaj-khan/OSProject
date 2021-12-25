import java.util.LinkedList;

//  Paged Memory Management Unit
public class PMMU {
    private int pagingBits = 9;
    private int offsetBits = 7;
    private Memory memory;
    private LinkedList<Integer> PageTable;

    public PMMU(){
        this.memory = new Memory();
        this.PageTable = new LinkedList<Integer>();
    }

    public void clearPageTable(){
        PageTable.clear();
    }

    public void addPage(int page){
        PageTable.addLast(page);
    }

    public void addPages(LinkedList<Integer> pages){
        this.PageTable.addAll(pages);
    }

    public LinkedList<Integer> getPageTable() {
        return PageTable;
    }

    private char[] Logical2PhysicalAddress(char[] logical) throws Exception{
        int index,offset,page;
        char[] physical = new char[2];

        //  extract page number and offset from logical address
        offset = logical[1] & ((1 << offsetBits) - 1);
        index = (logical[0] << 1) + (logical[1] >> offsetBits);

        //  check if page is valid
        try{
            page = PageTable.get(index);
        } catch(Exception e){
            throw new Exception("Virtual Address {"+index+"} is outside allowed paging space {" + PageTable+"}");
        }
        //  generate physical address

        physical[0] = (char) (page >> 1);
        physical[1] = (char) (((page & 1) << offsetBits) + offset);

        return physical;
    }



    //////////////    MEMORY READ APIs    //////////////
    public char read_8bit(char[] logical) throws Exception {
        char[] physical = Logical2PhysicalAddress(logical);
        return memory.load_8bit(physical);
    }

    public char read_8bit(int logical) throws Exception {
        char[] address = Convert.I2B(logical);
        char[] physical = Logical2PhysicalAddress(address);
        return memory.load_8bit(physical);
    }

    public char[] read_16bit(char[] logical) throws Exception {
        char[] physical = Logical2PhysicalAddress(logical);
        return memory.load_16bit(physical);
    }

    public char[] read_16bit(int logical) throws Exception {
        char[] address = Convert.I2B(logical);
        char[] physical = Logical2PhysicalAddress(address);
        return memory.load_16bit(physical);
    }



    //////////////    MEMORY WRITE APIs    //////////////
    public void write_8bit(char[] logical, char value) throws Exception {
        char[] physical = Logical2PhysicalAddress(logical);
        memory.store_8bit(physical,value);
    }

    public void write_8bit(int logical, char value) throws Exception {
        char[] address = Convert.I2B(logical);
        char[] physical = Logical2PhysicalAddress(address);
        memory.store_8bit(physical,value);
    }

    public void write_16bit(char[] logical, char[] value) throws Exception {
        char[] physical = Logical2PhysicalAddress(logical);
        memory.store_16bit(physical,value);
    }

    public void write_16bit(int logical, char[] value) throws Exception {
        char[] address = Convert.I2B(logical);
        char[] physical = Logical2PhysicalAddress(address);
        memory.store_16bit(physical,value);
    }
}
