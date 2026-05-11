package recadapters

import com.example.pract15_1_neuimina.R

data class feel(val image:Int, val name_feel:String)
class MyFeel{val list = arrayListOf(feel(R.drawable.`in`, ""),
    feel(R.drawable.relax,"Расслабленным"),
    feel(R.drawable.focus, "Сосредоточеным"),
    feel(R.drawable.anxious,"Взволнованным")
    )
}
