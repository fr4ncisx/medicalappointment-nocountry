import { SectionWrapper } from "@components/layout/SectionWrapper";
import { TableContextProvider } from "@context/table.provider";
import { getMedicamentos } from "@services/getMedicamentos";
import { MEDIC_LINKS } from "../../MEDIC_LINKS";
import { GestionarMedicamentosContent } from "./GestionarMedicamentosContent";
import { useParams } from "react-router";
import { useModalStore } from "@store/modal.store";
import CustomModal from "@ui/CustomModal/CustomModal";
import { MedicamentosForm } from "./Form/MedicamentosForm";
import { Toaster } from "sonner";

export const GestionarMedicamentos = () => {
    const id: string = useParams().id || "";
    const setModalData = useModalStore(state => state.setModalData);
    const showModal = useModalStore((state) => state.modalData.showModal);

    const handleAddMedicamento = () => {
        setModalData({
            showModal: true,
            title: "Agregar Medicamento",
            operation: "add_medication",
            data: { id }
        });
    }
    return (
        <>
            <SectionWrapper sideBarItems={MEDIC_LINKS}>
                <TableContextProvider handleAdd={handleAddMedicamento} fetchRows={getMedicamentos} idForEndpoint={id}>
                    <GestionarMedicamentosContent />
                </TableContextProvider>
            </SectionWrapper>
            {
                showModal && (
                    <CustomModal>
                        <MedicamentosForm />                            
                    </CustomModal>
                )
            }
            <Toaster />
        </>
    );
}