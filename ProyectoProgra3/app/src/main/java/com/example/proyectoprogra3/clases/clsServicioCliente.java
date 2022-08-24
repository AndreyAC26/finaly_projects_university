package com.example.proyectoprogra3.clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class clsServicioCliente {

    private Realm realm;

    public clsServicioCliente(Realm _realm){
        this.realm = _realm;
    }

    //Se declara la funcion que resive por parametros los archivos
    public void insertarEstudiante(String _nombre, String _identificacion,  String _telefono, String _correo,
                                    String _saldo){

        int mCodigo = CalculaCodigoCliente();
        //Para guardar los datos en la base de datos
        realm.beginTransaction();
        clsCliente mCliente = realm.createObject(clsCliente.class, mCodigo);
        mCliente.setNombre(_nombre);
        mCliente.setIdentificacion(_identificacion);
        mCliente.setTelefono(_telefono);
        mCliente.setCorreo(_correo);
        mCliente.setSaldo(_saldo);
        realm.commitTransaction();

    }

    public void actualizarEstudiante(String _nombre, String _identificacion, String _correo,
                                     String _telefono, String _saldo, int _codigoCliente){

        clsCliente mEstudiante = buscarCliente(_codigoCliente);
        realm.beginTransaction();
        mEstudiante.setNombre(_nombre);
        mEstudiante.setIdentificacion(_identificacion);
        mEstudiante.setCorreo(_correo);
        mEstudiante.setTelefono(_telefono);
        mEstudiante.setSaldo(_saldo);
        realm.commitTransaction();

    }

    public void borrarCliente(int _codigoCliente){

        clsCliente mCliente = buscarCliente(_codigoCliente);
        realm.beginTransaction();
        mCliente.deleteFromRealm();
        realm.commitTransaction();
    }

    public List<clsCliente> ListaClientes(){

        RealmResults<clsCliente> mResultado = realm.where(clsCliente.class).findAll();
        return realm.copyFromRealm(mResultado);
    }


    public clsCliente buscarCliente(int _codigoCliente){
        clsCliente mCliente = realm.where(clsCliente.class).equalTo("CodigoCliente",_codigoCliente).findFirst();
        return mCliente;

    }

    //se crea la funcion de auto incremento para los codigos de los clientes
    private final static int CalculaCodigoCliente(){
        Realm mrealm = Realm.getDefaultInstance();
        Number mCodigoActual = mrealm.where(clsCliente.class).max("CodigoCliente");
        int nextCodigo;
        if(mCodigoActual == null){
            nextCodigo = 1;
        }else{
            nextCodigo = mCodigoActual.intValue() +1;
        }
        return nextCodigo;
    }
}
