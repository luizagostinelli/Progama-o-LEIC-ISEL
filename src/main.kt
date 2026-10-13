
const val MAX_ATTEMPTS = 6
const val WRONG = '_'
const val CORRECT = '+'
const val SWAPPED = '#'

fun main() {
    val secret = words.filter{ noRepeats(it) }.random()
    println("Adivinhar uma palavra com ${secret.length} letras em $MAX_ATTEMPTS tentativas.")
    var attempts = 1
    do {
        println("${attempts}ª tentativa")
        val guess: String = readGuess(secret.length)
        val result: String = getResult(secret, guess)
        println(guess)
        println(result)
        if (allCorrect(result)) break
        attempts++
    } while (attempts <= MAX_ATTEMPTS)

    val status = if (attempts > MAX_ATTEMPTS) "LOSE" else "WIN"
    println("YOU $status -> Word=$secret")
}

val words = listOf(
    "ARRAY", "CICLO", "DADOS", "DEBUG", "ERROS", "FLUXO", "LISTA", "PILHA",
    "TEXTO", "VALOR", "VETOR", "CHAVE", "CAMPO", "FONTE", "FORMA", "LINHA",
    "MUNDO", "PORTA", "PRAIA", "LIVRO", "CASAR", "PEDRA", "NUVEM", "SONHO",
    "TEMPO", "VERDE", "BANCO", "BEBER", "BRAVO", "CARRO", "COISA", "FOLHA",
    "GENTE", "GRUPO", "JOVEM", "MASSA", "NOITE", "PAPEL", "RAPAZ", "TERRA"
)


fun noRepeats(s: String): Boolean {
    for (i in 0..s.length - 1) {
        for (k in i + 1 until s.length) {
            if (s[i] == s[k]) return false
        }
    }
    return true
}

fun allCorrect(result: String): Boolean {
    for (i in 0..result.length - 1) {
        if (result[i] != CORRECT) return false
    }
    return true
}

fun readGuess(length: Int): String {
    var x: String
    do {
        print("Palavra: ")
        x = readln().uppercase()
        val valid = x.length == length && x.all { it in 'A'..'Z' }
        if (!valid) println("Palavra inválida.")
    } while (!valid)
    return x
}


fun getResult(secret: String, guess: String): String {
    var result = ""
    for (i in 0..guess.length - 1) {
        if (guess[i] == secret[i]) {
            result += CORRECT
        } else if (guess[i] in secret) {
            result += SWAPPED
        } else {
            result += WRONG
        }
    }
    return result
}

