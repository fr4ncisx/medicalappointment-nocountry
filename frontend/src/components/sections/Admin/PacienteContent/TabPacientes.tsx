import { SectionWrapper } from "@components/layout/SectionWrapper";
import { ADMIN_LINKS } from "../ADMIN_LINKS";
import { TableContextProvider } from "@context/table.provider";
import { PacienteContent } from "./PacienteContent";
import { getPacientes } from "@services/getPacientes";
import { Box, Typography } from "@mui/material";
import { TabsAdmin } from "@tipos/component";
import { CustomButton } from "@ui/CustomButton/CustomButton";
import { TabStyles } from "../TabStyles";

interface Props {
    handleChangeTab: (tab: TabsAdmin) => void
}

export const TabPacientes = ({ handleChangeTab }: Props) => {
    return (
        <SectionWrapper asideBarItems={ADMIN_LINKS}>
            <TableContextProvider fetchRows={getPacientes}>
                <Box sx={TabStyles.container}>
                    <CustomButton onClick={() => handleChangeTab("pacientes")} sx={{ ...TabStyles.tab, backgroundColor: "#198751", color: "#f1f1f1" }}>
                        Pacientes
                    </CustomButton>
                    <CustomButton onClick={() => handleChangeTab("medicos")} sx={TabStyles.tab}>
                        Médicos
                    </CustomButton>
                </Box>
                <Typography variant="h5" gutterBottom color="primary" sx={{ letterSpacing: "4px" }}>
                    Tabla de Pacientes
                </Typography>
                <PacienteContent />
            </TableContextProvider>
        </SectionWrapper>
    );
} 