/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.atena.tecnologies.bandasucuzu;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import com.mongodb.MongoClient;
public class BandaSucuzu {

    public static void main(String[] args) {
        
        MongoClientURI uri = crearConexion();
        Scanner teclado = new Scanner(System.in);
        
        try(MongoClient mongoClient = new MongoClient(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("sucuzu");
            
//             Ingresar integrantes
//            mostrarColeccion(database, "integrantes");
//            System.out.println("Nombre del integrante");
//            String nombre = teclado.nextLine();
//            System.out.println("Apellido del integrante");
//            String apellido = teclado.nextLine();
//            System.out.println("Instrumento del integrante");
//            String edad = teclado.nextLine();
//            insertarIntegrante(database, "integrantes", nombre, apellido, edad);
//            mostrarColeccion(database, "integrantes");

//            buscar
//            mostrarColeccion(database, "integrantes");
//            System.out.println("Que integrante esta buscando?");            
//            String integrante = teclado.nextLine();
//            buscarPorNombre(database, "integrantes", integrante);
//            
////            Eliminar integrantes
//            mostrarColeccion(database, "integrantes");
//            System.out.println("Integrante que se va a eliminar");
//            String eliminarIntegrantes = teclado.nextLine();
//            eliminarIntegrante(database, "integrantes", eliminarIntegrantes);
//            mostrarColeccion(database, "integrantes");
//            
////            Actualizar integrantes
//                mostrarColeccion(database, "integrantes");
//                System.out.println("Integrante que se va a actualizar");
//                String integrante= teclado.nextLine();
//                System.out.println("Ingrese el nuevo Instrumento");
//                String actualizaInstrumento= teclado.nextLine();
//                actualizarInstrumento(database, "integrantes", integrante, actualizaInstrumento);
//                mostrarColeccion(database, "integrantes");

        } catch (Exception e)
        {   
            System.out.println(e);
        }
    }
    
    
    // METODO PARA CREAR LA CONEXION A MONGODB
    public static MongoClientURI crearConexion() {
        
        MongoClientURI uri = new MongoClientURI("mongodb+srv://loncho:sucuzu@sucuzu.gtn9gid.mongodb.net/?retryWrites=true&w=majority");
                System.out.println("Comprobando conexion");
        return uri;
    }
    // MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS
    public static void mostrarColeccion(MongoDatabase database, String coleccion) {
        MongoCollection<Document> colec = database.getCollection(coleccion);
        
        MongoCursor<Document> document = colec.find().iterator();
        
         while(document.hasNext()){
                ArrayList<Object> veri = new ArrayList(document.next().values());
                for (int i = 0; i < veri.size(); i++)
                {
                System.out.println(veri.get(i));    
                }
            }
    }
    
    // METODO PARA INSERTAR UN DOCUMENTO (REGISTRO)
    public static void insertarIntegrante(MongoDatabase database, String coleccion, String nombre, String apellido, String instrumento) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        
        Document documento = new Document();
        documento.put("nombre", nombre);
        documento.put("apellido", apellido);
        documento.put("instrumento", instrumento);
        collection.insertOne(documento);
        
    }
               
    // MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS QUE COINCIDAN CON EL NOMBRE
    public static void buscarPorNombre(MongoDatabase database, String coleccion, String nombre) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        Document consulta = new Document();
        consulta.put("nombre", nombre);
        
        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        MongoCursor<Document> cursor = collection.find(consulta).iterator();
        while(cursor.hasNext()) {
            System.out.println("-- " + cursor.next().get("instrumento"));
        }
    }
    
    // METODO PARA ACTUALIZAR UN DOCUMENTO (REGISTRO)
    public static void actualizarInstrumento(MongoDatabase database, String coleccion, String nombre, String instrumento) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // SENTENCIA CON LA INFORMACION A REMPLAZAR
        Document actualizaInstrumento = new Document();
        actualizaInstrumento.append("$set", new Document().append("instrumento", instrumento));
        
        // BUSCA EL DOCUMENTO EN LA COLECCION
        Document buscarPorNombre = new Document();
        buscarPorNombre.append("nombre", nombre);
        
        // REALIZA EL UPDATE
        collection.updateOne(buscarPorNombre, actualizaInstrumento);
    }
    
    // METODO PARA ELIMINAR UN DOCUMENTO (REGISTRO)
    public static void eliminarIntegrante(MongoDatabase database, String coleccion, String nombre) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        collection.deleteOne(new Document().append("nombre", nombre));
    }
}
