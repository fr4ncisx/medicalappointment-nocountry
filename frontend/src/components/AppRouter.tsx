import { BrowserRouter, Route, Routes } from "react-router";
import { Layout } from "./layout/Layout";
import { Welcome } from "./sections/Welcome/Welcome";
import { MedicosDisponiblesSection } from "./sections/MedicosDisponibles/MedicosDisponiblesSection";
import { AgendaTuCitaSection } from "./sections/AgendaTuCita/AgendaTuCitaSection";

export const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<>ERROR 404: page not found</>} />
                <Route element={<Layout />}>
                    <Route path="/medicos-disponibles" element={<MedicosDisponiblesSection />} />
                    <Route path="/agenda-tu-cita" element={<AgendaTuCitaSection />} />
                    <Route path="/" element={<Welcome />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}