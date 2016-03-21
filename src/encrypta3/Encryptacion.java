package encrypta3;

import Modelo.Numero;
import Modelo.Texto;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;

public class Encryptacion {
    private int aClavePublica[];
    private int[][] aCPrivada;
    private int multiplicacion,lAA,lAB,lAC,fA,fB,fC;
    private String stringDRemplazo;
    
    public Encryptacion(String cPrivada) {
        this.aClavePublica=new int[3];
        this.aCPrivada=new int[81][10];
        this.stringDRemplazo="12345678900%/";
        this.setPrivateKey(cPrivada);
    }
    public Encryptacion(String cPrivada,String stringRemplazo) {
        this.aClavePublica=new int[3];
        this.aCPrivada=new int[81][10];
        this.stringDRemplazo=stringRemplazo;
        this.setPrivateKey(cPrivada);
    }
    //metodos que no se usan
    private static String toCharrr(String mensaje){
        char a;
        char b;
        String mSalida="";
        String intermedio="";
        for (int i = 0; i < mensaje.length()-1; i++) {
            intermedio="";
            a=mensaje.charAt(i);
            b=mensaje.charAt(i+1);
            intermedio+=a;
            intermedio+=b;
            mSalida+=String.valueOf((char)Integer.parseInt(intermedio));
            i++;
        }
        return mSalida;
    }
    
    private static String charToInteger(String mensaje){
        CharArrayWriter caw=new CharArrayWriter();
        String mSalida="";
        for (int i = 0; i < mensaje.length(); i++) {
            mSalida+=String.valueOf((int)mensaje.charAt(i));
        }
        return mSalida;
    }
    
