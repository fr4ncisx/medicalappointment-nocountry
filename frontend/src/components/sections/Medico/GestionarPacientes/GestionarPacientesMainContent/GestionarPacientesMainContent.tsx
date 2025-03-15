import { Box, Grid2 } from "@mui/material";
import { GestionarPacientesTable } from "../ContenidoGestionarPacientesTable/GestionarPacientesTable";
import { SearchBar } from "@ui/SearchBar/SearchBar";

export const GestionarPacientesMainContent = () => {
    return (
        <Grid2 size="grow" sx={{
            backgroundColor: "#fff",
            padding: "2em"
        }}
        >
            <Box width={"300px"}>
                <SearchBar label="Buscar pacientes..." />
            </Box>
            <GestionarPacientesTable />
        </Grid2>
    );
}