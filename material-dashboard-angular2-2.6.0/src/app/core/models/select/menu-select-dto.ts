export interface MenuSelectDto{
    id: number;
    name: string;
    idFather: number|null;
    select: boolean;
}