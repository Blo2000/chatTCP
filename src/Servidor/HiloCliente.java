package Servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread{
    private Socket cliente;
    private ArrayList<HiloCliente> clientesConectados;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public HiloCliente(Socket cliente, ArrayList<HiloCliente> clientesConectados) {
        this.cliente = cliente;
        this.clientesConectados = clientesConectados;

    }

    @Override
    public void run() {
        String mensaje;
        try {
            InputStream in = cliente.getInputStream();
            entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            salida = new DataOutputStream(out);
            System.out.println(clientesConectados.size());
            while (true) {
                mensaje = entrada.readUTF();
                System.out.println("Mensaje recibido de un cliente: " + mensaje);

                enviarMensajeATodos(mensaje);
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado");
            clientesConectados.remove(this);

        }
    }
    private void enviarMensajeATodos(String mensaje) {
        for (HiloCliente cliente : clientesConectados) {
            if (cliente != this) {
                try {
                    cliente.salida.writeUTF(mensaje);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
