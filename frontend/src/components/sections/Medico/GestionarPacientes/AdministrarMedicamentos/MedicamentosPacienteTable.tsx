import { Box, Button } from "@mui/material";
import { MedicamentosTable } from "./MedicamentosTable.tsx/MedicamentosTable";

interface Props {
    pacienteId: number | null
    handleShowAdminstrarMedicamentosPaciente: (showAdministrarMedicamentos: boolean, id?: number) => void
}

export const MedicamentosPacienteTable = ({ pacienteId, handleShowAdminstrarMedicamentosPaciente }: Props) => {
    const handleAgregarMedicamento = () => {
        // TODO open modal con form de agregar medicamento al paciente
    }

    return (
        <Box>
            <Box sx={{ display: "flex", justifyContent: "space-between"}}>
                <Button sx={{ marginBottom: "1rem" }} variant="contained" onClick={() => handleShowAdminstrarMedicamentosPaciente(false)} disableElevation>Volver hacia atrás</Button>
                <Button sx={{ marginBottom: "1rem" }} variant="contained" color="secondary" onClick={handleAgregarMedicamento} disableElevation>Agregar Medicamentos</Button>
            </Box>
            <MedicamentosTable pacienteId={pacienteId} />
        </Box>
    );
}