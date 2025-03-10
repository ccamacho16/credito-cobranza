package com.template.generic.exception;

/**
 * -------------------------------------------------------------------------*
 * Información General
 * -------------------------------------------------------------------------*
 * Código de Aplicación:
 * Código de Objeto:
 * Descripción:
 * Author Prog.: Crisvel Camacho
 * -------------------------------------------------------------------------*
 * Fecha | Author | Comentario
 * 30.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public class OperationException extends RuntimeException{
    private static final long serialVersionUID = 10316048604864L;

    private ErrorCode codigoError;

    public ErrorCode getCodigoError()
    {
        return codigoError;
    }

    public OperationException(OperationException e)
    {
        super(e);
        this.codigoError = e.codigoError;
    }

    public OperationException(String mensaje)
    {
        super(mensaje);
    }

    public OperationException(String mensaje, ErrorCode codigoError)
    {
        super(mensaje);
        this.codigoError = codigoError;
    }

    public OperationException(String mensaje, Throwable e)
    {
        super(mensaje, e);
    }

    public OperationException(String mensaje, Throwable e, ErrorCode codigoError)
    {
        super(mensaje, e);
        this.codigoError = codigoError;
    }
}
