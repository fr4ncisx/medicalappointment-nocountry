import { ObjectStyles } from "@tipos/component";
import { CSSProperties } from "react";

export const AgendaCSStyles: Record<string, CSSProperties> = {
    formCalendar: {
        display: "flex",
        justifyContent: "center",
        alignItems: "start",
        gap: "2rem"
    },
    svg: {
        height: "26px",
        width: "26px",
        textAlign: "center",
        fill: "none",
        stroke: "currentColor",
        strokeWidth: 1.5,
    },
    path: {
        strokeLinecap: "round",
        strokeLinejoin: "round",
    }
}

export const AgendaTuCitaStyles: ObjectStyles = {
    container: {
        display: "flex",
        flexDirection: "column",
        gap: "3rem",
        margin: "2rem 0",
        height: "100vh",
    },
    title: {
        color: "#185D3B",
        fontWeight: "bold",
        marginLeft: "15px",
        textAlign: "center"
    },
    form: {
        minWidth: "300px",
        maxWidth: "300px",
        padding: "20px",
        display: 'flex',
        flexDirection: 'column',
        borderRadius: "10px",
        border: "1px solid #c1c1c1",
        backgroundColor: "#fff"
    },
    calendar: {
        width: "fit-content",
        borderRadius: "10px",
        backgroundColor: "#fcfcfc",
        "calendar-date::part(header)": {
            fontSize: "1rem",
            justifyContent: "space-around",
            backgroundColor: "#CCEFDD",
            padding: "0.8rem",
            borderRadius: "10px",
        },
        "calendar-date::part(container)": {
            padding: "10px 10px 5px",
            border: "1px solid #cdcdcd",
            borderRadius: "10px",
            fontSize: "1.2rem",
            width: "100%",
        },
        "calendar-month::part(table)": {
            fontSize: "1rem",
            borderSpacing: "0px 5px",
            borderCollapse: "separate",
            width: "100%",
        },
        "calendar-date::part(button)": {
            width: "25px",
            height: "25px",
            borderRadius: "50%",
            backgroundColor: "#198751",
            color: "#fff",
            border: "none",
        },
        "calendar-month::part(button)": {
            width: "35px",
            height: "35px",
            padding: "5px",
            borderRadius: "50%",
        },
        "calendar-month::part(selected)": {
            backgroundColor: "#198751",
        },
    },
}