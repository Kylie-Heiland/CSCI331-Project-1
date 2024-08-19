/*
 * Process Control Block with a linked list.
 * 
 * CSCI 331
 * 
 * Kylie Heiland
 * 9/16/2023
 */

public class ProcessControlBlocks implements PCBS
{

    private PCBEntry[] pcbs; //Each index of PCBEntry is a particular process. We assume that process 0 already exists.

    public ProcessControlBlocks(){ //If no parameter is passed when creating the object, the array size is set to 10.
        pcbs = new PCBEntry[10];
        pcbs[0] = new PCBEntry(-1); //Assume that process 0 exists.
        //System.out.println("The array has been created.");
    }
    
    public ProcessControlBlocks(int n){ //PCB is implemented as an array of size n.
        pcbs = new PCBEntry[n]; //Makes n number of PCBs.
        pcbs[0] = new PCBEntry(-1); //Assume that process 0 exists.
    }
    
    /*Creates a child process of process p.*/
    public String create(int p){
        if(pcbs[p] == null) //If process p does not exist, return error message, "No such a parent process."
            return "No such a parent process";
        int count = 1;
        int total = pcbs.length;
        int q = (p + count) % total;
        while(pcbs[q] != null && count < total){ //Loops through the PCB entries starting at pcbs[q] until an empty spot is found.
            count++;
            q = (q + 1) % total;
        }
        if(count == total) return "No space for new child process"; //If process control blocks are full, return "No space for new process."
        
        //Create the child processes and add to p's children list
        PCBEntry child = new PCBEntry(p);
        pcbs[q] = child;
        pcbs[p].children.add(q);
        return null; //Return null if the creation is success.
    }
    
    /*Destroys a child process of process p.*/
    public void destroy(int p){
        if(pcbs[p] == null) return; //If the process is already destroyed, do nothing.
        while(!pcbs[p].children.isEmpty()){ //Deletes each of p process's children.
            int q = pcbs[p].children.remove(0);
            destroy(q);
            pcbs[q] = null; //Deallocates element q from linked list.
        }
        
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
        ProcessControlBlocks pcbs = new ProcessControlBlocks(10);
       /* pcbs.create(0); //Creates first child process of pcbs[0], but stored as pcbs[1] since we assume that process 0 already exists.
        pcbs.create(0); //Creates second child process of pcbs[0], but stored as pcbs[2] since we assume that process 0 already exists.
        pcbs.create(2); //Creates first child process of pcbs[2], but stored as pcbs[3] since we assume that process 0 already exists.
        pcbs.create(0); //Creates third child process of pcbs[0], but stored as pcbs[4] since we assume that process 0 already exists.
        
        System.out.println(pcbs); //Prints current information.
        
        pcbs.destroy(0); //Destroy all family members derived from pcb[0].
        
        pcbs.create(0); //child process at location 1 
             pcbs.create(0); //child process at location 2
             pcbs.create(2); //child process at location 3
             pcbs.create(3); //child process at location 4
             pcbs.create(0); //child process at location 5
             System.out.println(pcbs); //Prints current information.
             pcbs.destroy(0); //destroy all child processes
        System.out.println("After destroy all children of process 0. pcbs information is: \n");
        System.out.println(pcbs);*/
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
 * OUTPUT
 * 
 * Process 0 Information: 
The children are processes: [1, 2, 4]

Process 1 Information: 
The parent is: 0
There is no child.

Process 2 Information: 
The parent is: 0
The children are processes: [3, 5]

Process 3 Information: 
The parent is: 2
The children are processes: [7]

Process 4 Information: 
The parent is: 0
There is no child.

Process 5 Information: 
The parent is: 2
The children are processes: [6]

Process 6 Information: 
The parent is: 5
There is no child.

Process 7 Information: 
The parent is: 3
The children are processes: [8]

Process 8 Information: 
The parent is: 7
There is no child.


After destroy all children of process 2. pcbs information is: 
Process 0 Information: 
The children are processes: [1, 2, 4]

Process 1 Information: 
The parent is: 0
There is no child.

Process 2 Information: 
The parent is: 0
There is no child.

Process 4 Information: 
The parent is: 0
There is no child.



 */
