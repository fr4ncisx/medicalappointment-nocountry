import { Client } from "@stomp/stompjs";
import { Dispatch, SetStateAction } from "react";

export const socketConnection = (id: string, setIsConnected: Dispatch<SetStateAction<boolean>>) => {
    const brokerURL = "ws://localhost:8080/ws";
    const userChannel = `/user/${id}/notifications`;
    
    if (!id) return; // Evitamos ejecutar si el ID no está disponible

    const client = new Client({
        brokerURL,
        reconnectDelay: 5000, // Intenta reconectar en 5 segundos
        onConnect: () => {
            setIsConnected(true);
            console.log("Conectado al WebSocket");

            client.subscribe(userChannel, (message) => {
                const quote = JSON.parse(message.body);
                console.log(`Mensaje recibido: ${quote}`);
            });
        },
        onStompError: (frame) => {
            console.error("Error en STOMP:", frame.headers["message"]);
            console.error("Detalles:", frame.body);
        },
        onWebSocketClose: () => {
            setIsConnected(false);
            console.warn("Conexión WebSocket cerrada, intentando reconectar...");
        },
    });

    client.activate();

    return () => {
        client.deactivate();
        console.log("WebSocket desconectado");
    };
}