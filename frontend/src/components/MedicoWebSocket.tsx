import { Container, Typography } from "@mui/material";
import { useUserStore } from "@store/user.store";
import { useEffect, useState, useRef } from "react";
import { Client } from "@stomp/stompjs";

export const MedicoWebSocket = () => {
    const [isConnected, setIsConnected] = useState(false);
    const id = useUserStore((state) => state.getUserId)();

    const brokerURL = "ws://localhost:8080/ws";
    const userChannel = `/user/${id}/notifications`;

    // Guardamos la referencia del cliente para evitar recrearlo en cada render
    const clientRef = useRef<Client | null>(null);

    useEffect(() => {
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
        clientRef.current = client;

        return () => {
            client.deactivate();
            console.log("WebSocket desconectado");
        };
    }, [id]);

    return (
        <Container>
            <Typography variant="body2" color={isConnected ? "success" : "error"}>
                {isConnected ? "Conectado" : "Desconectado, intentando reconectar..."}
            </Typography>
        </Container>
    );
};
