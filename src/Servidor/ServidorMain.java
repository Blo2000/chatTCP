package Servidor;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorMain {
    public static void main(String[] args) {
        int puerto = 6001;
        ServerSocket servidor = null;
        HiloCliente hc;
        ArrayList<DataOutputStream> clientesConectados = new ArrayList<>();
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Nuevo cliente conectado");
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Atendiendo petición del cliente");

                hc = new HiloCliente(cliente,clientesConectados);
                hc.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
