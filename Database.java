@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
        columnNames=cols;
        indexes= new Treap[cols.length];

        for(int c=0; c<cols.length; c++)
            indexes[c]=null;

        database= new String[maxSize][cols.length];
        for(int r=0; r<maxSize; r++)
        {
            for(int c=0; c<cols.length; c++)
                database[r][c]=null;
        }
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
        if(newRowDetails.length != columnNames.length)
            throw DatabaseException.invalidNumberOfColums();

       
        /*if(isFull() || InsertIndex()==-1)
            throw DatabaseException.databaseFull();*/

        int InsertRow=InsertIndex();
       // Cell Add= new Cell(InsertRow, newRowDetails[c]);
       Cell test=CheckDuplicate(newRowDetails);
        if(test != null)
            throw DatabaseException.duplicateInsert(test);
    
        if(InsertRow==-1)
            throw DatabaseException.databaseFull();

        try{

            for(int c=0; c<columnNames.length; c++)
            {

                Cell Add= new Cell(InsertRow, newRowDetails[c]);
                database[InsertRow][c]=newRowDetails[c];

                try{
                    if(indexes[c] != null){
                        
                        indexes[c].insert(Add);
                    }
                }
                catch(DatabaseException e)
                {
                    throw e;
                }
            }

        }
        catch(DatabaseException e)
        {
            if(InsertRow != -1)
            {
                for(int c=0; c<columnNames.length; c++)
                {
                    database[InsertRow][c]=null;
                    Cell Add= new Cell(InsertRow, newRowDetails[c]);
                    if(indexes[c] != null)
                        indexes[c].remove(Add);
                }
            }
            throw e;

        }
        
        
    }
    public Cell CheckDuplicate( String[] Row)
    {
            int r =InsertIndex();
            boolean rt=true;
            Cell f;
            for(int c=0; c<columnNames.length; c++)
            {
                if(indexes[c] != null)
                {
                    f= new Cell(r, Row[c]);
                    if(indexes[c].access(f) != null)
                    {
                        return f;
                        //throw  DatabaseException.duplicateInsert(f);
                    }
                    else{
                        //rt=false;
                    }

                }
                else{
                    //rt=false;
                }

            }
            //return rt;
        
     
        return null;
    }

    public Boolean isFull()
    {
        for(int r = 0; r < database.length; r++)
        {
            if(database[r][0]==null)
                return false;
        }
        return true;
    }
    public int InsertIndex()
    {
        for(int r = 0; r < database.length; r++)
        {
            if(database[r][0]==null)
                return r;
        }
        return -1;
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {

        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        String[]RT= new String[columnNames.length];


        if(DeleteRowIndex(col, data)==-1)
        {
            return new String[0];
        }
        else{
            int r=DeleteRowIndex(col, data);
            for(int c=0; c<columnNames.length; c++)
            {
                if(indexes[c] != null){
                    indexes[c].remove(new Cell(r,database[r][c]));
                }

                RT[c]=database[r][c];    //deep copying
                database[r][c]=null;
                
            }

        }
        return RT;


    }
    public int DeleteRowIndex(String col, String data)
    {
        for(int c=0; c<database.length; c++)
        {
            if(Whichtreap(col)!=-1 && database[c][Whichtreap(col)]==data)
                return c;
        }
        return -1;
    }
    public int Whichtreap(String col)
    {
        for(int c=0;c<columnNames.length; c++)
        {
            if(columnNames[c]==col)
                return c;
        }
        return -1;
    }

    public Boolean DoesColExist(String col)
    {
        for(int c=0; c< columnNames.length;c++ )
        {
            if(columnNames[c]==col)
                return true;
        }
        return false;
    }
    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        //System.out.println(HowManyRows(col, data));
        if(HowManyRows(col, data)==0)
        {
            return new String[0][0];
        }

        String[][] RT=new String[HowManyRows(col, data)][columnNames.length];
        int f=HowManyRows(col, data);
        for(int c=0; c<f; c++)
        {
            try{
            RT[c]=removeFirstWhere(col, data);//possible issue make size accurate if there is exception
            }
            catch(DatabaseException r)
            {
                System.out.println(r.toString());
            }
        }

        return RT;
    }
    public int HowManyRows(String col, String data)
    {
        int Count=0;
        for(int c=0; c<database.length; c++)
        {
            if( Whichtreap(col)!= -1 && database[c][Whichtreap(col)] == data)
                Count++;
        }
        return Count;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        if(DeleteRowIndex(col, data)==-1|| HowManyRows(col, data)==0)
            return new String[0];
        
        String[] RT;
        int RowIndex=DeleteRowIndex(col, data);
        RT=database[RowIndex];//shallow copying
       // for(int c=0; c<columnNames.length; c++)
        //{
            if(indexes[Whichtreap(col)] != null)
                indexes[Whichtreap(col)].access(new Cell(RowIndex,database[RowIndex][Whichtreap(col)]));
       // }


        return RT;
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {

        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        if(HowManyRows(col, data)==0)
        {
            return new String[0][0];
        }
        String[][] RT=new String[HowManyRows(col, data)][columnNames.length];
        int f=HowManyRows(col, data);
        int index=0;
        for(int c=0; c<database.length; c++)
        {
            if(database[c][Whichtreap(col)]==data)
            {
                RT[index]=database[c];
                index++;
            }
               // try{
           // RT[c]=findFirstWhere(col, data);
            //}
            //catch(DatabaseException r)
            //{

            //}
        }

        return RT;

    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        if(HowManyRows(col, updateCondition)==0)
        {
            return new String[0];
        }

        String[] RT;
        int RowIndex=DeleteRowIndex(col, updateCondition);
        String temp= database[RowIndex][Whichtreap(col)];
        database[RowIndex][Whichtreap(col)]=data;
        RT=database[RowIndex];
        if(indexes[Whichtreap(col)] != null)
        {
            indexes[Whichtreap(col)].remove(new Cell(RowIndex, temp));
            indexes[Whichtreap(col)].insert(new Cell(RowIndex, data));
        }


        return RT;
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        if(HowManyRows(col, updateCondition)==0)
        {
            return new String[0][0];
        }

        String[][] RT=new String[HowManyRows(col, updateCondition)][columnNames.length];
        int rows=HowManyRows(col, updateCondition);
        for(int c=0; c<rows; c++)//possible issue
        {
           // try
            //{
                RT[c]=updateFirstWhere(col, updateCondition, data);
            //}
            //catch(DatabaseException r)
            //{

            //}
        }

        return RT;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        if(indexes[Whichtreap(col)]!= null)
            return indexes[Whichtreap(col)];

        int colindex=Whichtreap(col);
        indexes[colindex]= new Treap<>();
        try{
            for(int r=0; r<database.length; r++)
            {
                if(database[r][colindex] != null)
                    indexes[colindex].insert(new Cell(r, database[r][colindex]));
            }
        }
        catch(DatabaseException e)
        {
            indexes[colindex]=null;
            throw e;

        }     

        
        return indexes[colindex];
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {

        for(int c=0; c<columnNames.length; c++){
            try{
            generateIndexOn(columnNames[c]);
            }
            catch(DatabaseException e)
            {

            }

        }
        
        return indexes;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        if(!DoesColExist(col))
            throw DatabaseException.invalidColumnName(col);

        return HowManyRows(col, data);
    }

    
}
