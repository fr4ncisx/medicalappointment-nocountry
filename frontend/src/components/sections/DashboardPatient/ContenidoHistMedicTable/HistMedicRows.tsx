import { Button, TableCell, TableRow } from "@mui/material";
import RemoveIcon from '@mui/icons-material/Remove';

const rows: Record<string, string>[] = [
    {
        id: "1",
        fecha: "23/04/2025",
        histMedic: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Officia aperiam ipsam esse voluptatibus reiciendis ratione beatae, facilis quis pariatur aliquid quos dolorem commodi rem nemo ipsum, quod, odio natus provident.",
    },
    {
        id: "2",
        fecha: "15/03/2025",
        histMedic: "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Officia aperiam ipsam esse voluptatibus reiciendis ratione beatae, facilis quis pariatur aliquid quos dolorem commodi rem nemo ipsum, quod, odio natus provident.",
    },
];

export const HistMedicRows = () => {
    // TODO obtener historial medico de un paciente del back
    return (
        <>
            {
                rows.map(({ id, fecha, histMedic }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{fecha}</TableCell>
                        <TableCell align="center">{histMedic}</TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}