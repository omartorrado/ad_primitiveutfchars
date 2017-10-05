/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitiveutfchars;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otorradomiguez
 */
public class Primitiveutfchars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta = "/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/primitiveutfchars/text3.txt";
        escribir(ruta);
        leer(ruta);
    }

    public static void escribir(String file) {
        String cadea = "esta e unha cadea\n";
        try {
            int totalBytes = 0;
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    dos.writeChars(cadea);
                    System.out.println("WriteChars escribiu: " + cadea);
                    System.out.println("WriteChars escribiu: " + (dos.size() - totalBytes) + " bytes");
                    totalBytes += (dos.size() - totalBytes);
                } else {
                    dos.writeUTF(cadea);
                    System.out.println("WriteUTF escribiu: " + cadea);
                    System.out.println("WriteUTF escribiu: " + (dos.size() - totalBytes) + " bytes");
                    totalBytes += (dos.size() - totalBytes);
                }
            }
            dos.close();
            System.out.println("Bytes totais escritos = " + totalBytes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Primitiveutfchars.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Primitiveutfchars.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void leer(String file) {
        String linea = "";
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            int bytesTotales = dis.available();
            int bytesCadea = 0;
            int bytesXaLidos = 0;
            int iteraciones = 1;
            while (dis.available() > 0) {
                if (bytesTotales == dis.available()) {
                    System.out.println("Leemos a primeira cadea utf: " + dis.readUTF());
                } else {
                    if (iteraciones == 1) {
                        for (int i = 0; i < 18; i++) {
                            linea += dis.readChar();
                        }
                        System.out.println("Leemos a primeira cadea chars: " + linea);
                        iteraciones++;
                        bytesXaLidos = bytesCadea;
                    } else {
                        System.out.println("Leemos a " + iteraciones + "ª cadea: " + dis.readUTF());
                        iteraciones++;
                    }
                }
                bytesCadea = bytesTotales - dis.available();
                if (dis.available() > 0) {
                    System.out.println("Numero de bytes leidos: " + (bytesCadea - bytesXaLidos));
                    System.out.println("Numero de bytes por leer = " + dis.available());
                }
            }
            dis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Primitiveutfchars.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Primitiveutfchars.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
