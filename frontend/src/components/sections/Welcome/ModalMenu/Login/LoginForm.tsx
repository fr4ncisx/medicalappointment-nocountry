/* eslint-disable @typescript-eslint/no-explicit-any */
import { Box, Typography } from "@mui/material";
import { CustomError, JwtData } from "@tipos/types";
import { Form } from "@ui/Form/Form";
import { LoginButton } from "./LoginButton";
import { FormData, loginSchema, loginUiSchema } from "./loginSchema";
import { FormStyle } from "./LoginStyles";
import { useState } from "react";
import { loginUser } from "@services/loginUser";
import { useNavigate } from "react-router";
import { useUserStore } from "@store/user.store";
import { useModalStore } from "@store/modal.store";
import { UserRole } from "@tipos/store";
import { jwtDecode } from "jwt-decode";

export const LoginForm = () => {
    const [data, setData] = useState<FormData>();
    const [error, setError] = useState<CustomError>(null);
    const navigate = useNavigate();
    const getDashboardUrl = useUserStore(state => state.getUserDashboardURL);
    const saveToken = useUserStore(state => state.saveToken);
    const setUserData = useUserStore(state => state.setUserData);

    const redirectTo = useModalStore(state => state.modalData.redirect);
    const [loading, setLoading] = useState(false);

    const handleLogin = async () => {
        setError(null);
        setLoading(true);
        const token = await loginUser({ data, setError });
        if (token !== null) {
            saveToken(token);
            const decoded: JwtData = jwtDecode(token);
            const userData = {
                email: decoded.sub,
                role: UserRole[decoded.role]
            };
            setUserData(userData);
            const dashboard = getDashboardUrl();
            const to = (redirectTo !== null && redirectTo !== undefined) ? redirectTo : dashboard;
            setLoading(false);
            navigate(to);
        } else {
            setLoading(false);
        }
    }

    const handleChange = ({ data, errors }: { data: any, errors: any[] }) => {
        setData(data);
        if (errors.length !== 0) {
            setError({ type: "input", description: "entrada invalida en formulario de inicio de sesion" })
        } else {
            setError(null);
        }
    }
    return (
        <Box sx={FormStyle.form}>
            <Box>
                <Form schema={loginSchema} uiSchema={loginUiSchema} data={data} onChange={handleChange} />
                {
                    error?.type === "fetch" && <Typography color="error" textAlign="center">{error.description}</Typography>
                }
            </Box>
            <LoginButton loading={loading} error={error} handleLogin={handleLogin} />
        </Box>
    );
}