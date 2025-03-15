import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { CitasRows } from "./CitasRows";
import { CitasHeaders } from "./CitasHeaders";

export const CitasTable = () => {
    return (
        <CustomTable>
            <CustomTableHeader>
                <CitasHeaders />
            </CustomTableHeader>
            <CustomTableBody>
                <CitasRows />
            </CustomTableBody>
        </CustomTable>
    );
}