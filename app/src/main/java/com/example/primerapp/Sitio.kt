package com.example.primerapp

class Sitio{
    var id : String
    var nombre: String
    var informacion_general: String
    var ubicacion: String
    var temperatura: String
    var sitios_recomendados: String
    var image: String = ""
    var pais: String

    constructor(id:String,nombre:String,informacion_general:String,ubicacion:String,temperatura:String,sitios_recomendados:String,image:String,pais:String){
        this.id=id
        this.nombre = nombre
        this.informacion_general=informacion_general
        this.ubicacion = ubicacion
        this.temperatura = temperatura
        this.sitios_recomendados = sitios_recomendados
        this.image = image
        this.pais = pais
    }

    override fun toString(): String {
        return "nombre: "+nombre+", informacion general: "+informacion_general
    }
}