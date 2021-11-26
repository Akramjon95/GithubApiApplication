package maxcoders.uz.githubapplication.model

import java.io.Serializable

data class Item(
    val id: Long? = null,
    val name:String? = null,
    val owner:Owner? = null,
    val language:String? = null,
    val description:String? = null,
    val full_name:String? = null
):Serializable