

public class Main {

    public static void main(String[] args) {
       /* task1();
        task2();
        task3();
       task4();*/
       task6();
    }
    public static void task6()
    {
        Treap<Integer> tree= new Treap<Integer>();
        try{
        tree.insert(5);
        System.out.println(tree);
        tree.insert(6);
        System.out.println(tree);
        tree.insert(0);
        System.out.println(tree);
        tree.insert(51);
        System.out.println(tree);
        tree.insert(7);
        System.out.println(tree);
        tree.insert(10);
        System.out.println(tree);
        tree.insert(20);
        System.out.println(tree);
        tree.insert(535);
        System.out.println(tree);
        tree.insert(15);
        System.out.println(tree);
        tree.insert(75);
        System.out.println(tree);
        tree.insert(100);
        System.out.println(tree);
        tree.insert(59);
        System.out.println(tree);
        tree.remove(20);
        System.out.println(tree);
        tree.remove(5);
        System.out.println(tree);
        tree.remove(15);
        System.out.println(tree);
        tree.remove(7);
        System.out.println(tree);
        tree.remove(59);
        System.out.println(tree);
        /*tree.remove(20);
        System.out.println(tree);
        tree.remove(6);
        System.out.println(tree);
        tree.remove(15);
        System.out.println(tree);
        tree.remove(5);
        System.out.println(tree);
        tree.remove(0);
        System.out.println(tree);
       tree.remove(75);
       System.out.println(tree);
       tree.remove(535);
       System.out.println(tree);
       tree.remove(7);
       System.out.println(tree);
       tree.remove(51);
       System.out.println(tree);
       tree.remove(10);
       System.out.println(tree);
       tree.insert(71);
       System.out.println(tree);
       tree.insert(66);
       System.out.println(tree);
       tree.remove(66);
       System.out.println(tree);*/
       
       
        }
        catch(DatabaseException e)
        {
            System.out.println(e.toString());
        }



    }
    public static void task5()
    {
        try{
            Treap<Integer> tree= new Treap<>();
            tree.insert(5);
            tree.remove(5);
            tree.remove(5);
            System.out.println(tree.toString());
        }
        catch(DatabaseException r)
        {
            System.out.println(r.toString());
        }
    }
    public static void task4()
    {
        String[] Column={"Name","Surname", "Try"};
        String[] Data1= {"Siya", "Mbuy5", "APP"};
        String[] Data2= {"Siya1", "Mbuy4", "APP1"};
        String[] Data3= {"Siya2", "Mbuy3", "APP4"};
        String[] Data4= {"Siya3", "Mbuy2", "APP2"};
        String[] Data5= {"Siya4", "Mbuy1", "APP5"};
        String[] Data6= {"Siya6", "Mbuy6", "APP8"};
        Database DB= new Database(Column, 5);
       try{
      DB.generateIndexAll();
        }
        catch(DatabaseException e)
        {
            System.out.println(e);
        }
        DB.PrintDB();
        try{
            System.out.println("Length: " + DB.updateAllWhere("Try", "Sit", "gh").length );
        }
        catch(DatabaseException e)
        {

        }
        try{
          DB.insert(Data1);
          DB.insert(Data2);
          DB.insert(Data3);
          DB.insert(Data4);
         DB.insert(Data5);
          DB.insert(Data6);

        }
        catch(DatabaseException e)
        {
            System.out.println(e.toString());
        }
        DB.PrintDB();
        System.out.println(" ");
        try{
           String[][] r= DB.updateAllWhere("Try", "APP5", "API");
            //DB.removeFirstWhere("Try", "APP1");
           // DB.removeFirstWhere("Try", "APP1");
            //for(int c=0; c<3; c++)
              //  System.out.println(r[c]);

            

        }
        catch(DatabaseException e)
        {
            System.out.println(e.toString());
        }
        DB.PrintDB();
    }
    public static void task3()
    {
        try{
        Treap<Integer> tree= new Treap<>();
        System.out.println(tree.toString());
        System.out.println(tree.access(25));
        System.out.println(tree.remove(26));
        tree.insert(25);
        System.out.println(tree.toString());
        for(int c=0; c<10; c++)
        {
            tree.insert(c);
        }
            try{
                tree.insert(5);
            }
            catch(DatabaseException e)
            {
                System.out.println(e);
            }
        System.out.println(tree.toString());
        tree.remove(5);
        System.out.println(tree.toString());





        }
        catch(DatabaseException e)
        {
            System.out.println(e);
        }
    }

    public static void task1() {
        /*
         * You are not given a Main for this task, because we want you to figure out how
         * to do it for yourself.
         * 
         * You are provided with a validTreap() function which will print out valid or
         * invalid for a passed in Treap.
         * 
         * Use this function to make sure that your heaps follow the rules set by the
         * Assignment.
         * 
         * Tip : Create a Main that inserts / deletes a lot of elements and call
         * validTreap after every step
         */
    }

    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         * 
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         */
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        Database db = new Database(columns, 100);

        String[][] modules = {
                { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
                { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
                { "UP0102", "Academic orientation 102", "0", "1", "Year" },
                { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
                { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
                { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
                { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
                { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
                { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
                { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
                { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
                { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
                { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
                { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
                { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
                { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
                { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
                { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
                { "PHY263", "General physics 263", "24", "2", "Sem 2" },
                { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
                { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
                { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
                { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
                { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
                { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
                { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
                { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
                { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
                { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
                { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
                { "FSK700", "Physics 700", "135", "4", "Year" }
        };

        try {
            for (String[] mod : modules) {
                db.insert(mod);
            }

            db.generateIndexAll();
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }

        System.out.println(db.indexes[0]);
        System.out.println(db.indexes[1]);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }
}
