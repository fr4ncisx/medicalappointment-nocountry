import { AgendarCitaInput } from "@tipos/backendTypes";
import { AppointmentResponse } from "@tipos/backendTypes";
import { CustomError } from "@tipos/types";
import { Dispatch, SetStateAction } from "react";
import { handleError } from "@utils/handleError";

interface Params {
    data: AgendarCitaInput | undefined,
    date: string,
    setError: Dispatch<SetStateAction<CustomError>>,
    getToken: any,
    patientId: string | undefined,
}


export const addAppointment = async ({data, date, setError, getToken, patientId}: Params) => {
    const token = getToken();
    const medicId = data?.selectorMedicos?.medic;
    const bodyAppointment: AppointmentResponse = {
    date: date || "",
    time: data?.time || "",
    visitReason: data?.visitReason || "",
    };
    const url = `${import.meta.env.VITE_BACKEND_URL}/api/v1/appointments/schedule?patientId=${patientId}&medicId=${medicId}`;
    const params: RequestInit = {
    method: "POST",
    headers: {
        'Authorization': `${token}`,
        "Content-Type": "application/json"
    },
    body: JSON.stringify(bodyAppointment)
    };
    const addAppointmentResponse = await fetch(url, params)
    .then(async (response) => {
        if(!response.ok){
            const responseBody = await response.json();
            throw new Error(`${response.status}: ${responseBody[0].error || responseBody[0].ERROR}`);
        }
        return await response.json();
    })
    .then((response) => {
        return response;
    })
    .catch((e) => {
        const error = handleError(e);
        setError(error);
        return null;
    })
    return addAppointmentResponse;
};