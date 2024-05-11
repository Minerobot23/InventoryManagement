
package com.mycompany.inventorymanagment;

/**Outsourced Part That uses a Company Name
 *
 * @author Cristhian Garcia
 */
public class Outsourced extends Part{
    
    
    //Outsourced variables 
    private String companyName;

    
    /** Creates an Outsourced Part with the specified id, price, stock, min, max, companyName.
     * @param id The Part’s id.
     * @param name The Part's name.
     * @param price The Part’s price.
     * @param stock The amount of Part in stock.
     * @param min The minimum amount of Part in stock.
     * @param max  The maximum amount of Part in stock.
     * @param companyName  The Outsourced Part’s Company Name.
    */
    
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
        
    }
    
    
    
     /** Sets the Company Name 
     * @param companyName  The Outsourced Part’s Company Name to set.
     */
    public void setCompanyName(String companyName) {
        
        this.companyName = companyName;
    }

     /** Gets the Company Name 
     * @return companyName
     */
    public String getCompanyName() {
       
        
        return companyName;
    }

}
