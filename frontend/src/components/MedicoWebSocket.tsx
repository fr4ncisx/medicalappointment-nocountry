import { Box, Container, List, ListItem, Typography } from "@mui/material";
import { useUserStore } from "@store/user.store";
import { useEffect, useState } from "react";
import { io } from "socket.io-client";

interface Notification {
    id: string
    text: string
    isRead: boolean
    user: any
}

export const MedicoWebSocket = () => {
    const id = useUserStore(state => state.getUserId)();
    const [notifications, setNotifications] = useState<Notification[]>([]);
    const [conection, setConection] = useState(false);

    const socket = io("ws://localhost:8080/ws");
    const userChannel = `/user/${id}/notifications`;

    useEffect(() => {
        socket.on("connect", () => {
            setConection(socket.connected);
            console.log(`Estado de conexion con websocket ${socket.connected ? "Establecida" : "No establecida"}`);
        });

        socket.on(userChannel, (data: Notification) => {
            setNotifications([...notifications, data]);
            console.log(data);
        });

        return () => {
            socket.disconnect();
        }
    }, []);
    return (
        <Container>
            <Box>
                <Box>
                    <Typography variant="body2">
                        {`Estado de conexion con websocket: `}
                    </Typography>
                    <Typography variant="body2" color={ conection ? "info" : "success"}>
                        {`${conection ? "Establecida" : "No establecida"}`}
                    </Typography>
                </Box>

                <Typography variant="body2" color="warning">
                    {`Estado de reconexion: ${socket.active ? "Reconectando..." : ""}`}
                </Typography>
            </Box>
            <List>
                {
                    notifications.length === 0
                        ? notifications.map(({ id, isRead, text, user }) => (
                            <ListItem>
                                <Typography variant="body1" color="info">
                                    {id}
                                </Typography>
                                <Typography variant="body1" color="info">
                                    {isRead}
                                </Typography>
                                <Typography variant="body1" color="info">
                                    {text}
                                </Typography>
                                <Typography variant="body1" color="info">
                                    {JSON.stringify(user)}
                                </Typography>
                            </ListItem>
                        ))
                        : (
                            <ListItem>
                                SIN NOTIFICACIONES
                            </ListItem>
                        )
                }
            </List>
        </Container>
    );
}