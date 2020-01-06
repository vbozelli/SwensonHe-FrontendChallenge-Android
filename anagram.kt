fun anagram(word1: String, word2: String): Boolean {
    val spaceRegex = "\\s".toRegex()
    val word1String = word1.replace(spaceRegex, "")
    val word2String = word2.replace(spaceRegex, "")
    val length1 = word1String.length
    val length2 = word2String.length
    if (length1 != length2) {
        return false
    }
    var i = 0
    val charactersDict1 = mutableMapOf<Char,Int>()
    while (i < length1) {
        val character = word1String[i]
        var quantity = charactersDict1[character]
        if (quantity == null) {
            quantity = 0
        }
        quantity += 1
        charactersDict1[character] = quantity
        i += 1
    }
    i = 0
    val charactersDict2 = mutableMapOf<Char,Int>()
    while (i < length2) {
        val character = word2String[i]
        var quantity = charactersDict2[character]
        if (quantity == null) {
            quantity = 0
        }
        quantity += 1
        charactersDict2[character] = quantity
        i += 1
    }
    if (charactersDict1.size != charactersDict2.size) {
        return false
    }
    return charactersDict1 == charactersDict2
}