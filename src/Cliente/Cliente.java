/*
NOMBRES: VALERIA CONTRERAS RODRÍGUEZ, FÁTIMA MUÑOZ MORENO, ABIGAIL MENA ZAMORA, MANUEL ARGUIJO.
TEMA DEL PROGRAMA: Chat con Sockets.
DESCRIPCIÓN: : Chat grupal utilizando Sockets.
FECHA: 21/06/2021.
*/
package Cliente;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
/**
 *
 * @author Manuel
 */
public class Cliente extends JFrame{
    private JTextArea mensajes;
    private Socket socket;
    
    private int puerto;
    private String host;
    private String usuario;
    
    public Cliente(){
        super("Cliente");
        mensajes = new JTextArea();
        mensajes.setEnabled(false); 
        mensajes.setLineWrap(true); 
        mensajes.setWrapStyleWord(true);
        JScrollPane scrollMensajesChat = new JScrollPane(mensajes);
        JTextField Mensaje = new JTextField("");
        JButton jButtonEnviar = new JButton("Enviar");
        
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        c.add(scrollMensajesChat, gbc);

        gbc.gridwidth = 1;        
        gbc.weighty = 0;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        gbc.insets = new Insets(0, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(Mensaje, gbc);

        gbc.weightx = 0;
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(jButtonEnviar, gbc);
        
        this.setBounds(300, 50, 300, 400);
        this.setVisible(true);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        

        ConfiguracionCliente cc = new ConfiguracionCliente(this);
        host = cc.getHost();
        puerto = cc.getPuerto();
        usuario = cc.getUsuario();
        System.out.println("Se conectó " + host + " en el puerto " + puerto + " con el nombre de ususario: " + usuario);
       
    
        try {
            socket = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        jButtonEnviar.addActionListener(new Conexion_Servidor(socket, Mensaje, usuario));
        
    }

    public void recibirMensajesServidor(){
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
      
        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                mensajes.append(mensaje + System.lineSeparator());
            } catch (IOException ex) {
                ex.printStackTrace();
                conectado = false;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                conectado = false;
            }
        }
    }
    
    public static void main(String[] args) { 
        Cliente c = new Cliente();
        c.recibirMensajesServidor();
    }
}
