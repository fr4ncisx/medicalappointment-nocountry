import { TableCell } from "@mui/material";

const headers = [
    {
        id: "1",
        title: "Fecha",
    },
    {
        id: "2",
        title: "Hora",
    },
    {
        id: "3",
        title: "Doctor",
    },
    {
        id: "4",
        title: "",
    }
];

export const CitasHeaders = () => {
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