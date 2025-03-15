import { CustomButton } from "@ui/CustomButton/CustomButton";
import { Box, Typography } from "@mui/material";
import { MedicosDisponiblesStyles } from "./MedicosDisponiblesStyles";
import DoctorCard from "./Components/DoctorCard";
import Filters from "./Components/Filters";
import ModalMenu from "../Welcome/ModalMenu/ModalMenu";
import CustomModal from "@ui/CustomModal/CustomModal";
import SearchBar from "./Components/SearchBar/SearchBar";
import { DoctorData } from "@tipos/backendTypes";
import { CustomError } from "@tipos/types";
import { useUserStore } from "@store/user.store";
import { useState, useEffect } from "react";

interface DoctorsResponse {
    doctors: DoctorData[]
}

export const MedicosDisponiblesSection = () => {
  const [medicos, setMedicos] = useState<DoctorData[]>([]);
  const [error, setError] = useState<CustomError | null>(null);
  const [loading, setLoading] = useState<Boolean>(false);
  const getToken = useUserStore(state => state.getToken);

  useEffect(() => {
    setLoading(true);
    const token = getToken();
    fetch(`${import.meta.env.VITE_BACKEND_URL}/api/v1/medic`, {method: "GET", headers: {"Content-Type": "application/json"}})
    .then(async (response) => {
        const responseBody = await response.json();
        if (!response.ok) {
            throw new Error(responseBody.error);
        } else{
            setMedicos(responseBody.medics)
        }
        return responseBody;
    })
    .catch((error) => {
        let errorMsg = error.message;

        if(error.message === "Failed to fetch"){
            errorMsg = "Falló la conexión";
        }

        setError({
            description: errorMsg,
            type: "fetch",
            status: error.status
        })
    })
    .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <Typography variant="h6">Cargando los médicos disponibles</Typography>;
  }
  console.log(medicos)


  return (
    <>
      <Box sx={MedicosDisponiblesStyles.container}>
        {/* Box Izquierdo - Container */}
        <Box sx={MedicosDisponiblesStyles.asideContainer}>
          <SearchBar />
          {/* Box - Filtros */}
          <Filters />
          {/* Boton - Mostrar Doctores */}
          <Box
            sx={{ display: "flex", justifyContent: "center", marginTop: "2rem" }}
          >
            <CustomButton>
              {/* OnClick={} debería pedir los doctores teniendo en cuenta los filtros. */}
              <Typography
                fontFamily="Inria Sans Bold"
                textTransform="none"
                fontSize={"1.4rem"}
              >
                Mostrar Doctores
              </Typography>
            </CustomButton>
          </Box>
        </Box>

        {/* Box Derecho - Grid Doctores */}
        <Box sx={MedicosDisponiblesStyles.gridContainer}>
          {!error && !loading &&  (
            medicos.map((doctor) => (
              <DoctorCard key={doctor.id} doctor={doctor} />
            ))
          ) }
        </Box>
      </Box>
      <CustomModal>
        <ModalMenu />
      </CustomModal>
    </>
  );
};
