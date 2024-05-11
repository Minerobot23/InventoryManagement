
package com.mycompany.inventorymanagment;

/** InHouse Part That uses a MachineID
 *
 * @author Cristhian Garcia
 */
public class InHouse extends Part{
    
    
    //InHouse variables 
    private int machineId;
    
    
    /** Creates an In-House Part with the specified id, price, stock, min, max, machineID.
     * @param id The Part’s id.
     * @param name The Part's name.
     * @param price The Part’s price.
     * @param stock The amount of Part in stock.
     * @param min The minimum amount of Part in stock.
     * @param max  The maximum amount of Part in stock.
     * @param machineID  The In-House Part’s Machine ID.
    */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
        
    }
     /** Sets the Machine ID 
     * @param machineId  The In-House Part’s Machine ID to set.
     */
    public void setMachineID(int machineId) {
        
        this.machineId = machineId;
    }

     /** Gets the Machine ID 
     *
     * @return machineId
     */
    public int getMachineID() {
       
        
        return machineId;
    }

    
    
    
}
