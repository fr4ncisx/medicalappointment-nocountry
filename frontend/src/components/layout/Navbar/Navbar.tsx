import { Box, Typography } from "@mui/material";
import { Anchor } from "@ui/Anchor/Anchor";
import { CustomButton } from "@ui/CustomButton/CustomButton";
import { NavbarStyles } from "./NavbarStyles";

export const Navbar = () => {
    return (
        <Box sx={NavbarStyles.nav}>
            <Box sx={NavbarStyles.logo} />
            <Box sx={NavbarStyles.items}>
                <Anchor to="/medicos-disponibles" ariaLabel="navegar a la pagina de medicos disponibles">
                    <Typography fontFamily="Inria Sans Bold">Médicos Disponibles</Typography>
                </Anchor>
                <Anchor to="#" role="button" ariaLabel="navegar a la ventana de registro de usuarios">
                    <Typography fontFamily="Inria Sans Bold">Registrarse</Typography>
                </Anchor>
                <CustomButton>
                    <Typography fontFamily="Inria Sans Bold" textTransform="none">Iniciar Sesión</Typography>
                </CustomButton>
            </Box>
        </Box>
    );
}