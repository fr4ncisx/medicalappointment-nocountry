import { Grid2 } from "@mui/material";
import { DashboardPatientStyles } from "./DashboardPatientStyles";
import { AsideBar } from "@ui/AsideBar/AsideBar";
import { PatientMainContent } from "./PatientMainContent/PatientMainContent";
import { PATIENT_LINKS } from "./PATIENT_LINKS.tsx";

export const DashboardPatient = () => {
    return (
        <Grid2 container sx={DashboardPatientStyles.container}>
            <AsideBar links={PATIENT_LINKS} />
            <PatientMainContent />
        </Grid2>
    );
}