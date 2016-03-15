package encrypta3;

import java.util.logging.Level;
import java.util.logging.Logger;


public class main {

    
    public static void main(String[] args) {
        try {
            Encryptacion e= new Encryptacion("fieccch");
            String texto=e.encripta("holaaaaaaa");
//            e.cambiarClaverprivada("123123213");
            System.out.println(texto);
            System.out.println(e.desencripta(texto));
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
