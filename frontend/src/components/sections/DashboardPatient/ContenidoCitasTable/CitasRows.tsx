import { Button, TableCell, TableRow } from "@mui/material";
import RemoveIcon from '@mui/icons-material/Remove';
import ScheduleIcon from '@mui/icons-material/Schedule';

const rows: Record<string, string>[] = [
    {
        id: "1",
        fecha: "23/04/2025",
        hora: "11:00HS",
        doctor: "Gonzalez, Julio",
    },
    {
        id: "2",
        fecha: "15/03/2025",
        hora: "10:30HS",
        doctor: "Castillos, Martín",
    },
    {
        id: "3",
        fecha: "28/05/2025",
        hora: "08:00HS",
        doctor: "Sevallo, Paula"
    },
    {
        id: "4",
        fecha: "12/06/2025",
        hora: "13:00HS",
        doctor: "Lima, Alexia",
    }
];

export const CitasRows = () => {
    // TODO obtener citas de un paciente del back
    const handleCancelar = (id: string) => {
        // TODO eliminar paciente
    }
    const handleReprogramar = (id: string) => {
        // TODO eliminar paciente
    }
    return (
        <>
            {
                rows.map(({ id, fecha, hora, doctor }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{fecha}</TableCell>
                        <TableCell align="center">{hora}</TableCell>
                        <TableCell align="center">{doctor}</TableCell>
                        <TableCell align="center">
                            <Button startIcon={<RemoveIcon />} sx={{fontSize: "0.8em", textTransform: "none", marginRight: "8px"}} variant="contained" color="error" disableElevation onClick={() => handleCancelar(id)}>Cancelar</Button>
                            <Button startIcon={<ScheduleIcon />} sx={{fontSize: "0.8em", textTransform: "none", backgroundColor: "#FEB20E"}} variant="contained" disableElevation onClick={() => handleReprogramar(id)}>Reprogramar</Button>
                        </TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}