export const agendaSchema = {
    "type": "object",
    "required": [
        "specialty",
        "selectorMedicos",
        "time"
    ],
    "properties": {
        "specialty": {
            oneOf: [
                {
                    const: "CLINICA",
                    title: "CLINICA",
                },
                {
                    const: "CARDIOLOGIA",
                    title: "CARDIOLOGIA",
                },
                {
                    const: "NEUROLOGIA",
                    title: "NEUROLOGIA",
                },
                {
                    const: "PSIQUIATRIA",
                    title: "PSIQUIATRIA",
                },
                {
                    const: "PSICOLOGIA",
                    title: "PSICOLOGIA",
                },
                {
                    const: "NUTRICION",
                    title: "NUTRICION",
                },
                {
                    const: "DERMATOLOGIA",
                    title: "DERMATOLOGIA",
                },
                {
                    const: "GINECOLOGIA",
                    title: "GINECOLOGIA",
                },
            ],
        },
        "selectorMedicos": {
            "type": "object",
            "properties": {
                "medic": {
                    "type": "string",
                }
            }
        },
        "time": {
            "type": "string",
            "errorMessage": {
                "format": "El formato de la hora no es válido."
            }
        },
        "visitReason": {
            "type": "string",
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
            "scope": "#/properties/selectorMedicos",
            "label": "Doctor", 
            "options": {
                "customControl": "selectorMedicos",
            }
        },
        {
            "type": "Control",
            "scope": "#/properties/time",
            "label": "Horario",
            "options": {
                "format": "time"
            }
        },
        {
            "type": "Control",
            "scope": "#/properties/visitReason",
            "label": "Razón de la visita"
        }
    ]
}