package pl.cloudmate.physicsquiz

class Question (question:String, answer:Boolean){
    val q = question
    val a = answer

}

class Questions() {
    val questionList : List<Question>

    init {
        questionList = listOf(
            Question("Woda zawsze zaczyna wrzeć w 100°C",false),
            Question("Jowisz jest większy od Saturna", true),
            Question("Promieniowanie Alfa jest promieniowaniem elektromagnetycznym", false),
            Question("Przyśpieszenie grawitacyjne Ziemi wynosi 9,801 m/s^2",false),
            Question("Przyśpieszenie grawitacyjne Księżyca wynosi 1,62 m/s^2", true),
            Question("Światło potrzebuje około 8 minut, żeby dotrzeć z Słońca do Ziemi",true),
            Question("Jowisz jest większy od Saturna", true),
            Question("Promieniowanie gamma jest promieniowaniem elektromagnetycznym",true),
            Question("Mars ma atmosferę", true),
            Question("Temperatura zamarazania wody jest zależna od ciśnienia",true),
        )

    }
}