import { TableCell } from "@mui/material";

const headers = [
    {
        id: "1",
        title: "Fecha",
    },
    {
        id: "2",
        title: "Datos del historial médico",
    },
];

export const HistMedicHeaders = () => {
    return (
        <>
            {
                headers.map((item) => (
                    <TableCell key={item.id} align="center">{item.title}</TableCell>
                ))
            }
        </>
    );
}