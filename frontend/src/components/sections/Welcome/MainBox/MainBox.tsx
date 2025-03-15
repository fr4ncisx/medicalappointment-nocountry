import { Box, Typography } from "@mui/material";
import { AgendarCitaButton } from "./AgendarCitaButton";
import { MainBoxStyles } from "./MainBoxStyles";

export const MainBox = () => {
    return (
        <Box sx={MainBoxStyles.mainBox}>
            <Box sx={MainBoxStyles.welcomeBox}>
                <Box sx={MainBoxStyles.circle1} />
                <Box sx={MainBoxStyles.circle2} />
                <Box>
                    <Typography sx={MainBoxStyles.title}>
                        MedicalAppointment
                    </Typography>
                    <Typography sx={MainBoxStyles.subtitle}>
                        Tu lugar para pedir un turno
                    </Typography>
                </Box>
                <AgendarCitaButton />
            </Box>
        </Box>
    );
}