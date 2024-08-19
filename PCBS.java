/* Process Control Block Interface
 * CSCI 331
 * Kylie Heiland
 * 9/16/2023
 */
public interface PCBS
{
    /**Create a child process of process p. If process p does not exist, return error message, "No such a parent process." 
     * If process control blocks are full, return "No space for new process." 
     * Return null if the creation is success.
     */
    public String create(int p);
    
    /*Destroy the process control block at index p.*/
    public void destroy(int p);
}
