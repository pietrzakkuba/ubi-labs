//https://youtu.be/F9UC9DY-vIU

fun sayHello(greeting: String, vararg itemsToGreet: String) {
    itemsToGreet.forEach { itemToGreet ->
        println("$greeting $itemToGreet")
    }
}


fun main() {
    val interestingThings = listOf("Kotlin", "Programming", "Comic Books")
    sayHello("Hi", "Kotlin")

    val person = Person()
    person.lastName
    person.firstName
    person.nickName = "Shades"
    person.printInfo()

    }