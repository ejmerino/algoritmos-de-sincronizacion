package com.espe.server;

import java.io.*;
import java.net.*;
import java.util.Date;

 class TimeServer {
    public static void main(String[] args) {
        try {
            // Puerto en el que el servidor escuchará
            int port = 12345;
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Servidor esperando conexiones...");

            // Bucle para recibir solicitudes de los clientes
            while (true) {
                byte[] receiveData = new byte[1024];

                // Recibir la solicitud del cliente
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Obtener la hora actual
                long serverTime = System.currentTimeMillis();
                String serverTimeString = Long.toString(serverTime);

                // Enviar la respuesta con la hora del servidor
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(serverTimeString.getBytes(), serverTimeString.length(), clientAddress, clientPort);
                socket.send(sendPacket);
                System.out.println("Hora enviada al cliente: " + serverTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
