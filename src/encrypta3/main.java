package encrypta3;

import java.io.CharArrayWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main {

    
    public static void main(String[] args) {
        try {
            Encryptacion e= new Encryptacion("fieccch");
            String clavePrivada="";
            String tAEncriptar="";
            String tEncriptado="";
            String tDesEncriptado="";

            

            for (int i = 0; i < 1000 && tAEncriptar.equals(tDesEncriptado); i++) {
                clavePrivada="";
                tAEncriptar="";
                tEncriptado="";
                clavePrivada=textoAleatorio();
                tAEncriptar=textoAleatorio();
                System.out.println("NUEVA CLAVE PRIVADA :: "+clavePrivada);
                e.cambiarClaverprivada(clavePrivada);
                System.out.println("Texto a encriptar ::"+tAEncriptar);
                tEncriptado=e.encripta(tAEncriptar);
                System.out.println("Texto encriptado ::"+tEncriptado);
                tDesEncriptado=e.desencripta(tEncriptado);
                System.out.println("Texto desEncriptado ::"+tDesEncriptado);
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static String textoAleatorio(){
        Random r= new Random();
        CharArrayWriter caw=new CharArrayWriter();
        for (int j = 0; j < r.nextInt(99); j++) {
            caw.write(r.nextInt(93)+33);
            
        }
        return caw.toString()+"a";
    }
}
