package com.espe.client;

import java.net.*;
import java.util.Date;

class TimeClient {
    public static void main(String[] args) {
        try {
            // Dirección y puerto del servidor
            String serverAddress = "localhost";
            int serverPort = 12345;

            // Crear el socket
            DatagramSocket socket = new DatagramSocket();

            // Establecer el tiempo de solicitud (T1)
            long T1 = System.currentTimeMillis();
            byte[] sendData = new byte[1024];
            String requestMessage = "Hora solicitada";
            sendData = requestMessage.getBytes();

            // Enviar la solicitud al servidor
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Solicitud enviada al servidor...");

            // Recibir la respuesta del servidor
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            long T3 = System.currentTimeMillis(); // Hora cuando se recibe la respuesta

            // Obtener la hora del servidor (T2)
            String serverTimeString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            long T2 = Long.parseLong(serverTimeString);

            // Calcular la hora estimada del servidor (T_s)
            long T_s = T2 + ((T3 - T1) / 2);

            // Mostrar los resultados
            System.out.println("Hora solicitada por el cliente (T1): " + new Date(T1));
            System.out.println("Hora recibida del servidor (T2): " + new Date(T2));
            System.out.println("Hora recibida por el cliente (T3): " + new Date(T3));
            System.out.println("Hora estimada del servidor (T_s): " + new Date(T_s));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
