export interface RcClientDto{
    id: number;
    dni: string;
    names: string;
    lastnames: string;

    birthdate: Date;
    gender: string;
    civilStatus: string;
    phone: string;
    
    email: string;
    incomeMonth: number;
    category: string;
    occupation: string;
    
    addressHome: string;
    addressBusiness: string;

    comment: string;
}