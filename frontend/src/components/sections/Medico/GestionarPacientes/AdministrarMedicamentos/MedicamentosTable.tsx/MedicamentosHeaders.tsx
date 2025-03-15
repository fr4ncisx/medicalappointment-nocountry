import { TableCell } from "@mui/material";

const headers = [
    {
        id: "1",
        title: "Medicación",
    },
    {
        id: "2",
        title: "Dosis",
    },
    {
        id: "3",
        title: "Tomar entre los dias"
    },
    {
        id: "4",
        title: "",
    }
];

export const MedicamentosHeaders = () => {
    return (
        <>
            {
                headers.map((item) => (
                    <TableCell key={item.id} align="center" width={item.title === "" ? "350px" : ""}>{item.title}</TableCell>
                ))
            }
        </>
    );
}