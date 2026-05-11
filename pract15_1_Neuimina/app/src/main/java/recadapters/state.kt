package recadapters

import com.example.pract15_1_neuimina.R

data class state(val title:String, val text_state:String, val image_state:Int)
class MyState
{
    val state_list= arrayListOf(state("Заголовок блока", "Кратенькое описание блока с двумя строчками", R.drawable.blockone),
        state("Заголовок блока", "Кратенькое описание блока с двумя строчками", R.drawable.blocktwo))
}
