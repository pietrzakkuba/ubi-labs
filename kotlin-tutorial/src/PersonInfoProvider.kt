interface PersonInfoProvider {
    fun printInfo(person: Person)
}


 class BasicInfoProvider : PersonInfoProvider {
     override fun printInfo(person: Person) {
         println("basicInfoProvider")
         person.printInfo()
     }
 }

fun main() {

    BasicInfoProvider().printInfo(Person("Adam"))

}