public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;
    
    public Cell(int databaseRow, String value)
    {
        
        this.value=value;
        this.databaseRow=databaseRow;
       
    }

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }
}
