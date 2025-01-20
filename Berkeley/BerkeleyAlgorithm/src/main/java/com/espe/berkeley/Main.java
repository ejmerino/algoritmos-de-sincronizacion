package com.espe.berkeley;

public class Main {
    public static void main(String[] args) {
        BerkeleyServer server = new BerkeleyServer();

        // Recibir tiempos de los nodos y calcular el promedio
        server.receiveTimesAndCalculateAverage();

        // Ajustar los tiempos de los nodos según el tiempo promedio
        server.adjustNodeTimes();
    }
}
