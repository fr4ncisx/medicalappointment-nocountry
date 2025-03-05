import { useState, FormEvent, CSSProperties } from "react";
import "cally";
import { Box, Typography } from '@mui/material';
import { AgendaCSStyles, AgendaTuCitaStyles } from "./AgendaTuCitaStyles";
import { CustomButton } from '@ui/CustomButton/CustomButton';
import { Form } from "@ui/Form";
import { agendaSchema, agendaUiSchema } from "./agendaSchema";

interface FormData {
    specialty: string | undefined;
    doctor: string | undefined;
    time: string;
}

export const AgendaTuCitaSection = () => {
    const [data, setData] = useState<FormData>();
    const [value, setValue] = useState<string>("");
    const [error, setError] = useState<boolean>(false);

    const handleChange = ({ data, errors }: { data: any, errors: any }) => {
        if (!errors) setData(data);
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!data && !value) {
            setError(true);
        } else {
            setError(false);
            console.log("La cita se ha agendado:", { data, value })
        }
    }

    return (
        <Box sx={AgendaTuCitaStyles.container}>
            <Typography variant="h3" sx={AgendaTuCitaStyles.title}>Agendá tu cita</Typography>

            <form method="POST" onSubmit={handleSubmit} style={AgendaCSStyles.formCalendar}>
                <Box sx={AgendaTuCitaStyles.form}>
                    <Box sx={AgendaTuCitaStyles.formLeft}>
                        <Form schema={agendaSchema} uiSchema={agendaUiSchema} data={data} onChange={handleChange} />

                        {error && (
                            <Typography variant="body1" textAlign="center">
                                Por favor, asegurese de completar todos los campos y seleccionar una fecha
                            </Typography>
                        )}

                        <CustomButton type="submit" sx={AgendaTuCitaStyles.button}>
                            <Typography textTransform="none">Agendá tu cita</Typography>
                        </CustomButton>
                    </Box>
                </Box>

                <Box sx={AgendaTuCitaStyles.calendar}>
                    <calendar-date value={value} onchange={(e) => setValue((e?.target as HTMLInputElement).value)}>
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