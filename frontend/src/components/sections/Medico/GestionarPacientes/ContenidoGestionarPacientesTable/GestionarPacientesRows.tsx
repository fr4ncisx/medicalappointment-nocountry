import { Button, TableCell, TableRow } from "@mui/material";

const rows: Record<string, string>[] = [
    {
        id: "1",
        nombreCompleto: "Lucas Araya",
        dni: "12345687"
    },
    {
        id: "2",
        nombreCompleto: "Cesar Fonseca",
        dni: "98761233"
    },
    {
        id: "3",
        nombreCompleto: "Mariana Bustos",
        dni: "34509776"
    },
    {
        id: "4",
        nombreCompleto: "Luu Freere",
        dni: "42397894"
    }
];

export const GestionarPacientesRows = () => {
     // TODO obtener pacientes del back
     return(
        <>
            {
                rows.map(({ id, nombreCompleto, dni }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{nombreCompleto}</TableCell>
                        <TableCell align="center">{dni}</TableCell>
                        <TableCell align="center" sx={{display: "flex", gap: "1rem"}}>
                            <Button variant="contained" color="info" disableElevation>
                                Historial Medico
                            </Button>
                            <Button variant="contained" color="warning" disableElevation>
                                Administrar Medicamentos
                            </Button>
                        </TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}