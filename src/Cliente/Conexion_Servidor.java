/*
NOMBRES: VALERIA CONTRERAS RODRÍGUEZ, FÁTIMA MUÑOZ MORENO, ABIGAIL MENA ZAMORA, MANUEL ARGUIJO.
TEMA DEL PROGRAMA: Chat con Sockets.
DESCRIPCIÓN: : Chat grupal utilizando Sockets.
FECHA: 21/06/2021.
 */
package Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;

/**
 *
 * @author Manuel
 */
public class Conexion_Servidor implements ActionListener {

    private Socket socket;
    private JTextField Mensaje;
    private String usuario;
    private DataOutputStream salidaDatos;

    public Conexion_Servidor(Socket socket, JTextField Mensaje, String usuario) {
        this.socket = socket;
        this.Mensaje = Mensaje;
        this.usuario = usuario;
        try {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.salidaDatos.writeUTF(this.usuario + ": " + this.Mensaje.getText());
            this.Mensaje.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
