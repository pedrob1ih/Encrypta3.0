package encrypta3;

import Modelo.Numero;
import Modelo.Texto;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Encryptacion {
    private int aClaves[];
    private int[][] aPatrones;
    private Fichero f;
    private String nombreFicheroClavePrivada;
    private int multiplicacion,lAA,lAB,lAC,fA,fB,fC;
    private String stringDRemplazo;
    
    public Encryptacion(String nombreFicheroClavePrivada) {
        this.aClaves=new int[3];
        this.aPatrones=new int[81][10];
        this.nombreFicheroClavePrivada=nombreFicheroClavePrivada;
        this.f= new Fichero(this.nombreFicheroClavePrivada);
        leerClavePrivada();
        this.stringDRemplazo="12345678900%/";
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
    public void cambiarClaverprivada(String texto) throws Exception{
        if(texto.equals(""))
            throw new Exception("Error Clave privada vacia");
        else{
            for (int i = 0; i < 10; i++) {
                texto+=texto;
            }
            CharArrayReader car=null;
            try{
                car= new CharArrayReader(texto.toCharArray());
                for (int i = 0; i < aPatrones.length; i++) {
                    for (int j = 0; j < aPatrones[1].length; j++) {
                        int valor=car.read();
                        if(valor<32)
                            throw new Exception("Error al generar la calve, tiene que usar caracteres entre 0 y 128");
                        else
                            valor=valor-32;
                        while (valor >99)
                            valor-=100;
                        this.aPatrones[i][j]=valor;
                    }
                }
            }
            catch(IOException e){
                throw new Exception("fallo al generar");
            }
            finally{
                if(car!=null) car.close();
            }
            guardarClavePrivada();
        }
    }
    
    
    //ELECCION DE CLAVE 
    private void generaClavepublica(){
        Random r=new Random();
        for(int i=0;i<aClaves.length;i++)
            //genera una clave q hace posible la desencriptacion
            aClaves[i]=r.nextInt(9)+1;
    }
    //ENCRIPTACION
    public String encripta(String texto){
        this.generaClavepublica();
        texto=texto.replace("\n", stringDRemplazo);
        this.fA=this.aClaves[0];
        this.fB=this.aClaves[1];
        this.fC=this.aClaves[2];
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
//        System.out.println(outPut.getTexto());
        return outPut.getTexto();
    }   //resta valor ascii
    private void algoritmoRestaStrMas0(Numero j,int frecuencia,int eles,String texto,Texto outPut){
        int k=0;
        for (int i = 0; i < frecuencia && j.getNumero()<texto.length(); i++) {
            k=(int)texto.charAt(j.getNumero());
            k-=this.aPatrones[eles][i];
            k+=10;
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
//        System.out.println("ENCRIPTAR SIN CLAVE "+texto);
        String outPut="";
        //la clave de descifre se coloca en la primera pos , la tercera y la ultima
        //1 23 1 2 3123
//        //System.out.println("clave 1 : "+this.aClaves[0]);
//        //System.out.println("clave 2 : "+this.aClaves[1]);
//        //System.out.println("clave 3 : "+this.aClaves[2]);
        texto=this.aClaves[0]+texto; //insercion de la primera clave
        
        for (int i = 0; i < 3; i++) 
        outPut=outPut+texto.toCharArray()[i]; // se añade la primera clave mas dos caracteres del texto
        
        outPut=outPut+this.aClaves[1]; // se añade la segunda clave al texto en la tercera posicion
        
        for (int i = 3; i <texto.toCharArray().length ; i++) 
        outPut=outPut+texto.toCharArray()[i];
        
        outPut=outPut+this.aClaves[2]; // se pasa la tercera clave al texto 
        return outPut;
    }   //ADDICION DE LA CLAVE
     
    //DESENCRIPTACION
    public String desencripta(String tEncriptado) throws Exception{
        
        this.fA=this.aClaves[0];
        this.fB=this.aClaves[1];
        this.fC=this.aClaves[2];
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
                aClaves[j]=Integer.parseInt(String.valueOf(tEncriptado.charAt(i)));
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
//            System.out.println((String)arSS.get(x.getNumero())+this.aPatrones[posicionArray][i]);
            int numero=Integer.parseInt((String)arSS.get(x.getNumero()))+this.aPatrones[posicionArray][i];
            String numeroRect="";
//            System.out.println("algoritmoDesSuma-numero "+numero);
            if(numero<10)
                numeroRect="000"+String.valueOf(numero);
            else if(numero<100)
                numeroRect="00"+String.valueOf(numero);
            else
                numeroRect="0"+String.valueOf(numero);
            arSS.set(x.getNumero(), numeroRect);
            x.setNumero(x.getNumero()+1);
        }
    }
    private String DesValToString(ArrayList arSS) throws Exception{
        CharArrayWriter caw=null;
        try{
            caw= new CharArrayWriter();
            for (int i = 0; i < arSS.size(); i++) {
//                System.out.println((String)arSS.get(i));
                caw.write((String)arSS.get(i));
            }
        }
        catch(Exception e){
            throw new Exception("fallo en el flujo al escribir");
        }
        finally{
            if(caw!=null) caw.close();
        }
//        System.out.println(caw.toString());
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
    
    public String getClavePrivada() {
        String outPut="";
        for (int i = 0; i < aPatrones.length; i++) {
            for (int j = 0; j < aPatrones[1].length; j++) {
                outPut+=aPatrones[i][j];
                outPut+=".";
            }
            outPut+=":\n";
        }
        return outPut;
    }
    private void guardarClavePrivada(){
        f.GuardaCreaFicheroClaves(aPatrones);
    }
    private void leerClavePrivada(){
        f.leeFicheroClaves(aPatrones);
    }
    public void guardarFichero(String nFichero,String texto){
        f.guardaFicheroTexto(nFichero,texto);
    }
    public String cargarFichero(String nFichero){
        return f.cargaFicheroTexto(nFichero);
    }
    
    //GETTERS & SETTERS

    public String getNombreFicheroClavesPrivadas() {
        return nombreFicheroClavePrivada;
    }
}


class Fichero {
    private String nombreFichero;

    public Fichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
    //CREACION O LECTURA DEL FICHERO ALEATORIO E INSERCION DE ARRAYS
    
    public void GuardaCreaFicheroClaves(int aNumEm[][]){
        //generacionn de un fichero con numeros aleatorio entre 0 y 90 separados por '.'
        
        FileOutputStream fos=null;
        DataOutputStream dos=null;
        try{
            fos = new FileOutputStream(this.nombreFichero);
            dos = new DataOutputStream(fos);
            for (int i = 0; i < aNumEm.length; i++) {
                dos.writeChar('.');
                for (int j = 0; j < aNumEm[1].length; j++) {
                    dos.writeInt(aNumEm[i][j]);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if (dos!=null) dos.close();
                if (fos!=null) fos.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }            
    }
    public void leeFicheroClaves(int aNumEm[][]){
        File f=null;
        FileInputStream fis=null;
        DataInputStream dis=null;
        try{
            f=new File(this.nombreFichero);
            if (f.exists()) {
                fis=new FileInputStream(f);
                dis=new DataInputStream(fis);
                int i=0;
                while (true) {    
                    dis.readChar();
                    for (int j = 0; j < aNumEm[1].length; j++) {
                        aNumEm[i][j]=dis.readInt();
                    }
                    i++;
                }
            }
        }
        catch (EOFException eof) { //...
        }
        catch (FileNotFoundException fnf) { //...
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
            if (dis!=null) dis.close();
            if (fis!=null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void guardaFicheroTexto(String nombreFichero,String texto){
        FileOutputStream fos=null;
        DataOutputStream dos=null;
        try{
            fos = new FileOutputStream(nombreFichero);
            dos = new DataOutputStream(fos);
            for (int i = 0; i < texto.length(); i++) {
                dos.writeChar(texto.charAt(i));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if (dos!=null) dos.close();
                if (fos!=null) fos.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }            
    }
    
    public String cargaFicheroTexto(String nombreFichero){
        File f=null;
        FileInputStream fis=null;
        DataInputStream dis=null;
        String outPut="";
        try{
            f=new File(nombreFichero);
            if (f.exists()) {
                fis=new FileInputStream(f);
                dis=new DataInputStream(fis);
                while (true) {    
                    outPut+=dis.readChar();
                }
            }
        }
        catch (EOFException eof) { //...
        }
        catch (FileNotFoundException fnf) { //...
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
            if (dis!=null) dis.close();
            if (fis!=null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outPut;
    }
    public String cargaFicheroEncriptado(File archivo){
        File f=null;
        FileInputStream fis=null;
        DataInputStream dis=null;
        String outPut="";
        try{
            f=archivo;
            if (f.exists()) {
                fis=new FileInputStream(f);
                dis=new DataInputStream(fis);
                while (true) {    
                    outPut+=dis.readChar();
                }
            }
        }
        catch (EOFException eof) { //...
        }
        catch (FileNotFoundException fnf) { //...
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
            if (dis!=null) dis.close();
            if (fis!=null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outPut;
    }
}
