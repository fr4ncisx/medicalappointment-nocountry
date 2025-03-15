export const agendaSchema = {
    "type": "object",
    "required": [
        "specialty",
        "doctor",
        "time"
    ],
    "properties": {
        "specialty": {
            oneOf: [
                {
                    const: "Dermatologia",
                    title: "Dermatología",
                },
                {
                    const: "Endocrinologia",
                    title: "Endocrinología",
                },
                {
                    const: "Cardiologia",
                    title: "Cardiología",
                },
                {
                    const: "Neumologia",
                    title: "Neumología",
                },
                {
                    const: "Ginecologia",
                    title: "Ginecología",
                },
                {
                    const: "Oftalmologia",
                    title: "Oftalmología",
                },
            ],
        },
        "doctor": {
            oneOf: [
                {
                    const: "Swong",
                    title: "SWONG, Jorge",
                },
                {
                    const: "Preslie",
                    title: "PRESLIE, Miranda",
                },
                {
                    const: "Fernandez",
                    title: "FERNANDEZ, Milagros",
                },
            ],
        },
        "time": {
            "type": "string",
            "errorMessage": {
                "format": "El formato de la hora no es válido."
            }
        }
    },
    "errorMessage": {
        "required": "El campo es obligatorio."
    }
}

export const agendaUiSchema = {
    "type": "VerticalLayout",
    "elements": [
        {
            "type": "Control",
            "scope": "#/properties/specialty",
            "label": "Especialidad",
        },
        {
            "type": "Control",
            "scope": "#/properties/doctor",
            "label": "Doctor",
        },
        {
            "type": "Control",
            "scope": "#/properties/time",
            "label": "Horario",
            "options": {
                "format": "time"
            }
        },
    ]
}