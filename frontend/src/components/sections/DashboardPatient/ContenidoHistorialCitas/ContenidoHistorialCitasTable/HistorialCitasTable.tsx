import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { HistorialCitasRows } from "./HistorialCitasRows";
import { HistorialCitasHeaders } from "./HistorialCitasHeaders";

export const HistorialCitasTable = () => {
    return (
        <CustomTable>
            <CustomTableHeader>
                <HistorialCitasHeaders />
            </CustomTableHeader>
            <CustomTableBody>
                <HistorialCitasRows />
            </CustomTableBody>
        </CustomTable>
    );
}