    private static List<Integer> factorial(long number) {
        long n = number;
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
          while (n % i == 0) {
            factors.add(i);
            n /= i;
          }
        }
        return factors;
    }

    //generacion de la clave privada a partir de una cadena de texto
    public boolean setPrivateKey(String texto) {
        if(texto.equals(""))
            return false;
        else{
            for (int i = 0; i < 10; i++) {
                texto+=texto;
            }
            CharArrayReader car=null;
            try{
                car= new CharArrayReader(texto.toCharArray());
                for (int i = 0; i < aCPrivada.length; i++) {
                    for (int j = 0; j < aCPrivada[1].length; j++) {
                        int valor=car.read();
                        if(valor<32)
                            return false;
                        else
                            valor=valor-32;
                        while (valor >99)
                            valor-=100;
                        this.aCPrivada[i][j]=valor;
                    }
                }
            }
            catch(IOException e){
                return false;
            }
            finally{
                if(car!=null) car.close();
            }
        }
        return true;
    }
    
    
    //ELECCION DE CLAVE 
    private void generaClavepublica(){
        Random r=new Random();
        for(int i=0;i<aClavePublica.length;i++)
            //genera una clave q hace posible la desencriptacion
            aClavePublica[i]=r.nextInt(9)+1;
    }
    //ENCRIPTACION
    public String encripta(String texto){
        this.generaClavepublica();
        texto=texto.replace("\n", stringDRemplazo);
        this.fA=this.aClavePublica[0];
        this.fB=this.aClavePublica[1];
        this.fC=this.aClavePublica[2];
        this.multiplicacion=fA*fB*fC;
        this.lAA=multiplicacion/fA;
        this.lAB=multiplicacion/fB;
        this.lAC=multiplicacion/fC;
        if(lAA>=81)
            lAA-=5;
        if(lAB>=81)
            lAB-=5;
        if(lAC>=81)
            lAC-=5;
        return enInsertaClave(this.restaStrMas0(texto));
    }   //Métodos de Encriptacion
    private String restaStrMas0(String texto){ //resta valor a la cadena en ascii individualmente
        Texto outPut=new Texto("");
        //dependiendo de la frecuencia de cada una de las claves se usará un array u otro
        Numero j=new Numero(0);
        while(j.getNumero()<texto.length()) {
            algoritmoRestaStrMas0(j, fA,lAA, texto, outPut);
            algoritmoRestaStrMas0(j, fB,lAB, texto, outPut);
            algoritmoRestaStrMas0(j, fC,lAC, texto, outPut);
        }      
        return outPut.getTexto();
    }   //resta valor ascii
    private void algoritmoRestaStrMas0(Numero j,int frecuencia,int eles,String texto,Texto outPut){
        int k=0;
        for (int i = 0; i < frecuencia && j.getNumero()<texto.length(); i++) {
            k=(int)texto.charAt(j.getNumero());
            k-=this.aCPrivada[eles][i];
            k+=100;
            if(k<10)//dos ceros mas 
                outPut.setTexto(outPut.getTexto()+"000"+k);
            else if(k<100) //un cero mas
                outPut.setTexto(outPut.getTexto()+"00"+k);
            else if(k<1000) //un cero mas
                outPut.setTexto(outPut.getTexto()+"0"+k);
            else 
                outPut.setTexto(outPut.getTexto()+k);
            j.setNumero(j.getNumero()+1);
        }
    }
    private String enInsertaClave(String texto){
        String outPut="";
        texto=this.aClavePublica[0]+texto; //insercion de la primera clave
        
        for (int i = 0; i < 3; i++) 
        outPut=outPut+texto.toCharArray()[i]; // se añade la primera clave mas dos caracteres del texto
        
        outPut=outPut+this.aClavePublica[1]; // se añade la segunda clave al texto en la tercera posicion
        
        for (int i = 3; i <texto.toCharArray().length ; i++) 
        outPut=outPut+texto.toCharArray()[i];
        
        outPut=outPut+this.aClavePublica[2]; // se pasa la tercera clave al texto 
        return outPut;
    }   //ADDICION DE LA CLAVE
     
    //DESENCRIPTACION
    public String unEncripta(String tEncriptado) throws Exception{
        
        this.fA=this.aClavePublica[0];
        this.fB=this.aClavePublica[1];
        this.fC=this.aClavePublica[2];
        this.multiplicacion=fA*fB*fC;
        this.lAA=multiplicacion/fA;
        this.lAB=multiplicacion/fB;
        this.lAC=multiplicacion/fC;
        if(lAA>=81)
            lAA-=5;
        if(lAB>=81)
            lAB-=5;
        if(lAC>=81)
            lAC-=5;
        
        //quita la clave y la añade a el array de claves
        //separa dos a dos los caracteres del String de entrada
        ArrayList alSS=desConcat4a4(desQuitaClave(tEncriptado)); 
        // suma el valor que previamente ha sido restado
        desSuma(alSS); 
        String salida=DesValToString(alSS);
        return salida.replaceAll(stringDRemplazo, "\n");
    } //desencritar
    private String desQuitaClave(String tEncriptado){
        String tLimpio="";
        int j=0;
        for (int i = 0; i < tEncriptado.length(); i++) {
            if (i!=0 && i!=3 && i!=tEncriptado.length()-1){
                tLimpio=tLimpio+tEncriptado.charAt(i);
            }    
            else{   
                aClavePublica[j]=Integer.parseInt(String.valueOf(tEncriptado.charAt(i)));
//                //System.out.println("desQuitaClave = "+aClaves[j]);
                j++;
            }
        }
//        System.out.println("DESENCRIPTAR SIN CLAVE "+tLimpio);
        return tLimpio;
    } //quita la clave de encriptacion
    private ArrayList desConcat4a4(String tLimpio){
        ArrayList arSS=new ArrayList();
        int i=0;
        String temp="";
        while(i<tLimpio.length()-3){
            temp=String.valueOf(tLimpio.charAt(i));
            temp+=String.valueOf(tLimpio.charAt(i+1));
            temp+=String.valueOf(tLimpio.charAt(i+2));
            temp+=String.valueOf(tLimpio.charAt(i+3));
            arSS.add(temp);    
            i+=4;
//            System.out.println("concatena : "+temp);
        }     
        return arSS;
    } //concatena dos a dos el texto Encriptado
    private void desSuma(ArrayList arSS){
        Numero x=new Numero(0);
        
        //dependiendo de la frecuencia de cada una de las claves se usará un array u otro
        
        while(x.getNumero()<arSS.size()) {
            algoritmoDesSuma(x, fA, lAA, arSS);
            algoritmoDesSuma(x, fB, lAB, arSS);
            algoritmoDesSuma(x, fC, lAC, arSS);
        }
    } //suma el valor en ascii  
    private void algoritmoDesSuma(Numero x,int frecuencia,int posicionArray,ArrayList arSS){
        for (int i = 0; i < frecuencia && x.getNumero()<arSS.size() ; i++) {
            int numero=Integer.parseInt((String)arSS.get(x.getNumero()))+this.aCPrivada[posicionArray][i];
            numero-=100;
            arSS.set(x.getNumero(), numero);
            x.setNumero(x.getNumero()+1);
        }
    }
    private String DesValToString(ArrayList arSS) throws Exception{
        CharArrayWriter caw=null;
        try{
            caw= new CharArrayWriter();
            for (int i = 0; i < arSS.size(); i++) {
                caw.write((int)arSS.get(i));
            }
        }
        catch(Exception e){
            throw new Exception("fallo en el flujo al escribir");
        }
        finally{
            if(caw!=null) caw.close();
        }
        return caw.toString();
    } //cambia del valor en ascii al caracter

    
    //OTROS METODOS
    public boolean isEncripy(String input){
        try{
            if ((int)input.charAt(0)>47 && (int)input.charAt(0)<58
                    && (int)input.charAt(3)>47 && (int)input.charAt(3)<58
                    && (int)input.charAt(input.length()-1)>47 && 
                    (int)input.charAt(input.length()-1)<58)
                return true;
        }
        catch(Exception e){
            
        }
        return false;
    }
    
    public String exportPrivateKey() {
        String outPut="";
        for (int i = 0; i < aCPrivada.length; i++) {
            for (int j = 0; j < aCPrivada[1].length; j++) {
                outPut+=aCPrivada[i][j];
                outPut+=".";
            }
            outPut+="\n";
        }
        return outPut;
    }
    public boolean importPrivateKey(String clave) {
        int j=0,k=0;
        String temp="";
        for (int i = 0; i < clave.length() && j<81; i++) {
            if(clave.charAt(i)!='.' && clave.charAt(i)!='\n'){
                if(temp.length()<2)
                    temp+=clave.charAt(i);
                if(temp.length()==2){
                    aCPrivada[j][k]=Integer.valueOf(temp);
                    temp="";
                    k++;
                }
                if(k==10){
                    temp="";
                    k=0;
                    j++;
                }
            }   
        }
        if(j==81 && k==0)
            return true;
        return false;
    }    
}