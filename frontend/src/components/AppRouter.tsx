import { BrowserRouter, Route, Routes } from "react-router";
import { Layout } from "./layout/Layout";

export const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route element={<Layout />}>

                </Route>
            </Routes>
        </BrowserRouter>
    );
}