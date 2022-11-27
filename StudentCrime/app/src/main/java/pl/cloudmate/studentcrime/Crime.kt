package pl.cloudmate.studentcrime



class Crime (
    val index: Int,
    val stub: String,
    val desc: String,
    val timestamp: Int,
    val isSolved: Boolean)


class Crimes() {
    val crimeList : List<Crime>

    init {
        crimeList = List(20) {
            Crime(
                477456+it,
                "Short desc of $it",
                "Long description of incident no. $it",
                1668959398+3600*it,
                it%3 == 1
            )
        }

    }
}