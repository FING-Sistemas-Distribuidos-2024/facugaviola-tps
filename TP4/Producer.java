package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Producer {
    private static final int SLEEP = 500;

    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket sock = context.createSocket(SocketType.PUSH);
            sock.bind("tcp://*:3000");
            System.out.println("Producer iniciado!");

            int count = 0;

            while (true) {
                count++;
                System.out.println("Enviando mensaje desde cliente #" + count);
                sock.send("Message #" + count);
                Thread.sleep(SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}