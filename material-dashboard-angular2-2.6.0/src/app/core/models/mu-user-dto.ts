import { UserStatus } from "./Mapeo/user-status";

export interface MuUserDto{
    id: number;
    name: string;
    username: string;
    password: string;
    email: string;
    status: UserStatus;

    idRole: string;
    nameRole: string;
    idBranch: string;
    nameBranch: string;
}