/**
 * Process Control Block with a linked list.
 *
 * CSCI 331
 * 
 * Kylie Heiland
 * 
 * 9/16/23
 */
public class ProcessControlBlocks2 implements PCBS
{
    private PCBEntry2[] pcbs; //Each index of PCBEntry is a particular process. We assume that process 0 already exists.

    ProcessControlBlocks2(){ //If no parameter is passed when creating the object, the array size is set to 10.
        pcbs = new PCBEntry2 [10];
        pcbs[0] = new PCBEntry2(-1); //Assume that process 0 exists.
        //System.out.println("The array has been created.");
    }
    
    ProcessControlBlocks2(int n){ //PCB is implemented as an array of size n.
        pcbs = new PCBEntry2 [n];
        pcbs[0] = new PCBEntry2(-1); //Assume that process 0 exists.
    }
    
    /**Creates a child process of process p.*/
    public String create(int p){
        int count = 1;
        int q = (p + count) % pcbs.length;
        while(pcbs[q] != null && count < pcbs.length){ //Loops through the PCB entries starting at pcbs[q] until an empty spot is found.
            count++;
            q = (q + 1) % pcbs.length;
        }
        if(count == pcbs.length) return "No space for new child process"; //If process control blocks are full, return "No space for new process."
        
        //Create the child process.
        PCBEntry2 child = new PCBEntry2(p);
        pcbs[q] = child; 
    
        if(pcbs[p].child == -1){ //If process p does not have a child.
            //System.out.println(pcbs[p].child); //Tests to see if no child.
            //Add to p's children list
            pcbs[p].child = q;
        } else {
            //System.out.println(pcbs[p].child); //Tests to see if no child.
            int a = pcbs[p].child; //a is the process' child's younger sibling.
            //System.out.println(pcbs[a].ys);
            // a = pcbs[a].ys; 
            //Loops through the process' children until the youngest one is found.
            while(pcbs[a].ys != -1){
                a = pcbs[a].ys;
            }
            child.os = a; //Sets the index of the youngest child of process 
            pcbs[a].ys = q; //Sets the index of child as the younger sibling of the older sibling. 
        }
            
        return null; //Return null if the creation is success.
    }
    
    /*Destroys a child process of process p.*/
    public void destroy(int p){
        int parentIndex = p; //Holds the index of the parent of the child we are trying to remove.
        
        int a = pcbs[p].child; //a is the process' child.
        //Loops through the process' children until the youngest one is found.
        
        if(a == -1){ //If a has no children, there is nothing to delete.
            return;
        }
        while(pcbs[a].ys != -1){
            //System.out.println(pcbs[a].ys);
            a = pcbs[a].ys;
        }
        //System.out.println(a);
        
        
        
        //Loops back up to process p's first child, deleting all family members along the way.
        do{ 
            if(pcbs[a].child != -1){ //If the youngest one has a child, call recursively.
                destroy(a); //Those children get destroyed. 
            } else if(pcbs[a].os == -1){ //If the child has no older sibling.
                //Delete the child.
                int temp = pcbs[a].parent; //p is now the parent.
                pcbs[a] = null; //Removes child.
                a = temp;
                pcbs[a].child = -1; //The child does not exist anymore.
            } else { //If the child has an older sibling and no children.
                //Delete the child.
                int temp = pcbs[a].os; //p is now the older sibling.
                pcbs[a] = null; //Removes child.
                a = temp;
                pcbs[a].ys = -1; //The child does not exist anymore.
            }     
        }while(a != parentIndex);
         
        
        /*
        if(pcbs[p] == null) return; //If the process is already destroyed or process p, do nothing.
        if(pcbs[p].child == -1 && pcbs[p].os == -1 && pcbs[p].ys == -1){ //If the process has no child, younger/older sibling.
            temp = pcbs[p].parent; //p is now the parent.
            pcbs[p] = null; //Removes child.
            p = temp;
            pcbs[p].child = -1; //The child does not exist anymore.        
        }
        
        //int deletedChild = pcbs[p].child;
        //pcbs[p].child = -1; //Sets the process' child to -1.
        
        if(a == -1){ //If there is no child.
            a = p;
        }
        System.out.println(a); 
        
        
        
        //Loops back up to process p first child, deleting all family members along the way.
        while ((a != -1 && pcbs[a].os != -1) || (pcbs[a].os == -1 && pcbs[a].parent != -1)){
            System.out.println("This works");
            if(pcbs[a].child != -1){ //If the current child has children of its own.
                return;
            } else { //If the current child of process p has no children.
                if(pcbs[a].os != -1){
                int temp = pcbs[a].os; //a is now the older sibling.
                pcbs[a] = null; //Removes child.
                a = temp;
                pcbs[a].ys = -1; //The younger sibling does not exist anymore.
                System.out.println(pcbs[a].os != -1);
                } else if(pcbs[a].os == -1 && pcbs[a].parent != -1){ //If the current child has no older sibling, but it has p still, we have reached the end.
                    int temp = pcbs[a].parent; //a is now the parent.
                    pcbs[a] = null; //Removes child.
                    a = temp;
                    pcbs[a].child = -1; //The younger sibling does not exist anymore.
                }
            }
        }

        
        //int b = pcbs[a].os;
       // int temp;
        //For each of the parent's children, their children need to be destroyed as well. We will start by calling the youngest child with destroy().
        //This will continue until all children except the eldest have been called.
       
        
        // while(!pcbs[p].children.isEmpty()){ //Deletes each of p process's children.
           // int q = pcbs[p].children.remove(0);
         //   destroy(q);
           // pcbs[q] = null; //Deallocates element q from linked list. */
        
    }
    
