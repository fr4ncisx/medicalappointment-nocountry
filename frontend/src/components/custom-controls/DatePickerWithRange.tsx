/* eslint-disable @typescript-eslint/no-explicit-any */
import { ControlProps } from '@jsonforms/core';
import { withJsonFormsControlProps } from '@jsonforms/react';
import { Box } from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers';
import { format, parse } from "date-fns";

const DatePickerWithRange = ({ data, path, enabled, handleChange }: ControlProps) => {
    const startDate = data?.startDate ? parse(data.startDate, "dd/MM/yyyy", new Date()) : null;
    const endDate = data?.endDate ? parse(data.endDate, "dd/MM/yyyy", new Date()) : null;
    const dateRangeError = (endDate !== null && startDate !== null) && endDate < startDate;

    const handleStartDateChange = (value: any) => {
        const date = new Date(value);
        const correctDate = format(date, "dd/MM/yyyy");
        handleChange(`${path}.startDate`, correctDate);
    }

    const handleEndDateChange = (value: any) => {
        const date = new Date(value);
        const correctDate = format(date, "dd/MM/yyyy");
        handleChange(`${path}.endDate`, correctDate);
    }

    return (
        <Box sx={{ display: "flex", gap: "1rem" }}>
            <DatePicker
                disabled={!enabled}
                value={startDate}
                name='startDate'
                format='dd/MM/yyyy'
                onChange={(value) => handleStartDateChange(value)}
                slotProps={{
                    textField: {
                        variant: 'outlined',
                        error: dateRangeError,
                        helperText: dateRangeError ? "Rango invalido" : "",
                    },
                }}
            />
            <DatePicker
                disabled={!enabled}
                value={endDate}
                name='endDate'
                format='dd/MM/yyyy'
                onChange={(value) => handleEndDateChange(value)}
                slotProps={{
                    textField: {
                        variant: 'outlined',
                        error: dateRangeError
                    },
                }}
            />
        </Box>
    );
}

export default withJsonFormsControlProps(DatePickerWithRange);