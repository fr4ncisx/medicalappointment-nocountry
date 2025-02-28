import { BrowserRouter, Route, Routes } from "react-router";
import { Layout } from "./layout/Layout";
import { Welcome } from "./sections/Welcome/Welcome";
import { MedicosDisponiblesSection } from "./sections/MedicosDisponibles/MedicosDisponiblesSection";

export const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<>ERROR 404: page not found</>} />
                <Route element={<Layout />}>
                    <Route path="/medicos-disponibles" element={<MedicosDisponiblesSection />} />
                    <Route path="/" element={<Welcome />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}