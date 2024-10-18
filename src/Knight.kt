import kotlin.random.Random

class Knight(health: Int, power: Int): Human(health, power) {
    override val heroName = "Knight"
    var defence = (power / 2).coerceAtLeast(2)
    override val spStat get() = "defence: $defence"

    override fun damage(power: Int) {
        val p = (power - defence).coerceAtLeast(0)
        val pdefence = defence
        defence = (defence - power).coerceAtLeast(0)
        if (p != power)
            println("blocked ${power - p} damage (defence: $pdefence -> $defence)")
        super.damage(p)
    }

    override fun attack(opponent: Human) {
        val dmg = (power * Random.nextDouble(0.4, 1.2)).toInt()
        println("attack by $dmg")
        opponent.damage(dmg)
    }

    override val actionName = "Set defence"
    override fun action() {
        val pdefence = defence
        defence += (power / 2).coerceAtLeast(2)
        println("defence set: $pdefence -> $defence")
    }
}