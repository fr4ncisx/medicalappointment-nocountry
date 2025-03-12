import { TableCell } from "@mui/material";

const headers = [
    {
        id: "1",
        title: "Fecha",
    },
    {
        id: "2",
        title: "Doctor",
    },
    {
        id: "4",
        title: "Diagnóstico",
    }
];

export const DiagnosticoHeaders = () => {
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