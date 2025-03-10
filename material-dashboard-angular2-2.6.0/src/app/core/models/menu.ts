
export interface Menu {
    id: number;
    name: string;
    url: string;
    icon: string;
    sequence: number;
    fatherDto: Menu;
    childrenDto: Menu[];
    isOpen: boolean;
}