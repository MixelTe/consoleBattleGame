interface Alive {
    var health: Int
    fun heal()
    fun damage(power: Int)
    val alive get() = health > 0
}

enum class Heroes {
    Wizard, Robot, Knight
}

abstract class Human(val maxHealth: Int, val power: Int): Alive {
    abstract val heroName: String
    override var health = maxHealth
    abstract fun attack(opponent: Human)
    abstract fun action()
    abstract val actionName: String
    abstract val spStat: String
    override fun heal() {
        val phealth = health
        health = (health + power / 3 + 1).coerceAtMost(maxHealth)
        println("health: $phealth -> $health")
    }
    override fun damage(power: Int) {
        health = (health - power).coerceAtLeast(0)
    }

    companion object {
        fun createHero(type: Heroes, level: Int): Human {
            return when (type) {
                Heroes.Wizard -> Wizard(10 + level, 3 + level * 3)
                Heroes.Robot -> Robot(15 + level * 2, 2 + level * 2)
                Heroes.Knight -> Knight(20 + level * 3, 1 + level)
            }
        }
    }
}