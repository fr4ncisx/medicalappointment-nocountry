import { Box, Typography, Stack } from "@mui/material";
import { Anchor } from "@ui/Anchor/Anchor";
import avatarSvg from "../avatar-svgrepo-com.svg";
import { DoctorDetailsStyles } from "./doctorDetailsStyles";
import { CustomButton } from "@ui/CustomButton/CustomButton";
import MyLocationIcon from '@mui/icons-material/MyLocation';
import { Icons, Characteristics, Specialties } from "./Datos";
import CustomModal from "@ui/CustomModal/CustomModal";
import ModalMenu from "../../Welcome/ModalMenu/ModalMenu";
import { useModalStore } from "@store/modal.store";
import { useNavigate, useParams } from "react-router";
import { useUserStore } from "@store/user.store";
import { useEffect, useState } from "react";
import { CustomError } from "@tipos/types";
import { DoctorData } from "@tipos/backendTypes";

export const DoctorDetails = () => {
    // const {id} = useParams();
    // const [detallesDoctor, setDetallesDoctor] = useState<DoctorData[]>([]);
    const setModalData = useModalStore((state) => state.setModalData);
    const isLogged = useUserStore(state => state.isLogged);
    const isUserLogged = isLogged();
    const navigate = useNavigate();
    // const [loading, setLoading] = useState(false);
    // const [error, setError] = useState<CustomError | null>(null);

    const agendaRedirect = () => {
        if(isUserLogged) {
            navigate("/agendar-cita");
        } else{
            setModalData({
                showModal: true,
                title: "¿Querés agendar una cita?",
                operation: "menu"
            });
        }
    }

    // useEffect(() => {
    //     setLoading(true);
    //     fetch(`${import.meta.env.VITE_BACKEND_URL}/api/v1/medic/${id}`, {method: "GET", headers: {"Content-Type": "application/json"}})
    //     .then(async (response) => {
    //         const responseBody = await response.json();
    //         if (!response.ok) {
    //             throw new Error(responseBody.error);
    //         } else{
    //             setDetallesDoctor(responseBody.medic);
    //         }
    //         return responseBody;
    //     })
    //     .catch((error) => {
    //         let errorMsg = error.message;
    
    //         if(error.message === "Failed to fetch"){
    //             errorMsg = "Falló la conexión";
    //         }
    
    //         setError({
    //             description: errorMsg,
    //             type: "fetch",
    //             status: error.status
    //         })
    //     })
    //     .finally(() => setLoading(false));
    // }, []);

    // if (loading) {
    //     return <Typography align="center">Cargando información del médico...</Typography>
    // }
    // console.log(detallesDoctor)

    return (
        <>
            <Box sx={{display: "flex", justifyContent: "center"}}>
            {/* {!error && !loading && (
                detallesDoctor.map(({id, name, lastname, description, state, gender,speciality}) => ( */}
                    <Box sx={DoctorDetailsStyles.container}>
                        <Box sx={DoctorDetailsStyles.left}>
                            <Box sx={DoctorDetailsStyles.avatarContainer}>
                                <img src={avatarSvg} alt="Doctor Avatar" width="300" height="300" />
                            </Box>
                            <Box sx={DoctorDetailsStyles.socialCont}>
                                {Icons.map(({ Icon, ariaLabel, to }, index) => (
                                    <Anchor key={index} to={to} ariaLabel={ariaLabel} target="_blank">
                                        <CustomButton sx={DoctorDetailsStyles.buttonSocial}>
                                            {Icon}
                                        </CustomButton>
                                    </Anchor>
                                ))}
                            </Box>
                            <Box sx={{ textAlign: "center" }}>
                                <Typography fontWeight="bold" variant="h4">$100 - $350</Typography>
                                <Typography variant="h6" color="#676767">Online - Presencial</Typography>
                            </Box>
                            <CustomButton sx={{ width: "200px" }} onClick={agendaRedirect}>
                                <Typography textTransform="none" fontSize="22px">Agendar cita</Typography>
                            </CustomButton>

                            <Stack direction="row" spacing={2} flexWrap="wrap" justifyContent="center">
                                <Typography variant="h6" color="#198751" style={{marginBottom: "10px"}} sx={DoctorDetailsStyles.contTypo}>Hombre</Typography>
                                <Typography variant="h6" color="#198751" style={{marginBottom: "10px"}} sx={DoctorDetailsStyles.contTypo}>Buenos Aires</Typography>
                            </Stack>
                        </Box>
                        <Box sx={DoctorDetailsStyles.right}>
                            <Box sx={DoctorDetailsStyles.nameSpec}>
                                <Typography variant="h3" fontWeight="bold">Leonel Franchesco</Typography>
                                <Typography variant="h5" color="#454545">Especialista en psicología</Typography>
                            </Box>
                            <Box sx={{ maxWidth: "400px" }}>
                                <Box sx={{ display: "flex", alignItems: "center" }}>
                                    <MyLocationIcon sx={{ marginRight: "4px" }} />
                                    <Typography variant="h5">Buenos Aires</Typography>
                                </Box>
                                <Typography variant="h6">Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam dicta esse, quae, nisi veniam eveniet aperiam optio odit quia ex cupiditate ullam itaque! Libero eligendi asperiores perferendis autem vitae iure?</Typography>
                            </Box>
                            <Box sx={DoctorDetailsStyles.specialtyCont}>
                                <Typography variant="h4" fontWeight="bold" marginBottom="5px">Especialidades</Typography>
                                <Stack direction="row" spacing={2} flexWrap="wrap">
                                    <Typography variant="h6" color="#198751" style={{marginBottom: "10px"}} sx={DoctorDetailsStyles.contTypo}>Psicología</Typography>
                                </Stack>
                            </Box>
                        </Box>
                    </Box>
                {/* ))
            )} */}
                
            </Box>
            <CustomModal>
                <ModalMenu />
            </CustomModal>
        </>
    )
};