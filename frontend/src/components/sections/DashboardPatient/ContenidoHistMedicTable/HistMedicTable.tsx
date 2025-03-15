import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { HistMedicRows } from "./HistMedicRows";
import { HistMedicHeaders } from "./HistMedicHeaders";

export const HistMedicTable = () => {
    return (
        <CustomTable>
            <CustomTableHeader>
                <HistMedicHeaders />
            </CustomTableHeader>
            <CustomTableBody>
                <HistMedicRows />
            </CustomTableBody>
        </CustomTable>
    );
}