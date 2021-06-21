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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Manuel
 */
public class ConfiguracionCliente extends JDialog{
    private JTextField Usuario;
    private JTextField Host;
    private JTextField Puerto;

    public ConfiguracionCliente(JFrame principal) {
        super(principal, "Configuracion del cliente", true);
        
        JLabel jLabelUsuario = new JLabel("Usuario:");
        JLabel jLabelHost = new JLabel("Host:");
        JLabel jLabelPuerto = new JLabel("Puerto:");
        
        Usuario = new JTextField();
        Host = new JTextField();
        Puerto = new JTextField();
        
        JButton jButtonAceptar = new JButton("Aceptar");
        jButtonAceptar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 0, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(jLabelUsuario, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(jLabelHost, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        c.add(jLabelPuerto, gbc);
        
        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        c.add(Usuario, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(Host, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        c.add(Puerto, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 20, 50, 20);
        c.add(jButtonAceptar, gbc);
        
        this.pack(); 
        this.setLocation(0, 0);
        this.setResizable(false); 
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
    
    
    public String getUsuario(){
        return this.Usuario.getText();
    }
    
    public String getHost(){
        return this.Host.getText();
    }
    
    public int getPuerto(){
        return Integer.parseInt(this.Puerto.getText());
    }
}
