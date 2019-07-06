/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detail;

/**
 *
 * @author DPadhya
 */
public class Family {
    String Id;
   String name;    

    public Family(String Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public Family() {
    }

    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
   
}
