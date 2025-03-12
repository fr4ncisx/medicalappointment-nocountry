import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { DiagnosticoRows } from "./DiagnosticoRows";
import { DiagnosticoHeaders } from "./DiagnosticoHeaders";

export const DiagnosticoTable = () => {
    return (
        <CustomTable>
            <CustomTableHeader>
                <DiagnosticoHeaders />
            </CustomTableHeader>
            <CustomTableBody>
                <DiagnosticoRows />
            </CustomTableBody>
        </CustomTable>
    );
}