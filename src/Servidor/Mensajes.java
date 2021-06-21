/*
NOMBRES: VALERIA CONTRERAS RODRÍGUEZ, FÁTIMA MUÑOZ MORENO, ABIGAIL MENA ZAMORA, MANUEL ARGUIJO.
TEMA DEL PROGRAMA: Chat con Sockets.
DESCRIPCIÓN: : Chat grupal utilizando Sockets.
FECHA: 21/06/2021.
*/
package Servidor;
import java.util.Observable;
/**
 *
 * @author Manuel
 */
public class Mensajes extends Observable{
    private String mensaje;
    
    public Mensajes(){
    }
    
    public String getMensaje(){
        return mensaje;
    }
    
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
        this.setChanged();
        this.notifyObservers(this.getMensaje());
    }
}
