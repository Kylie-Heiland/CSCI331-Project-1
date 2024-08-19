/* 
 * Refers to each entry in a PCB entry, using LinkedList. Each entry shows that process and the process' children.  
 * 
 * CSCI 331
 * 
 * Kylie Heiland
 * 9/16/23
 * 
 */
import java.util.*;
public class PCBEntry
{
    protected int parent; //Holds index of parent.
    protected LinkedList<Integer> children; //Holds index of children.
    
    public PCBEntry(){ //No default constructor should be used, however, it is implemented to avoid any issues.
        System.out.println("No parent given. Exiting program.");
        System.exit(1); //Stops program.
    }
    
    //A new parent is created, along with a new ArrayList which will hold the number of children the parent has.
    public PCBEntry(int parent){
        this.parent = parent;
        children = new LinkedList<Integer>();
    }
    
    /* Returns a String describing the current process' parent and its corresponding children. */
    public String toString(){
        String result = "";
        if(parent != -1)
            result += "The parent is: " + parent + "\n"; //Despite there being a child or not, the parent is always printed out.
        if(children.size() == 0) //If the parent has no child.
            result += "There is no child.\n";
        else //If the parent has one or more children.
            result += "The children are processes: " + children.toString() + "\n";
        return result;
    }
}
