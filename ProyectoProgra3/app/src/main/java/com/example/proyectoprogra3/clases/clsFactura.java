package com.example.proyectoprogra3.clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class clsFactura  extends RealmObject{

    @PrimaryKey
    private int NumFactura;
    private String FechaFactura;
    private String FechaVencimiento;
    private String MontoFactura;
    private String PagosFactura;
    private String EstadoDeFactura;
}
