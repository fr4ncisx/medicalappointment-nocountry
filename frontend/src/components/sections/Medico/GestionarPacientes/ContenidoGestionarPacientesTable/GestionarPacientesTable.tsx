import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { GestionarPacientesRows } from "../ContenidoGestionarPacientesTable/GestionarPacientesRows";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { GestionarPacientesHeaders } from "../ContenidoGestionarPacientesTable/GestionarPacientesHeaders";

export const GestionarPacientesTable = () => {
    return (
        <CustomTable>
            <CustomTableHeader>
                <GestionarPacientesHeaders />
            </CustomTableHeader>
            <CustomTableBody>
                <GestionarPacientesRows />
            </CustomTableBody>
        </CustomTable>
    );
}