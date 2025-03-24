import { TableCell, TableRow } from "@mui/material";
import { CitasData } from "@tipos/backendTypes";

export const HistorialCitasRows = ({historialCitas}: {historialCitas: CitasData[]}) => {
    return (
        <>
            {
                historialCitas.map(({ id, date, time, status, medic }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{date}</TableCell>
                        <TableCell align="center">{time}</TableCell>
                        <TableCell align="center">{medic.name} {medic.lastname}</TableCell>
                        <TableCell align="center">{status}</TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}