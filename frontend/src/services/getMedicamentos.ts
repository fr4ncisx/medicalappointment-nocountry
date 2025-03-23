import { MedicacionData } from "@tipos/backendTypes";
import { Parameters } from "@tipos/types";
import { handleError } from "@utils/handleError";

export const getMedicamentos = ({ token, setDataRows, setLoading, setError, idForEndpoint: pacienteId }: Parameters) => {
    setLoading(true);
    const MEDICAMENTOS_URL = `${import.meta.env.VITE_BACKEND_URL}/api/v1/medication/${pacienteId}`;
    fetch(MEDICAMENTOS_URL, { method: "GET", headers: { 'Authorization': `${token}` } })
        .then(async (response) => {
            const responseBody = await response.json();
            if (!response.ok) {
                throw new Error(`${response.status}: ${responseBody.error || responseBody.ERROR}`);
            }
            return responseBody;
        })
        .then((result: MedicacionData[]) => {
            setDataRows(result);
        })
        .catch((e) => {
            const error = handleError(e);
            setDataRows([]);
            setError(error);
        })
        .finally(() => setLoading(false));
}