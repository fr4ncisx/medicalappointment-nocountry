import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { HistMedicRows } from "./HistMedicRows";
import { HistMedicHeaders } from "./HistMedicHeaders";
import { useEffect, useState } from "react";
import { useUserStore } from "@store/user.store";
import { CustomError } from "@tipos/types";
import { StatusTable } from "@ui/StatusTable/StatusTable";
import { CitasPasadasData } from "@tipos/backendTypes";

export const HistMedicTable = () => {
    const [citasPasadas, setCitasPasadas] = useState<CitasPasadasData[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<CustomError | null>(null);
    const getToken = useUserStore(state => state.getToken);

    useEffect(() => {
        setLoading(true);
        const token = getToken();
        fetch(`${import.meta.env.VITE_BACKEND_URL}/api/v1/records/1`, {method: "GET", headers: {'Authorization': `${token}`}})
        .then(async (response) => {
            const responseBody = await response.json();
            if (!response.ok) {
                throw new Error(responseBody.error);
            } else{
                setCitasPasadas(responseBody)
            }
            return responseBody;
        })
        .catch((error) => {
            let errorMsg = error.message;
    
            if(error.message === "Failed to fetch"){
                errorMsg = "Falló la conexión";
            }
    
            setError({
                description: errorMsg,
                type: "fetch",
                status: error.status
            })
        })
        .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return <StatusTable color="info" label="Cargando..." />;
    }
    if (!citasPasadas) {
        return <StatusTable color="info" label="No tienes un historial médico" />;
    }
    
    return !error && !loading
        ? (
            <CustomTable tableWithTabs>
                <CustomTableHeader>
                    <HistMedicHeaders />
                </CustomTableHeader>
                <CustomTableBody>
                    <HistMedicRows citasPasadas={citasPasadas} />
                </CustomTableBody>
            </CustomTable>
        )
        : (
            <StatusTable color="error" label={error?.description || ""} />
        )
}