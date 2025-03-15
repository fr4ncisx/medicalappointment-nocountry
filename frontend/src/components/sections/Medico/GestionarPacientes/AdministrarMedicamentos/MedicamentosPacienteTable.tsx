import { Box, Button } from "@mui/material";
import { MedicamentosTable } from "./MedicamentosTable.tsx/MedicamentosTable";
import { useModalStore } from "@store/modal.store";
import CustomModal from "@ui/CustomModal/CustomModal";
import { MedicamentosForm } from "./MedicamentosForm/MedicamentosForm";
import { useState } from "react";

interface Props {
    pacienteId: number | null
    handleShowAdminstrarMedicamentosPaciente: (showAdministrarMedicamentos: boolean, id?: number) => void
}

export const MedicamentosPacienteTable = ({ pacienteId, handleShowAdminstrarMedicamentosPaciente }: Props) => {
    const showModal = useModalStore((state) => state.modalData.showModal);
    const setModalData = useModalStore((state) => state.setModalData);
    const [refetch, setRefetch] = useState(false);

    const handleAgregarMedicamento = () => {
        setModalData({
            showModal: true,
            title: "Agregar Medicamentos",
        });
    }

    return (
        <Box>
            <Box sx={{ display: "flex", justifyContent: "space-between" }}>
                <Button sx={{ marginBottom: "1rem" }} variant="contained" onClick={() => handleShowAdminstrarMedicamentosPaciente(false)} disableElevation>Volver hacia atrás</Button>
                <Button sx={{ marginBottom: "1rem" }} variant="contained" color="secondary" onClick={handleAgregarMedicamento} disableElevation>Agregar Medicamentos</Button>
            </Box>
            <MedicamentosTable pacienteId={pacienteId} refetch={refetch} />
            {
                showModal && (
                    <CustomModal>
                        <MedicamentosForm pacienteId={pacienteId} setRefetch={setRefetch}/>
                    </CustomModal>
                )
            }
        </Box>
    );
}