import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { HistorialCitasRows } from "./HistorialCitasRows";
import { HistorialCitasHeaders } from "./HistorialCitasHeaders";
import { useEffect, useState } from "react";
import { useUserStore } from "@store/user.store";
import { CustomError } from "@tipos/types";
import { StatusTable } from "@ui/StatusTable/StatusTable";
import { CitasData } from "@tipos/backendTypes";

export const HistorialCitasTable = () => {
    const [historialCitas, setHistorialCitas] = useState<CitasData[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<CustomError | null>(null);
    const getToken = useUserStore(state => state.getToken);
        
    useEffect(() => {
        setLoading(true);
        const token = getToken();
        fetch(`${import.meta.env.VITE_BACKEND_URL}/api/v1/appointments/patient/1`, {method: "GET", headers: {'Authorization': `${token}`,}})
        .then(async (response) => {
            const responseBody = await response.json();
            if (!response.ok) {
                throw new Error(responseBody.error);
            } else{
                setHistorialCitas(responseBody.appointments)
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
    if (!historialCitas) {
            return <StatusTable color="info" label="No tienes citas para mostrar" />;
        }

    return !error && !loading
                ? (
                    <CustomTable>
                        <CustomTableHeader>
                            <HistorialCitasHeaders />
                        </CustomTableHeader>
                        <CustomTableBody>
                            <HistorialCitasRows historialCitas={historialCitas} />
                        </CustomTableBody>
                    </CustomTable>
                )
                : (
                    <StatusTable color="error" label={error?.description || ""} />
                )
}