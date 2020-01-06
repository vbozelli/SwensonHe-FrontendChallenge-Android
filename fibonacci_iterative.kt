fun fibonacci_iterative(n: Int): Int {
    var a = 1
    var b = 1
    var i = 2
    var previousA: Int
    while (i <= n) {
        previousA = a
        a = a + b
        b = previousA
        i += 1
    }
    return a
}