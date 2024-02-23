package Servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread{
    private Socket cliente;
    private Object aux = new Object();

    public HiloCliente(Socket cliente) {
        this.cliente = cliente;


    }

    @Override
    public void run() {
        String mensaje;
        try {
            InputStream in = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(out);

            while (true) {
                mensaje = entrada.readUTF();
                System.out.println("Mensaje recibido de un cliente: " + mensaje);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
