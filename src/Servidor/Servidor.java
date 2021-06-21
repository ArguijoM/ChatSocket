/*
NOMBRES: VALERIA CONTRERAS RODRÍGUEZ, FÁTIMA MUÑOZ MORENO, ABIGAIL MENA ZAMORA, MANUEL ARGUIJO.
TEMA DEL PROGRAMA: Chat con Sockets.
DESCRIPCIÓN: : Chat grupal utilizando Sockets.
FECHA: 21/06/2021.
*/
package Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Manuel
 */
public class Servidor {
    public static void main(String[] args) {
        int puerto = 1212;
        int maximoConexiones = 10;
        ServerSocket servidor = null; 
        Socket socket = null;
        Mensajes mensajes = new Mensajes();
        
        try {
            servidor = new ServerSocket(puerto, maximoConexiones);

            while (true) {
                System.out.println("Servidor activo....");
                socket = servidor.accept();
                System.out.println("Se conectó cliente con la IP: " + socket.getInetAddress().getHostName());
                
                Conexion_Cliente cc = new Conexion_Cliente(socket, mensajes);
                cc.start();
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
