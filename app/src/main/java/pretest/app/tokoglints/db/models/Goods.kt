package pretest.app.tokoglints.db.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
data class Goods(
    @Id var id: Long = 0,
    var name: String = "",
    var qty: Int = 0,
    var supplier: String = "",
    var date: Date? = null
)