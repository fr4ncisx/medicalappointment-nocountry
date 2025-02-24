import { Outlet } from "react-router";
import { Footer } from "./Footer/Footer";
import { Navbar } from "./Navbar/Navbar";

export const Layout = () => {
    return (
        <>
            <Navbar />
            <main>
                <Outlet />
            </main>
            <Footer />
        </>
    );
}