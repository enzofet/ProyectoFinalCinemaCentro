/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Asiento {
    
   private String asiento;
    
    public Asiento(){
    }

    public Asiento(String asiento) {
        this.asiento = asiento;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    
      
    @Override
    public boolean equals(Object a){
        Asiento asiento = (Asiento)a;
        if(this == a){
            return true;
        }
        if(this.getClass() != a.getClass()){
            return false;
        }
        return this.asiento.equalsIgnoreCase(asiento.getAsiento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(asiento);
    }

    @Override
    public String toString() {
        return "Asiento: " + asiento;
    }
}
