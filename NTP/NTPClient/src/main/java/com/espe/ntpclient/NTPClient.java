package com.espe.ntpclient;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import java.net.InetAddress;
import java.util.Date;

public class NTPClient {
    public static void main(String[] args) {
        try {
            // Dirección del servidor NTP
            String ntpServer = "time.google.com";

            // Crear el cliente NTP
            NTPUDPClient client = new NTPUDPClient();
            client.setDefaultTimeout(10000); // Establecer tiempo de espera de 10 segundos

            // Obtener la dirección IP del servidor
            InetAddress inetAddress = InetAddress.getByName(ntpServer);

            // Consultar el servidor de tiempo
            TimeInfo timeInfo = client.getTime(inetAddress);

            // Obtener la hora del servidor
            long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            Date currentDate = new Date(returnTime);

            // Mostrar la hora sincronizada
            System.out.println("Hora sincronizada con el servidor NTP: " + currentDate);

            // Simular la sincronización de la hora local con la hora del servidor NTP
            System.out.println("Usando hora sincronizada: " + currentDate.toString());

            // Puedes utilizar currentDate en lugar de System.currentTimeMillis() para simular el uso de la hora sincronizada

        } catch (Exception e) {
            System.err.println("Error al sincronizar con el servidor NTP: " + e.getMessage());
        }
    }
}
