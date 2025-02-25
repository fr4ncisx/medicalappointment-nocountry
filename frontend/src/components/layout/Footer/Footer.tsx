import { Box, Container, Typography, IconButton } from "@mui/material";
import { Facebook, LinkedIn, Twitter, Instagram } from "@mui/icons-material";
import "./Footer.css";
import { FooterStyle } from "./FooterStyle";
import { Link } from "react-router";

export const Footer = () => {
  return (
    <Container
      component="footer"
      sx={FooterStyle.footerContainer}
      maxWidth={false}
    >
      {/*1er Box - Logo y Title*/}
      <Box sx={FooterStyle.footerTitle}>
        <Link
          to="#"
          style={{
            textDecoration: "none",
            fontWeight: "bold",
            color: "black",
          }}
        >
          MedicalAppointment {/* Appointment iría verde oscuro, falta logo */}
        </Link>
        <Typography variant="body2" color="textSecondary">
          © No Country 2025
        </Typography>
      </Box>

      {/* 2do Box - Secciones */}
      <Box sx={FooterStyle.footerLinks}>
        <Box sx={FooterStyle.footerLinksSection}>
          <Typography
            variant="subtitle1"
            fontWeight="bold"
            sx={FooterStyle.footerLinksSectionTitle}
          >
            NOSOTROS
          </Typography>
          <Link to="#">¿Quiénes somos?</Link>
          <Link to="#">Únete a nuestros médicos</Link>
        </Box>

        <Box sx={FooterStyle.footerLinksSection}>
          <Typography
            variant="subtitle1"
            fontWeight="bold"
            sx={FooterStyle.footerLinksSectionTitle}
          >
            SERVICIOS
          </Typography>
          <Link to="#">Agendá una cita</Link>
          <Link to="#">Consulta sobre nuestros médicos</Link>
          <Link to="#">Ver tus citas</Link>
        </Box>

        <Box sx={FooterStyle.footerLinksSection}>
          <Typography
            variant="subtitle1"
            fontWeight="bold"
            sx={FooterStyle.footerLinksSectionTitle}
          >
            RECURSOS
          </Typography>
          <Link to="#">Preguntas frecuentes</Link>
          <Link to="#">Reclamos</Link>
        </Box>
      </Box>

      {/* 3er Box - Redes Sociales */}
      <Box sx={FooterStyle.footerSocial}>
        <Typography variant="subtitle1" fontWeight="bold">
          ¡SEGUINOS!
        </Typography>
        <Box className="footer-social-icons">
          {/* Crear clase footer-icon que envuelva cada ícono en un círculo verde o editar el componente de cada ícono con MUI */}
          <IconButton>
            <Facebook />
          </IconButton>
          <IconButton>
            <Instagram />
          </IconButton>
          <IconButton>
            <LinkedIn />
          </IconButton>
          <IconButton>
            <Twitter />
          </IconButton>
        </Box>
      </Box>
    </Container>
  );
};
