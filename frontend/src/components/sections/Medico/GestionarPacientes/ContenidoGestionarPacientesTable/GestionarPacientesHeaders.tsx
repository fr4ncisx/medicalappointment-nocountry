import { TableCell } from "@mui/material";

const headers = [
    {
        id: "1",
        title: "Nombre Completo",
    },
    {
        id: "3",
        title: "DNI",
    },
    {
        id: "4",
        title: "",
        width: "430px"
    }
];

export const GestionarPacientesHeaders = () => {
    return (
        <>
            {
                headers.map((item) => (
                    <TableCell key={item.id} align="center" width={item.title === "" ? item.width : ""}>{item.title}</TableCell>
                ))
            }
        </>
    );
}