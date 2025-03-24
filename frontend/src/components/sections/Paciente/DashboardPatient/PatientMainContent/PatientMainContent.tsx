import { Box, Button, Grid2 } from "@mui/material";
import { CitasTable } from "../ContenidoCitasTable/CitasTable";
import { useState } from "react";
import { HistMedicTable } from "../ContenidoHistMedicTable/HistMedicTable";
import { DashboardPatientStyles } from "../DashboardPatientStyles";
import { PatientMainContentStyles } from "./PatientMainContentStyles";
import { AgendarCitaButton } from "./ButtonsMainContent/AgendarCitaButton";
import { DescargarHistMedicButton } from "./ButtonsMainContent/DescargarHistMedicButton";

type TabValues = "citas" | "historial-medico";

export const PatientMainContent = () => {
    const [tab, setTab] = useState<TabValues>("citas");

    const handleTab = (value: TabValues) => {
        setTab(value);
    }

    return (
        <Grid2 size="grow" sx={DashboardPatientStyles.mainContent}>
            <Box sx={{ display: "flex", justifyContent: "space-between", marginTop: "3rem" }}>
                <Box sx={PatientMainContentStyles.tabContainer}>
                    <Button sx={{ ...PatientMainContentStyles.startTab, color: tab === "citas" ? "#f1f1f1" : "#c1c1c1" }} onClick={() => handleTab("citas")}>
                        Citas
                    </Button>
                    <Button sx={{ ...PatientMainContentStyles.endTab, color: tab === "historial-medico" ? "#f1f1f1" : "#c1c1c1"}} onClick={() => handleTab("historial-medico")}>
                        Historial médico
                    </Button>
                </Box>

                {
                    tab === "citas"
                        ? <AgendarCitaButton />
                        : <DescargarHistMedicButton />
                }

            </Box>
            {
                tab === "citas"
                    ? <CitasTable />
                    : <HistMedicTable />
            }
        </Grid2>
    );
}