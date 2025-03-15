import { TableCell, TableRow } from "@mui/material";
import { CitasPasadasData } from "@tipos/backendTypes";

export const DiagnosticoRows = ({diagnostico}: {diagnostico: CitasPasadasData[]}) => {
    return (
        <>
            {
                diagnostico.map(({ id, recordDate, diagnosis }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{recordDate}</TableCell>
                        <TableCell align="center">{diagnosis}</TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}