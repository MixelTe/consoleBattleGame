fun main() {
    val players = listOf(Player(1), Player(2), Player(3))
    players.forEach { it.selectHero() }
    players[1].hero!!.health = 0

    var i = 0
    while (players.count { it.alive } != 1) {
        val player = players[i]
        if (player.alive) {
            println("!Turn of Player #${player.number}")
            player.attack(players[(i + 1) % 3], players[(i + 2) % 3])
            println("!End of turn")
            println()
            readlnOrNull()
            println()
        }
        i = (i + 1) % 3
    }
    val winner = players.first { it.alive }
    println("Player #${winner.number} has won!!!")
}