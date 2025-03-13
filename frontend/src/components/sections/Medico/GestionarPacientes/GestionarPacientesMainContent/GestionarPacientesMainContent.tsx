import { Box, Grid2 } from "@mui/material";
import { GestionarPacientesTable } from "../ContenidoGestionarPacientesTable/GestionarPacientesTable";
import { SearchBar } from "@ui/SearchBar/SearchBar";
import { useState } from "react";
import { MedicamentosPacienteTable } from "../AdministrarMedicamentos/MedicamentosPacienteTable";

export const GestionarPacientesMainContent = () => {
    const [showAdministrarMedicamentos, setShowAdministrarMedicamentos] = useState(false);
    const [pacienteId, setPacienteId] = useState<number | null>(null);

    const handleShowAdminstrarMedicamentosPaciente = (showAdministrarMedicamentos: boolean, id?: number) => {
        setShowAdministrarMedicamentos(showAdministrarMedicamentos);
        setPacienteId(id || 0);
    }

    return (
        <Grid2 size="grow" sx={{
            backgroundColor: "#fff",
            padding: "2em"
        }}
        >
            {
                showAdministrarMedicamentos
                    ?
                    (
                        <MedicamentosPacienteTable
                            pacienteId={pacienteId}
                            handleShowAdminstrarMedicamentosPaciente={handleShowAdminstrarMedicamentosPaciente}
                        />
                    )
                    :
                    (
                        <>
                            <Box width={"300px"}>
                                <SearchBar label="Buscar pacientes..." />
                            </Box>
                            <GestionarPacientesTable
                                handleShowAdminstrarMedicamentosPaciente={handleShowAdminstrarMedicamentosPaciente}
                            />
                        </>
                    )
            }
        </Grid2>
    );
}