package pretest.app.tokoglints.behaviors

import pretest.app.tokoglints.db.models.Goods

interface MainActivityBehavior {
    fun showData(data: List<Goods>)
}