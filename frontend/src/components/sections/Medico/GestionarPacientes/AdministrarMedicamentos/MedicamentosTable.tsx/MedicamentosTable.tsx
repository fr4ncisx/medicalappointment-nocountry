import CustomTable from "@ui/CustomTable/CustomTable";
import { CustomTableBody } from "@ui/CustomTable/CustomTableBody";
import { MedicamentosHeaders } from "./MedicamentosHeaders";
import { MedicamentosRows } from "./MedicamentosRows";
import { CustomTableHeader } from "@ui/CustomTable/CustomTableHeader";
import { StatusTable } from "@ui/StatusTable/StatusTable";
import { useEffect, useState } from "react";
import { useUserStore } from "@store/user.store";
import { CustomError } from "@tipos/types";
import { MedicacionData } from "@tipos/backendTypes";

interface Props {
    pacienteId: number | null
    refetch: boolean
}

export const MedicamentosTable = ({ pacienteId, refetch }: Props) => {
    const [medicamentos, setMedicamentos] = useState<MedicacionData[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<CustomError | null>(null);
    const getToken = useUserStore(state => state.getToken);

    useEffect(() => {
        setLoading(true);
        const token = getToken();
        fetch(`${import.meta.env.VITE_BACKEND_URL}/api/v1/medication/${pacienteId}`, { method: "GET", headers: { 'Authorization': `${token}` } })
            .then(async (response) => {
                const responseBody = await response.json();
                if (!response.ok) {
                    throw new Error(responseBody.error || responseBody.ERROR);
                }
                return responseBody;
            })
            .then((result: MedicacionData[]) => {
                setMedicamentos(result);
            })
            .catch((error) => {
                let errorMsg = error.message;

                if (error.message === "Failed to fetch") {
                    errorMsg = "Falló la conexión";
                }

                setError({
                    description: errorMsg,
                    type: "fetch",
                    status: error.status
                })
            })
            .finally(() => setLoading(false));
    }, [refetch]);

    if (loading) {
        return <StatusTable color="info" label="Cargando..." />;
    }
    return !error && !loading
        ? (
            <CustomTable>
                <CustomTableHeader>
                    <MedicamentosHeaders />
                </CustomTableHeader>
                <CustomTableBody>
                    <MedicamentosRows rows={medicamentos} />
                </CustomTableBody>
            </CustomTable>
        )
        : (
            <StatusTable color="error" label={error?.description || ""} />
        );
}