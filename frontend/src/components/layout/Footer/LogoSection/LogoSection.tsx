import { Box, Typography } from "@mui/material";
import { FooterStyle } from "../FooterStyle";
import { Logo } from "../Logo";

export const LogoSection = () => {
    return (
        <Box sx={FooterStyle.footerTitle}>
            <Box display={"flex"} alignItems={"center"}>
                <Logo />
                <Typography>
                    MedicalAppointment
                </Typography>
            </Box>
            <Typography variant="body2" color="textSecondary">
                © No Country 2025
            </Typography>
        </Box>
    );
}