package encrypta3;

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

public class Encryptacion {
    private int aClaves[];
    private int[][] aPatrones;
    private Fichero f;
    private String nombreFichero;
    int multiplicacion;
    int lAA;
    int lAB;
    int lAC;
    int fA;
    int fB;
    int fC;
    
    public Encryptacion(String nombreFichero) {
        this.aClaves=new int[3];
        this.aPatrones=new int[81][10];
        this.nombreFichero=nombreFichero;
        this.f= new Fichero(this.nombreFichero);
        leerClavePrivada();
        int multiplicacion=0;
        int lAA=0;
        int lAB=0;
        int lAC=0;
        int fA=0;
        int fB=0;
        int fC=0;
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

    //generacion de patrones apartir de texto
    public void cambiarClaverprivada(String texto) throws Exception{
        for (int i = 0; i < 100; i++) {
            texto+=texto;
        }
        CharArrayReader car=null;
        try{
            car= new CharArrayReader(texto.toCharArray());
            for (int i = 0; i < aPatrones.length; i++) {
                for (int j = 0; j < aPatrones[1].length; j++) {
                    int valor=car.read();
                    while (valor >30)
                        valor-=30;
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
        f.leeFicheroClaves(aPatrones);
    }
    
    
    //ELECCION DE CLAVE 
    private void generaClavepublica(){
        Random r=new Random();
        for(int i=0;i<aClaves.length;i++)
            //genera una clave q hace posible la desencriptacion
            aClaves[i]=r.nextInt(10);
    }
    //ENCRIPTACION
    public String encripta(String texto){
        this.generaClavepublica();
        if(aPatrones==null)
            f.leeFicheroClaves(aPatrones);
        texto=texto.replace("\n", "%/");
        this.fA=this.aClaves[0];
        this.fB=this.aClaves[1];
        this.fC=this.aClaves[2];
        this.multiplicacion=fA*fB*fC;
        this.lAA=multiplicacion/fA;
        this.lAB=multiplicacion/fB;
        this.lAC=multiplicacion/fC;
        return enInsertaClave(this.enRestaStrMas0(texto));
    }   //Métodos de Encriptacion
    private String enRestaStrMas0(String texto){ //resta valor a la cadena en ascii individualmente
        int x=0;
        String outPut="";
        //dependiendo de la frecuencia de cada una de las claves se usará un array u otro
        int a=0;
        while(x<texto.length()) {
            for (int i = 0; i < fA && x<texto.length(); i++) {
                a=(int)texto.charAt(x);
                a-=this.aPatrones[lAA][i];
                if(a<10)//dos ceros mas 
                    outPut=outPut+"00"+a;    
                else if(a<100) //un cero mas
                    outPut=outPut+"0"+a;    
                else 
                    outPut=outPut+a;    
                x++;
                //System.out.println(outPut);
            }
            for (int i = 0; i < fB && x<texto.length(); i++) {
                a=(int)texto.charAt(x);
                a-=this.aPatrones[lAB][i];
                if(a<10)//dos ceros mas 
                    outPut=outPut+"00"+a;    
                else if(a<100) //un cero mas
                    outPut=outPut+"0"+a;    
                else 
                    outPut=outPut+a;    
                x++;
                //System.out.println(outPut);
            }
            for (int i = 0; i < fC && x<texto.length(); i++) {
                a=(int)texto.charAt(x);
                a-=this.aPatrones[lAC][i];
                if(a<10)//dos ceros mas 
                    outPut=outPut+"00"+a;    
                else if(a<100) //un cero mas
                    outPut=outPut+"0"+a;    
                else 
                    outPut=outPut+a;    
                x++;
                //System.out.println(outPut);
            }
        }                
        return outPut;
    }   //resta valor ascii
    private String enInsertaClave(String texto){
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
        //quita la clave y la añade a el array de claves
        //separa dos a dos los caracteres del String de entrada
        f.leeFicheroClaves(aPatrones);
        ArrayList alSS=desConcat3a3(desQuitaClave(tEncriptado)); 
        // suma el valor que previamente ha sido restado
        desSuma(alSS); 
        String salida=DesValToString(alSS);
        return salida.replaceAll("%/", "\n");
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
        return tLimpio;
    } //quita la clave de encriptacion
    private ArrayList desConcat3a3(String tLimpio){
        ArrayList arSS=new ArrayList();
        int i=0;
        String temp="";
        while(i<tLimpio.length()-2){
            temp=String.valueOf(tLimpio.charAt(i));
            temp+=String.valueOf(tLimpio.charAt(i+1));
            temp+=String.valueOf(tLimpio.charAt(i+2));
            arSS.add(Integer.valueOf(temp));    
            i+=3;
//            //System.out.println("concatena : "+temp);
        }     
        return arSS;
    } //concatena dos a dos el texto Encriptado
    private void desSuma(ArrayList arSS){
        int x=0;
        
        //dependiendo de la frecuencia de cada una de las claves se usará un array u otro
        
        while(x<arSS.size()) {
            for (int i = 0; i < fA && x<arSS.size() ; i++) {
                arSS.set(x, ((int)arSS.get(x)+this.aPatrones[lAA][i]));
//                //System.out.println("suma = "+ ((int)arSS.get(i)+this.aPatrones[0][i]));
                x++;
            }
            for (int i = 0; i < fB && x<arSS.size() ; i++) {
                arSS.set(x, ((int)arSS.get(x)+this.aPatrones[lAB][i]));
//                //System.out.println("suma = "+ ((int)arSS.get(i)+this.aPatrones[1][i]));
                x++;
            }
            for (int i = 0; i < fC && x<arSS.size() ; i++) {
                arSS.set(x, ((int)arSS.get(x)+this.aPatrones[lAC][i]));
//                //System.out.println("suma = "+ ((int)arSS.get(i)+this.aPatrones[2][i]));
                x++;
            }
        }
    } //suma el valor en ascii
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

//    public int[][] getaPatrones() {
//        return aPatrones;
//    }

    public String getNombreFichero() {
        return nombreFichero;
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
