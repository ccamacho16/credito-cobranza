export enum UserStatus {
    ACTIVO = 'ACTIVO',
    BLOQUEADO = 'BLOQUEADO',
    SUSPENDIDO = 'SUSPENDIDO'
}

export function getUserStatusLabel(status: UserStatus): string {
    const labels: Record<UserStatus, string> = {
        [UserStatus.ACTIVO]: 'Activo',
        [UserStatus.BLOQUEADO]: 'Bloqueado',
        [UserStatus.SUSPENDIDO]: 'Suspendido'
    };
    return labels[status] || 'Desconocido';
}