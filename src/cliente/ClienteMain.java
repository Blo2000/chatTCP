package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMain {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String mensajeRecibido, mensajeEnviado;
        try {
            Socket cliente = new Socket("localhost", 6001);
            InputStream in = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(out);

            System.out.print("Se ha conectado al chad:\n");
            try {
                while(true){
                    mensajeEnviado = sc.nextLine();
                    salida.writeUTF(mensajeEnviado);
                    mensajeRecibido = entrada.readUTF();
                    System.out.println("Servidor: " + mensajeRecibido);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }catch (IOException e) {

            e.printStackTrace();

        }
    }
}
