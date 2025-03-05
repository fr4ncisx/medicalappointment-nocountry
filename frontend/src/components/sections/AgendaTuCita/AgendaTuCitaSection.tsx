import { useState, FormEvent } from "react";
import "cally";
import { Box, Typography } from '@mui/material';
import { AgendaCSStyles, AgendaTuCitaStyles } from "./AgendaTuCitaStyles";
import { CustomButton } from '@ui/CustomButton/CustomButton';
import { Form } from "@ui/Form";
import { agendaSchema, agendaUiSchema } from "./agendaSchema";

interface FormData {
    specialty: string | null;
    doctor: string | null;
    time: string | null;
};

export const AgendaTuCitaSection = () => {
    const [data, setData] = useState<FormData | {}>({
        doctor: null,
        specialty: null,
    });
    const [error, setError] = useState(false);
    const [loading, setLoading] = useState(false);
    const [date, setDate] = useState<string>("");

    const handleChange = ({ data, errors }: { data: any, errors: any }) => {
        setData(data);
        if(errors.length !== 0) {
            setError(true);
        } else {
            setError(false);
        }
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);
        console.info({ data, date });
        if (!data && !date) {
            setError(true);
        } else {
            setError(false);
            setLoading(false);
            console.info("La cita se ha agendado:", { data, date });
        }
    }

    const handleCalendarChange = (event: Event) => {
        const target = event.target as any;
        setDate(target.value);
    }

    return (
        <Box sx={AgendaTuCitaStyles.container}>
            <Typography variant="h3" sx={AgendaTuCitaStyles.title}>Agendá tu cita</Typography>

            <form method="POST" onSubmit={handleSubmit} style={AgendaCSStyles.formCalendar}>
                <Box sx={AgendaTuCitaStyles.form}>
                    <Form schema={agendaSchema} uiSchema={agendaUiSchema} data={data} onChange={handleChange} />

                    {
                        error
                            ? (
                                <Typography variant="body2" textAlign="center" sx={{ color: "#e85c5c", fontWeight: "bold" }}>
                                    Por favor, asegurese de completar todos los campos y seleccionar una fecha
                                </Typography>
                            )
                            : (
                                <Typography variant="body2" textAlign="center" sx={{ color: "#726969", fontWeight: "bold" }}>
                                    Asegurese de seleccionar una fecha en el calendario
                                </Typography>
                            )
                    }

                    <CustomButton
                        type="submit"
                        sx={{ marginTop: "1rem" }}
                        loading={loading}
                        loadingIndicator="Cargando…"
                        disabled={error}
                    >
                        <Typography textTransform="none">Agendá tu cita</Typography>
                    </CustomButton>
                </Box>

                <Box sx={AgendaTuCitaStyles.calendar}>
                    <calendar-date onchange={(e) => handleCalendarChange(e)}>
                        <svg style={AgendaCSStyles.svg} aria-label="Previous" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" {...{ slot: "previous" }}>
                            <path style={AgendaCSStyles.path} d="M15.75 19.5 8.25 12l7.5-7.5"></path>
                        </svg>
                        <svg style={AgendaCSStyles.svg} aria-label="Next" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" {...{ slot: "next" }}>
                            <path style={AgendaCSStyles.path} d="m8.25 4.5 7.5 7.5-7.5 7.5"></path>
                        </svg>
                        <calendar-month ></calendar-month>
                    </calendar-date>
                </Box>
            </form>
        </Box>
    )
}