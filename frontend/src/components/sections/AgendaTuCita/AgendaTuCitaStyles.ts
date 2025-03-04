import { SxProps } from "@mui/material";

export const AgendaTuCitaStyles: Record<string, SxProps> = {
    container: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-evenly",
        height: "1000px",
    },
    title: {
        color: "#185D3B",
        fontWeight: "bold",
        marginLeft: "15px",
    },
    form: {
        flexGrow: 1,
        width:"400px",
        backgroundColor: "#dedede",
    },
    formLeft: {
        paddingTop: "100px",
        minHeight: "800px",
        maxWidth: "400px",
        padding: "40px",
        display: 'flex',
        flexDirection: 'column',
        gap: "20px",
    },
    button: {
        width: "30px",
    },
    calendar: {
        backgroundColor: "#fcfcfc",
        padding: "20px",
        flexGrow: 2,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        "calendar-date::part(header)": {
            fontSize: "30px",
            justifyContent: "space-around",
            backgroundColor: "#CCEFDD",
            padding: "30px",
            borderRadius: "10px",
        },
        "calendar-date::part(container)": {
            padding: "20px",
            border: "2px solid #cdcdcd",
            borderRadius: "4px",
            fontSize: "45px",
            minHeight: "630px",
            width: "1200px",
        },
        "calendar-date::part(button)": {
            width: "50px",
            height: "50px",
            borderRadius: "50%",
            backgroundColor: "#198751",
            border: "none",
        },
        "calendar-month::part(table)": {
            fontSize: "30px",
            borderSpacing: "0px 10px",
            borderCollapse: "separate",
            width: "1150px",
        },
        "calendar-month::part(button)": {
            width: "50px",
            height: "50px",
            padding: "5px",
            borderRadius: "50%",
        },
        "calendar-month::part(selected)": {
            backgroundColor: "#198751",
        },
    },
}