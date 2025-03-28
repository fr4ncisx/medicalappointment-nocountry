import { Container, Typography } from "@mui/material";
import { useUserStore } from "@store/user.store";
import { useEffect, useState } from "react";
import { socketConnection } from "./socketConnection";

export const MedicoWebSocket = () => {
    const [isConnected, setIsConnected] = useState(false);
    const id = useUserStore((state) => state.getUserId)();

    useEffect(() => {
        socketConnection(id, setIsConnected);
    }, []);

    return (
        <Container>
            <Typography variant="body2" color={isConnected ? "success" : "error"}>
                {isConnected ? "Conectado" : "Desconectado, intentando reconectar..."}
            </Typography>
        </Container>
    );
};
