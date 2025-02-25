import { createTheme } from "@mui/material";

export const theme = createTheme({
    palette: {
      primary: {
        main: "#198751",
        // light: will be calculated from palette.primary.main,
        // dark: will be calculated from palette.primary.main,
        // contrastText: will be calculated to contrast with palette.primary.main
      }
    },
    components: {
      MuiTypography: {
        defaultProps: {
          fontFamily: "Inria Sans",
          fontWeight: 400
        }
      }
    }
  });