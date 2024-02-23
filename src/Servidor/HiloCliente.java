package Servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread{
    private Socket cliente;
    private Object aux = new Object();
    private ArrayList<DataOutputStream>  clientesConectados = new ArrayList<>();
    public HiloCliente(Socket cliente, ArrayList<DataOutputStream> Conectados) {
        this.cliente = cliente;
        this.clientesConectados = Conectados;

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
                System.out.println(clientesConectados.size());
                synchronized (aux) {
                    for (DataOutputStream clientStream : clientesConectados) {
                        if (clientStream != null) {
                            try {

                                clientStream.writeUTF(mensaje);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
