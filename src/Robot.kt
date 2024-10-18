import kotlin.random.Random

class Robot(health: Int, power: Int) : Human(health, power) {
    override val heroName = "Robot"
    var battery = power
    override val spStat get() = "battery: $battery"

    override fun damage(power: Int) {
        var p = power
        if (battery >= 2) {
            p = (power - (this.power / 2).coerceAtLeast(1)).coerceAtLeast(0)
            val pbattery = battery
            battery -= 2
            println("blocked ${power - p} damage (battery: $pbattery -> $battery)")
        }
        super.damage(p)
    }

    override fun attack(opponent: Human) {
        if (battery <= 0) {
            println("no energy!")
            return
        }
        val pbattery = battery
        battery--
        val dmg = (power * Random.nextDouble(0.4, 1.2)).toInt()
        println("attack by $dmg")
        println("battery $pbattery -> $battery")
        opponent.damage(dmg)
    }

    override val actionName = "Charge battery"
    override fun action() {
        val pbattery = battery
        battery += (power / 2).coerceAtLeast(2)
        println("battery charged: $pbattery -> $battery")
    }
}