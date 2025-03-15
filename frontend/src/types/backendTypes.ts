export interface DoctorData {
    id: number,
    name: string,
    lastname: string,
    description: string,
    state: string,
    documentId: string,
    gender: string,
    speciality: string,
    phone: string,
}

export interface PacienteData {
    id:                   number;
    firstName:            string;
    lastName:             string;
    documentId:           string;
    birthDate:            Date;
    gender:               string;
    phone:                string;
    address:              string;
    emergencyContactInfo: string;
}

export interface CitasData {
    id: number;
    date: string;
    time: string;
    visitReason: string;
    status: string;
    medic: {
        id: number;
        name: string;
        lastname: string;
    }
}

export interface CitasPasadasData {
    id: number;
    recordDate: string;
    visitReason: string;
    symptoms: string;
    symptomsFrequency: string;
    medicalHistory: string;
    smokingHabits: boolean;
    activityHabits: boolean;
    additionalInfo: string;
    diagnosis: string;
    treatment: string;
    doctorNotes: string;
}