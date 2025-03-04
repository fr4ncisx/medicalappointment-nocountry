import { Typography } from "@mui/material";
import { CustomButton } from "@ui/CustomButton/CustomButton";
import { Form } from "@ui/Form";
import { FormEvent, useState } from "react";
import { useNavigate } from "react-router";
import { FormData, loginSchema, loginUiSchema } from "./loginSchema";
import { FormStyle } from "./LoginStyles";
import { useUserStore } from "@store/user.store";
import { useModalStore } from "@store/modal.store";

export default function Login() {
    const [data, setData] = useState<FormData>();
    const navigate = useNavigate();
    const getDashboardUrl = useUserStore(state => state.getUserDashboardURL);
    const redirectTo = useModalStore(state => state.modalData.redirect);

    const handleChange = ({ data, errors }: { data: any, errors: any }) => {
        if (!errors) setData(data);
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const isUserSubmited = true;
        if (isUserSubmited) {
            const dashboard = getDashboardUrl();
            const to = (redirectTo !== null && redirectTo !== undefined) ? redirectTo : dashboard;
            navigate(to);
        }
    }

    return (
        <form method="POST" onSubmit={handleSubmit} style={FormStyle}>
            <Form schema={loginSchema} uiSchema={loginUiSchema} data={data} onChange={handleChange} />
            <CustomButton type="submit">
                <Typography textTransform="none" fontSize="18px">
                    Iniciar Sesión
                </Typography>
            </CustomButton>
        </form>
    );
}