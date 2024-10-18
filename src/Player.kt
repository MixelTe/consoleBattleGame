class Player(val number: Int) {
    var hero: Human? = null
    val alive get() = hero?.alive ?: false

    private fun <T> select(fn: (String) -> T?): T {
        while (true) {
            val r = fn(readlnOrNull() ?: "")
            if (r != null) return r
            else println("# wrong input")
        }
    }

    fun selectHero() {
        println()
        println()
        println("! Player #$number")
        println("Choose your hero!")
        println("1 - Knight")
        println("2 - Robot")
        println("3 - Wizard")
        hero = select {
            when (it) {
                "1" -> Human.createHero(Heroes.Knight, 1)
                "2" -> Human.createHero(Heroes.Robot, 1)
                "3" -> Human.createHero(Heroes.Wizard, 1)
                else -> null
            }
        }
        println("# Player #$number is ${hero?.heroName}")
    }

    fun attack(p1: Player, p2: Player) {
        if (p1.alive && p2.alive) {
            println("/${"-".repeat(28)}\\")
            println("| Player #${p1.number}".padEnd(14) + "Player #${p2.number} |".padStart(16))
            println("| ${p1.hero?.heroName ?: "Ghost"}".padEnd(14) + "${p2.hero?.heroName ?: "Ghost"} |".padStart(16))
            println("| health: ${p1.hero?.health ?: 0}".padEnd(14) + "health: ${p2.hero?.health ?: 0} |".padStart(16))
            println("| ${p1.hero?.spStat ?: ""}".padEnd(14) + "${p2.hero?.spStat ?: ""} |".padStart(16))
            println("|            " + "You".padEnd(16) + "|")
            println("|        " + (hero?.heroName ?: "Ghost").padEnd(20) + "|")
            println("|        " + "health: ${hero?.health ?: 0}".padEnd(20) + "|")
            println("|        " + (hero?.spStat ?: "").padEnd(20) + "|")
            println("\\${"-".repeat(28)}/")
            println()
            println("1 -> attack Player #${p1.number}")
            println("2 -> attack Player #${p2.number}")
            println("3 -> heal")
            println("4 -> ${hero?.actionName}")
            select {
                when (it) {
                    "1" -> {
                        hero?.attack(p1.hero!!)
                        if (!p1.hero!!.alive)
                            println("Player #${p1.number} are defeated!")
                        Unit
                    }
                    "2" -> {
                        hero?.attack(p2.hero!!)
                        if (!p2.hero!!.alive)
                            println("Player #${p2.number} are defeated!")
                        Unit
                    }
                    "3" -> hero?.heal()
                    "4" -> hero?.action()
                    else -> null
                }
            }
        }
        else {
            val p = if (p1.alive) p1 else p2
            println("/${"-".repeat(15)}\\")
            println("| Player #${p.number}".padEnd(16) + "|")
            println("| ${p.hero?.heroName ?: "Ghost"}".padEnd(16) + "|")
            println("| health: ${p.hero?.health ?: 0}".padEnd(16) + "|")
            println("| ${p.hero?.spStat ?: ""}".padEnd(16) + "|")
            println("|" + "".padEnd(15) + "|")
            println("|      " + "You".padEnd(9) + "|")
            println("| ${hero?.heroName ?: " Ghost "}".padEnd(16) + "|")
            println("| health: ${hero?.health ?: 0}".padEnd(16) + "|")
            println("| ${hero?.spStat ?: ""}".padEnd(16) + "|")
            println("\\${"-".repeat(15)}/")
            println()
            println("1 -> attack Player #${p.number}")
            println("3 -> heal")
            println("4 -> ${hero?.actionName}")
            select {
                when (it) {
                    "1" -> {
                        hero?.attack(p.hero!!)
                        if (!p.hero!!.alive)
                            println("Player #${p.number} are defeated!")
                        Unit
                    }
                    "3" -> hero?.heal()
                    "4" -> hero?.action()
                    else -> null
                }
            }
        }
    }
}