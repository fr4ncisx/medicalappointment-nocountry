import { Button } from "@mui/material";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { useNavigate } from "react-router";

export const AgendarCitaButton = () => {
    const navigate = useNavigate();

    const handleAgendarCita = () => {
        navigate("/agendar-cita");
    }
    return (
        <Button onClick={handleAgendarCita} variant="contained" color="primary" sx={{ textTransform: "none", height: "30px" }} disableElevation startIcon={<CalendarMonthIcon />}>
            Agendar cita
        </Button>
    );
}