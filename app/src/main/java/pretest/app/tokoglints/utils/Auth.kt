package pretest.app.tokoglints.utils

object Auth {
    private val users = listOf(
        mapOf("username" to "user1", "password" to "user1", "access" to "write"),
        mapOf("username" to "user2", "password" to "user2", "access" to "read"),
        mapOf("username" to "user3", "password" to "user3", "access" to "read"),
        mapOf("username" to "user4", "password" to "user4", "access" to "read"),
        mapOf("username" to "user5", "password" to "user5", "access" to "read"),
        mapOf("username" to "user6", "password" to "user6", "access" to "read")
    )

    fun login(username: String, password: String): Boolean {
        val found = users.filter {
            it["username"].equals(username).and(it["password"].equals(password))
        }
        return found.size == 1
    }

    fun getUser(username: String) = users.find { it["username"] == username }

}