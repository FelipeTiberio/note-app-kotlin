package com.imd.atividade

import java.io.Serializable

data class Note(var title : String, var  text : String): Serializable{
    var id : Long? = null
}