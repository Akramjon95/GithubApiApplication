package maxcoders.uz.githubapplication.model

data class RecyclerList(val items: List<RecyclerData>)
data class RecyclerData(val name:String?, val languages_url:String?, val description: String?, val owmer:Owner?)
data class Owner(var avatar_url: String?)