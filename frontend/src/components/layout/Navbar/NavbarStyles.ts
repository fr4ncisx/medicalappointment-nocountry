import { SxProps } from "@mui/material";

const DisplayFlexStyles: SxProps = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
}

export const NavbarStyles: Record<string, SxProps> = {
    nav: {
        backgroundColor: "#F1F1F1",
        padding: "1.5rem",
        width: "100%",
        height: "90px",
        ...DisplayFlexStyles
    },
    logo: {
        width: "70px",
        height: "70px",
        backgroundColor: "#4DDB94",
        border: "20px solid #25C274",
        borderRadius: "9999px"
    },
    items: {
        ...DisplayFlexStyles,
        gap: "1rem"
    }
};
