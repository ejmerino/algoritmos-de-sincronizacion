package com.espe.berkeley;

import java.text.SimpleDateFormat;
import java.util.*;

public class BerkeleyServer {
    private static final int NUM_NODES = 5;  // Número de nodos en el sistema
    private List<ClockNode> nodes;  // Lista de nodos
    private long averageTime;  // Tiempo promedio calculado

    public BerkeleyServer() {
        nodes = new ArrayList<>();
        // Crear y añadir nodos al servidor
        for (int i = 1; i <= NUM_NODES; i++) {
            nodes.add(new ClockNode(i, this));
        }
    }

    // Método para recibir los tiempos de los nodos y calcular el promedio
    public void receiveTimesAndCalculateAverage() {
        long total = 0;
        for (ClockNode node : nodes) {
            total += node.getLocalTime();
            System.out.println("Servidor recibe tiempo del nodo " + node.getNodeId() + ": " + formatTime(node.getLocalTime()));
        }

        averageTime = total / nodes.size();
        System.out.println("Servidor calcula el tiempo promedio: " + formatTime(averageTime));
    }

    // Método para enviar el tiempo promedio a los nodos
    public void adjustNodeTimes() {
        for (ClockNode node : nodes) {
            node.adjustTime(averageTime);
        }
    }

    // Formato para mostrar el tiempo legible
    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }

    // Método principal para ejecutar la simulación
    public static void main(String[] args) {
        BerkeleyServer server = new BerkeleyServer();

        // Recibir tiempos de los nodos y calcular el promedio
        server.receiveTimesAndCalculateAverage();

        // Ajustar los tiempos de los nodos según el tiempo promedio
        server.adjustNodeTimes();
    }
}
