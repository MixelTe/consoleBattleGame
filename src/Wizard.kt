import kotlin.random.Random

class Wizard(health: Int, power: Int): Human(health, power) {
    override val heroName = "Wizard"
    var mana = power
    override val spStat get() = "mana: $mana"
    override fun attack(opponent: Human) {
        if (mana <= 0) {
            println("no mana!")
            return
        }
        val pmana = mana
        mana--
        val dmg = (power * Random.nextDouble(1.0, 1.6)).toInt()
        println("attack by $dmg")
        println("mana $pmana -> $mana")
        opponent.damage(dmg)
    }

    override val actionName = "Restore mana"
    override fun action() {
        val pmana = mana
        mana += (power / 2).coerceAtLeast(2)
        println("mana restored: $pmana -> $mana")
    }
}