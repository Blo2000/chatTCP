package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {
    public static void main(String[] args) {
        int puerto = 6001;
        ServerSocket servidor = null;
        HiloCliente hc;
        try {
            servidor = new ServerSocket(puerto);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Atendiendo petición del cliente");

                hc = new HiloCliente(cliente);
                hc.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
