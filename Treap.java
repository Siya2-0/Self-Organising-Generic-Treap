public class Treap<T extends Comparable<T>> {
    public Node<T> root = null;

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        return root.toString() + "\n" + toString(root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr == null)
            return "";
        String res = "";

        if (curr.left != null) {
            if (curr.right != null) {
                res += pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res += pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }

        if (curr.right != null) {
            res += pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    /*
     * Don't change anything above this line
     */

    public void insert(T data) throws DatabaseException {
        if(data==null)
        {
            return;
        }

        if(Find(data) !=  null)
        {
           throw DatabaseException.duplicateInsert(data);
        }

        if(root==null)
        {
            Node<T> Add=new Node<T>(data);
            root=Add;
            return;
        }

        Node<T> traverse=root;
        Node<T> prev=null;
        Node<T> Add= new Node<T>(data);
        while(traverse != null)
        {
            if(traverse.data.compareTo(data)>0){
                prev=traverse;
                traverse=traverse.left;
            }
            else if(traverse.data.compareTo(data)<0){
                prev=traverse;
                traverse=traverse.right;
            }
        }

        if(prev.data.compareTo(data)>0)
        {
            prev.left=Add;
        }
        else if(prev.data.compareTo(data)<0)
        {
            prev.right=Add;
        }
        //now check heap properties and balancing 
        while(! IsHeap(Add))
        {
            if(FindParent(Add).left==Add)
            {
                RightRotation(Add);
            }
            else if(FindParent(Add).right==Add)
            {
                LeftRotation(Add);
            }
        }
        //System.out.println(this);
    }

    public void RightRotation(Node<T> child)
    {
        if(child==null ||child==root)
            return;

        if(FindParent(FindParent(child))==null && FindParent(child)==root) //GP is null root is parent;
        {
            Node<T> rightchild=child.right;
            child.right=root;
            root.left=rightchild;
            root=child;
        } 
        else
        {
            Node<T> rightchild=child.right;
            Node<T> GP=FindParent(FindParent(child));
            Node<T> P=FindParent(child);
            child.right=P;
            P.left=rightchild;
            if(GP.left==P)
                GP.left=child;
            else if(GP.right==P)
                GP.right=child;

        }

    }

    public void LeftRotation(Node<T> child)
    {
        if(child==null || child==root)
            return;

        if(FindParent(FindParent(child))==null && FindParent(child)==root) //GP is null root is null;
        {
            Node<T> rightchild=child.left;
            child.left=root;
            root.right=rightchild;
            root=child;
        } 
        else
        {
            Node<T> rightchild=child.left;
            Node<T> GP=FindParent(FindParent(child));
            Node<T> P=FindParent(child);
            child.left=P;
            P.right=rightchild;
            if(GP.left==P)
                GP.left=child;
            else if(GP.right==P)
                GP.right=child;

        }

    }
    public Boolean IsHeap(Node<T> node)
    {
        if(node == root){
                return true;
        }

        if(node.priority < FindParent(node).priority)
            return true;

        return false;
    }

    public Node<T> FindParent(Node<T> child)
    {
        if(Find(child.data)==null||child==root)
            return null;

        Node<T> traverse=root;
        Node<T> prev=null;
        while(traverse != null)
        {
            if(traverse.data == child.data)
            {
                return prev;
            }
            else if(traverse.data.compareTo(child.data)>0)
            {
                prev=traverse;
                traverse=traverse.left;
            }
            else if(traverse.data.compareTo(child.data)<0)
            {
                prev=traverse;
                traverse=traverse.right;
            }
        }

        return null;

    }
  
    public Node<T> remove(T data) {

        if(root==null||Find(data)==null|| root==null )
            return null;

        if(root.data.compareTo(data)==0 && root.left==null && root.right==null)
        {
            Node<T> r=root;
            root=null;
            //System.out.println(this);
            return r;
        }

        Node<T> Delete=Find(data);
        Node<T> HighNode=null;
        while( !IsLeaf(Delete) )
        {
            HighNode=HigherChildNode(Delete);
            if(Delete.left==HighNode)
                RightRotation(HighNode);
            else if(Delete.right==HighNode)
                LeftRotation(HighNode);
           // System.out.println("Rotation");    
            //System.out.println(this);
        }

        /*if(OneChild(Delete))//never ngenning
        {
            Node<T>parent=FindParent(Delete);
            Node<T> child;
            if(Delete.right==null)
                child=Delete.left;
            else//want to make this else if
                child=Delete.right;

            if(parent.left==Delete)
            {
                parent.left=child;

            }
            else if(parent.right==Delete)
            {
                parent.right=child;
            }
        }*/
       // else
        //{
            Node<T>parent=FindParent(Delete);
            if(parent.left==Delete)
            {
                parent.left=null;

            }
            else if(parent.right==Delete)
            {
                parent.right=null;
            }


       // }
        //System.out.println("After removing" + data);
        //System.out.println(this);
        return Delete;
    }

    public Node<T> HigherChildNode(Node<T> node)
    {
        if(node==null)
            return null;

        Node<T> priority=null;
        if(node.left != null)
            priority=node.left;
        if(node.right != null)
        {
            if( priority != null && node.right.priority > priority.priority)
                priority=node.right;
            else if(priority==null)
            {
                priority=node.right;
            }

        }
        return priority;
        
    }

    public Boolean IsLeaf(Node<T> node)
    {
        if(node.left==null && node.right==null)
            return true;
        return false;
    }
    public Boolean OneChild(Node<T> node)
    {
        if(node.left==null && node.right != null)
            return true;
        else if(node.right==null && node.left != null)
        {
            return true;
        }

        
        return false;
    }

    public Node<T> access(T data) {

        if(root==null || Find(data)==null)
            return null;
        //accessing root
        Node<T> Search=Find(data);
        Search.priority++;
        while(!IsHeap(Search))
        {
            if(FindParent(Search).left==Search)
            {
                RightRotation(Search);
            }
            else if(FindParent(Search).right==Search)
            {
                LeftRotation(Search);
            }
        }
        return Search;
    }

    public Node<T> Find(T data)
    {
        if(root==null)
        {
            return null;//not found
        }
        Node<T> traverse=root;

        while(traverse != null)
        {
            if(traverse.data.compareTo(data)==0)
                return traverse;
            else if(traverse.data.compareTo(data)>0)
                traverse=traverse.left;
            else if(traverse.data.compareTo(data)<0)
                traverse=traverse.right;
        }

        return traverse;//null 
    }
}
