import { TableCell, TableRow } from "@mui/material";

const rows: Record<string, string>[] = [
    {
        id: "1",
        fecha: "23/04/2025",
        hora: "11:00HS",
        doctor: "Gonzalez, Julio",
        estado: "confirmada"
    },
    {
        id: "2",
        fecha: "15/03/2025",
        hora: "10:30HS",
        doctor: "Castillos, Martín",
        estado: "confirmada"
    },
    {
        id: "3",
        fecha: "28/05/2025",
        hora: "08:00HS",
        doctor: "Sevallo, Paula",
        estado: "cancelada"
    },
    {
        id: "4",
        fecha: "12/06/2025",
        hora: "13:00HS",
        doctor: "Lima, Alexia",
        estado: "confirmada"
    }
];

export const HistorialCitasRows = () => {
    return (
        <>
            {
                rows.map(({ id, fecha, hora, doctor, estado }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{fecha}</TableCell>
                        <TableCell align="center">{hora}</TableCell>
                        <TableCell align="center">{doctor}</TableCell>
                        <TableCell align="center">{estado}</TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}