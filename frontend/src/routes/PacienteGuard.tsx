import { useUserStore } from "@store/user.store";
import { Roles } from "@tipos/store";
import { Navigate, Outlet } from "react-router";

export const PacienteGuard = () => {
    const hasRole = useUserStore(state => state.hasRole);
    const isLogged = useUserStore(state => state.isLogged);
    const isUserLogged = isLogged();
    return (isUserLogged && hasRole(Roles.PACIENTE)) ? <Outlet /> : <Navigate to="/" replace />;
}