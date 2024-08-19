/*
 * Refers to each entry in a PCB entry, NOT using LinkedList. Each entry shows that process, their older sibling, younger sibling, and oldest child.  
 *
 * CSCI 331
 * Kylie Heiland
 * 
 * 9/17/23
 */
public class PCBEntry2 
{
    protected int parent, child, ys, os; //Holds index of parent, child, younger sibling, and older sibling.
    
    public PCBEntry2(){ //No default constructor should be used, however, it is implemented to avoid any issues.
        System.out.println("No parent given. Exiting program.");
        System.exit(1); //Stops program.
    }
    
    //A new parent is created, along with a new ArrayList which will hold the number of children the parent has.
    public PCBEntry2(int parent){
        this.parent = parent;
        child = -1;
        ys = -1;
        os = -1;
    }
    
    /* Returns a String describing the current process' parent and its corresponding children. */
    public String toString(){
        String result = "";
        if(parent != -1)
            result = "The parent is: " + parent + ".\n"; //Despite there being a child or not, the parent is always printed out.
        if(child == -1) //If the parent has no child.
            result += "There is no child.\n";
        else //If the parent has one or more children.
            result += "The first child is: " + child + ".\n";
        if(ys == -1) //If there is no younger sibling.
            result += "There is no younger sibling.\n";
        else //If there is a younger sibling.
            result += "The younger sibling is " + ys + ".\n";
        if(os == -1) //If there is no older sibling.
            result += "There is no older sibling.\n";
        else //If there is an older sibling
            result += "The older sibling is " + os + ".\n";
        return result;
    }
}
