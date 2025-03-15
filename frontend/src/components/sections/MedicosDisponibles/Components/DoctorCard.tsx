import { Box, Typography } from "@mui/material";
import DoctorAvatar from "../DoctorAvatar";
import { MedicosDisponiblesStyles } from "../MedicosDisponiblesStyles";
import { useNavigate } from "react-router";
import { DoctorData } from "@tipos/backendTypes";

interface DoctorCardProps {
  doctor: DoctorData;
}

const DoctorCard: React.FC<DoctorCardProps> = ({ doctor }) => {
  const { id, name, lastname, speciality } = doctor;
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/medico/${id}`);
  };
  return (
    <Box
      key={id}
      sx={MedicosDisponiblesStyles.doctorCard}
      onClick={handleClick}
    >
      <Box sx={MedicosDisponiblesStyles.avatarContainer}>
        <DoctorAvatar profile_img="" />
      </Box>
      <Typography fontFamily="Inria Sans Bold" fontSize="1.2rem">
        {name} {lastname}
      </Typography>
      <Typography fontSize="1rem" color="gray">
        {speciality}
      </Typography>
    </Box>
  );
};

export default DoctorCard;
