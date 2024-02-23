package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteMain {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        try {
            Socket cliente = new Socket("localhost", 6001);
            InputStream in = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(out);
            ExecutorService executor = Executors.newSingleThreadExecutor();

            System.out.print("Se ha conectado al chad:\n");
            executor.submit(() -> {
                try {
                    while (true) {
                        String mensajeRecibido = entrada.readUTF();
                        System.out.println("Servidor: " + mensajeRecibido);
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            });
            executor.submit(() -> {
                try {
                    while (true) {
                        String mensajeEnviado = sc.nextLine();
                        salida.writeUTF(mensajeEnviado);
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            });

            // Cerrar el ExecutorService al salir
            Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdown));

        }catch (IOException e) {

            e.printStackTrace();

        }
    }
}
