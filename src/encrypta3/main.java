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
            CharArrayWriter cawTexto=new CharArrayWriter();
            String clavePrivada="";
            String texto="";
            String texto2="";
            

            
            for (int i = 0; i < 1000; i++) {
                clavePrivada="";
                texto="";
                texto2="";
                caw.reset();
                for (int j = 0; j < r.nextInt(99); j++) {
                    caw.write(r.nextInt(93)+33);
                    cawTexto.write(r.nextInt(93)+33);
                }
                clavePrivada=caw.toString()+"a";
                texto=cawTexto.toString()+"a";
                System.out.println("NUEVA CLAVE PRIVADA :: "+clavePrivada);
                e.cambiarClaverprivada(clavePrivada);
                texto2=e.encripta(texto);
                System.out.println(texto2);
                System.out.println(e.desencripta(texto2));
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
