import { Button, TableCell, TableRow } from "@mui/material";
import { MedicacionData } from "@tipos/backendTypes";

interface Props {
    rows: MedicacionData[]
}

export const MedicamentosRows = ({ rows }: Props) => {
    return (
        <>
            {
                rows.map(({ id, startDate, endDate, dosage, medicationName }) => (
                    <TableRow
                        key={id}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                        <TableCell align="center">{medicationName}</TableCell>
                        <TableCell align="center">{dosage}</TableCell>
                        <TableCell align="center">{`${startDate} ${endDate}`}</TableCell>
                        <TableCell align="center" sx={{ display: "flex", gap: "1rem" }}>
                            <Button variant="contained" color="info" disableElevation>
                                1
                            </Button>
                            <Button variant="contained" color="warning" disableElevation>
                                2
                            </Button>
                            <Button variant="contained" color="warning" disableElevation>
                                3
                            </Button>
                        </TableCell>
                    </TableRow>
                ))
            }
        </>
    );
}