package com.espe.berkeley;

import java.text.SimpleDateFormat;
import java.util.*;

public class ClockNode {
    private int nodeId;
    private long localTime;  // Tiempo local del nodo
    private BerkeleyServer server;  // Referencia al servidor

    public ClockNode(int nodeId, BerkeleyServer server) {
        this.nodeId = nodeId;
        this.server = server;

        // Simular un desfase aleatorio de -500 a 500 ms
        Random random = new Random();
        this.localTime = System.currentTimeMillis() + random.nextInt(1000) - 500;
    }

    // Obtener el tiempo local del nodo
    public long getLocalTime() {
        return localTime;
    }

    // Obtener el ID del nodo
    public int getNodeId() {
        return nodeId;
    }

    // Ajustar el reloj del nodo según el tiempo promedio
    public void adjustTime(long averageTime) {
        localTime = averageTime;
        System.out.println("Nodo " + nodeId + " ajusta su tiempo a: " + formatTime(localTime));
    }

    // Formato para mostrar el tiempo legible
    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }
}
