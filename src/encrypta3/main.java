package encrypta3;

import java.util.logging.Level;
import java.util.logging.Logger;


public class main {

    
    public static void main(String[] args) {
        Encryptacion e= new Encryptacion("fieccch");
        String texto=e.encripta("holaaaaaaa");
        System.out.println(texto);
        try {
            System.out.println(e.desencripta(texto));
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
