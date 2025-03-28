import { Container, Typography } from "@mui/material";
import { useUserStore } from "@store/user.store";
import { useEffect, useState } from "react";
import { Client } from '@stomp/stompjs';

export const MedicoWebSocket = () => {
    const [isConnected, setIsConnected] = useState(false);
    const id = useUserStore(state => state.getUserId)();

    const brokerURL = "ws://localhost:8080/ws";
    const userChannel = `/user/${id}/notifications`;
    useEffect(() => {
        const client = new Client({
            brokerURL,
            onConnect: () => {
                client.subscribe(userChannel, message => {
                    const quote = JSON.parse(message.body);
                    setIsConnected(true);
                    console.log(`Mensaje Recibido: ${quote}`)
                });
                client.onStompError = function (frame) {
                    console.log('Ocurrio un error: ' + frame.headers['message']);
                    console.log('Detalles del error: ' + frame.body);
                };
            },
        });
        client.activate();
        return () => {
            client.deactivate();
        }
    }, []);

    return (
        <Container>
            <Typography variant="body2" color="warning">
                {`Estado de reconexion: ${isConnected ? "Reconectando..." : "Conectado"}`}
            </Typography>
        </Container>
    );
}