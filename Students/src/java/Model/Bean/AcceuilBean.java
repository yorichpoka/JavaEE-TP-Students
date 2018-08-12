/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author POKA
 */
public class AcceuilBean extends Bean implements Serializable {

    /**
     * Creates a new instance of MainBean
     */
    public AcceuilBean() {
    }

    @PostConstruct
    @Override
    public void initialisation() { }
    
}
