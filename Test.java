/**
 * Tests the two PCBs to see which one is faster. 
 * 
 * CSCI 331
 * 
 * Kylie Heiland
 * 9/16/2023
 */

public class Test 
{
     public static long test(int n, int version){
         PCBS pcbs; //Interface
         //pcbs = new ProcessControlBlocks(); //Ensures that the default constructor works.
         //return 1;
         if(version == 1)
             pcbs = new ProcessControlBlocks(100);
         else
             pcbs = new ProcessControlBlocks2(100);
             
         long start = System.currentTimeMillis();
         for(int i = 0; i < n; i++){
             pcbs.create(0); //child process at location 1
             pcbs.create(0); //child process at location 2
             pcbs.create(2); //child process at location 3
             pcbs.create(3); //child process at location 4
             pcbs.create(0); //child process at location 5
             
             pcbs.destroy(2); //destroy all child processes
             pcbs.destroy(1); 
             pcbs.destroy(5);
         }
         long end = System.currentTimeMillis();
         return end-start; 
     }
     
     public static void main(String[] args){
         long t1 = test(1000000, 1); //version 1 time
         long t2 = test(1000000, 2); //version 2 time
         long difference = t1 - t2;
         
         System.out.println("Version 1 used " + t1 + " milliseconds");
         System.out.println("Version 2 used " + t2 + " milliseconds");
        
         if(difference > 0)
             System.out.println("Version 1 is " + difference + " milliseconds slower.");
         else
             System.out.println("Version 2 is " + difference + " milliseconds slower."); 
     } 
}

/*OUTPUT 
 * 
Version 1 used 3652 milliseconds
Version 2 used 3647 milliseconds
Version 1 is 5 milliseconds slower.

Version 1 used 3677 milliseconds
Version 2 used 3671 milliseconds
Version 1 is 6 milliseconds slower.

Version 1 used 3606 milliseconds
Version 2 used 3542 milliseconds
Version 1 is 64 milliseconds slower.
 * 
 */
