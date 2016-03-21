package Modelo;

import javax.swing.JTextField;


public class HiloInterfaz extends Thread{
    private Encryptacion e;
    private String input;
    private JTextField jTF;

    public HiloInterfaz(Encryptacion e, String input, JTextField jTF) {
        this.e = e;
        this.input = input;
        this.jTF = jTF;
    }
    
    @Override
    public void run() {
        jTF.setText(e.encripta(input));
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
