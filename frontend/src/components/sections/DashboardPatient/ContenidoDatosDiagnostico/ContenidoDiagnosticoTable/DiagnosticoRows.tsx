import { TableCell, TableRow } from "@mui/material";

const rows: Record<string, string>[] = [
    {
        id: "1",
        fecha: "23/04/2025",
        doctor: "Gonzalez, Julio",
        diagnostico: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Fugiat nihil voluptates odit nisi, exercitationem eum placeat eaque magni ipsam nulla inventore in, velit ducimus odio autem ad voluptas dolorem repellat!",
    },
    {
        id: "2",
        fecha: "15/03/2025",
        doctor: "Castillos, Martín",
        diagnostico: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Fugiat nihil voluptates odit nisi, exercitationem eum placeat eaque magni ipsam nulla inventore in, velit ducimus odio autem ad voluptas dolorem repellat!",
    },
    {
        id: "3",
        fecha: "28/05/2025",
        doctor: "Sevallo, Paula",
        diagnostico: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Fugiat nihil voluptates odit nisi, exercitationem eum placeat eaque magni ipsam nulla inventore in, velit ducimus odio autem ad voluptas dolorem repellat!",
    },
    {
        id: "4",
        fecha: "12/06/2025",
        doctor: "Lima, Alexia",
        diagnostico: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Fugiat nihil voluptates odit nisi, exercitationem eum placeat eaque magni ipsam nulla inventore in, velit ducimus odio autem ad voluptas dolorem repellat!",
    }
];

export const DiagnosticoRows = () => {
    return (
        <>
            {
                rows.map(({ id, fecha, doctor, diagnostico }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{fecha}</TableCell>
                        <TableCell align="center">{doctor}</TableCell>
                        <TableCell align="center">{diagnostico}</TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}