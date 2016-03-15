package encrypta3;

import java.io.CharArrayWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main {

    
    public static void main(String[] args) {
        try {
            Encryptacion e= new Encryptacion("fieccch");
            Random r= new Random();
            CharArrayWriter caw=new CharArrayWriter();
            String clavePrivada="";

            
            for (int i = 0; i < 1000; i++) {
                clavePrivada="";
                caw.reset();
                for (int j = 0; j < r.nextInt(9999); j++) {
                    caw.write(r.nextInt(93)+33);
                }
                clavePrivada=caw.toString();
                
                System.out.println("NUEVA CLAVE PRIVADA :: "+clavePrivada);
                e.cambiarClaverprivada(clavePrivada);
                String texto=e.encripta(clavePrivada);
                
                System.out.println(texto);
                System.out.println(e.desencripta(texto));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