    /* String describe all processes in this PCBS*/
    public String toString(){
        String result = "";
        for(int i = 0; i < pcbs.length; i++){//Loops through all processes.
            if(pcbs[i] != null){ //If the process exists:
                result += "Process " + i + " Information: \n" + pcbs[i].toString() + "\n";
            }
        }
        return result;
    }
    
    /*Tests this version out. */
    public static void main(String[] args){
      ProcessControlBlocks2 pcbs = new ProcessControlBlocks2(10);
        /*pcbs.create(0); //Creates first child process of pcbs[0], but stored as pcbs[1] since we assume that process 0 already exists.
        pcbs.create(0); //Creates second child process of pcbs[0], but stored as pcbs[2] since we assume that process 0 already exists.
        pcbs.create(2); //Creates first child process of pcbs[2], but stored as pcbs[3] since we assume that process 0 already exists.
        pcbs.create(0); //Creates third child process of pcbs[0], but stored as pcbs[4] since we assume that process 0 already exists. 
        
        System.out.println(pcbs); //Prints current information.
        
        pcbs.destroy(0); //Destroy all family members derived from pcb[0].
        System.out.println("After destroy all children of process 0. pcbs information is: ");
        System.out.println(pcbs);*/
        
        /*pcbs.create(0); //child process at location 1
          pcbs.create(0); //child process at location 2
          pcbs.create(2); //child process at location 3
          pcbs.create(3); //child process at location 4
          pcbs.create(0); //child process at location 5 */
             
        pcbs.create(0); //create a child for 0 at 1.
        pcbs.create(0); //create a second child for 0 at 2.
        pcbs.create(2); //create a first child for 2 at 3.
        pcbs.create(0); //create a third child for 0 at 4.
        pcbs.create(2); //create a second child for 2 at 5.
        pcbs.create(5); //create a first child for 5 at 6.
        pcbs.create(3); //create a first child for 3 at 7.
        pcbs.create(7); //create a first child for 7 at 8.
        
        System.out.println(pcbs); //Prints current information.
        
        pcbs.destroy(2); //destroy all child processes
        System.out.println("After destroy all children of process 2. pcbs information is: ");
        System.out.println(pcbs);
    }
}
/*
 * Process 0 Information: 
The first child is: 1.
There is no younger sibling.
There is no older sibling.

Process 1 Information: 
The parent is: 0.
There is no child.
The younger sibling is 2.
There is no older sibling.

Process 2 Information: 
The parent is: 0.
The first child is: 3.
The younger sibling is 4.
The older sibling is 1.

Process 3 Information: 
The parent is: 2.
The first child is: 7.
The younger sibling is 5.
There is no older sibling.

Process 4 Information: 
The parent is: 0.
There is no child.
There is no younger sibling.
The older sibling is 2.

Process 5 Information: 
The parent is: 2.
The first child is: 6.
There is no younger sibling.
The older sibling is 3.

Process 6 Information: 
The parent is: 5.
There is no child.
There is no younger sibling.
There is no older sibling.

Process 7 Information: 
The parent is: 3.
The first child is: 8.
There is no younger sibling.
There is no older sibling.

Process 8 Information: 
The parent is: 7.
There is no child.
There is no younger sibling.
There is no older sibling.


After destroy all children of process 2. pcbs information is: 
Process 0 Information: 
The first child is: 1.
There is no younger sibling.
There is no older sibling.

Process 1 Information: 
The parent is: 0.
There is no child.
The younger sibling is 2.
There is no older sibling.

Process 2 Information: 
The parent is: 0.
There is no child.
The younger sibling is 4.
The older sibling is 1.

Process 4 Information: 
The parent is: 0.
There is no child.
There is no younger sibling.
The older sibling is 2.
 */
