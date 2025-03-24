import { Button } from "@mui/material";
import DownloadIcon from '@mui/icons-material/Download';

export const DescargarHistMedicButton = () => {
    const handleDescHistMedic = () => {
        // TODO agregar logica para agregar un medico
    }
    return (
        <Button onClick={handleDescHistMedic} variant="contained" color="primary" sx={{ textTransform: "none", height: "30px" }} disableElevation startIcon={<DownloadIcon />}>
            Descargar historial médico
        </Button>
    );
}