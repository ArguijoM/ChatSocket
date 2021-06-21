/*
NOMBRES: VALERIA CONTRERAS RODRÍGUEZ, FÁTIMA MUÑOZ MORENO, ABIGAIL MENA ZAMORA, MANUEL ARGUIJO.
TEMA DEL PROGRAMA: Chat con Sockets.
DESCRIPCIÓN: : Chat grupal utilizando Sockets.
FECHA: 21/06/2021.
*/
package Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Manuel
 */
public class Conexion_Cliente extends Thread implements Observer{
    private Socket socket; 
    private Mensajes mensajes;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
    public Conexion_Cliente (Socket socket, Mensajes mensajes){
        this.socket = socket;
        this.mensajes = mensajes;
        
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        String mensajeRecibido;
        boolean conectado = true;
        mensajes.addObserver(this);
        
        while (conectado) {
            try {
                mensajeRecibido = entradaDatos.readUTF();
                mensajes.setMensaje(mensajeRecibido);
            } catch (IOException ex) {
                System.out.println("Se desconectó: " + socket.getInetAddress().getHostName());
                conectado = false; 
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }   
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            salidaDatos.writeUTF(arg.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
