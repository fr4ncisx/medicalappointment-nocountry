import { CircularProgress, Typography } from "@mui/material";
import { CustomButton } from "@ui/CustomButton/CustomButton";
import { CustomError } from "@tipos/types";

interface Props {
    loading: boolean
    error: CustomError
    handleLogin: () => Promise<void>
}

export const LoginButton = ({ error, loading, handleLogin }: Props) => {
    return (
        <CustomButton type="submit" onClick={handleLogin} disabled={error !== null && error?.type === "input"}>
            {
                loading
                    ?
                    <CircularProgress color="inherit" size="25px" />
                    :
                    <Typography textTransform="none" fontSize="18px">
                        Iniciar Sesión
                    </Typography>
            }
        </CustomButton>
    );
}