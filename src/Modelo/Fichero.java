package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Fichero {
    private String nombreFichero;

    public Fichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
    //CREACION O LECTURA DEL FICHERO ALEATORIO E INSERCION DE ARRAYS
    
    public void savePrivateKey(String privateKey) throws IOException{        
        FileOutputStream fos=null;
        DataOutputStream dos=null;
        try{
            fos = new FileOutputStream(this.nombreFichero);
            dos = new DataOutputStream(fos);
            for (int i = 0; i < privateKey.length(); i++) {
                dos.writeChar(privateKey.charAt(i));
            }
        }
        catch(IOException e){
            throw new IOException(e.getMessage());
        }
        finally{
            try{
                if (dos!=null) dos.close();
                if (fos!=null) fos.close();
            }
            catch(IOException e){
                throw new IOException(e.getMessage());
            }
        }            
    }
    public String getPrivateKey() throws Exception{
        File f=null;
        FileInputStream fis=null;
        DataInputStream dis=null;
        String output="";
        try{
            f=new File(this.nombreFichero);
            if (f.exists()) {
                fis=new FileInputStream(f);
                dis=new DataInputStream(fis);
                while (true) {    
                    output+=dis.readChar();
                }
            }
        }
        catch (EOFException eof) { //...
        }
        catch (FileNotFoundException fnf) { //...
            throw new Exception("No File Found");
        }
        catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        finally {
            try {
            if (dis!=null) dis.close();
            if (fis!=null) fis.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return output;
    }

    public void saveFile(String nombreFichero,String texto){
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
    
    public String loadFile(String nombreFichero){
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
}
