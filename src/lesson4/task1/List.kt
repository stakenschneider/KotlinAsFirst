@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.map { sqr(it) }.fold(0.0) { res, el -> res + el })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.fold(0.0) { res, el -> res + el } / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
//    круче, но возвращает копию листа :(((
//    if (list.isEmpty()) return mutableListOf()
//    return list.map { it - mean(list) }.toMutableList()
    if (list.isEmpty()) return mutableListOf()
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] = list[i] - mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var sum = 0.0
    for (i in a.indices) {
        sum += a[i] * b[i]
    }
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    var res = p[0]
    for (i in 1 until p.size) {
        res += p[i] * x.pow(i)
    }
    return res
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty())
        for (i in list.size - 1 downTo 1) {
            for (k in 0 until i) {
                list[i] += list[k]
            }
        }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var num = n
    var factor = 2
    while (num > 1) {
        if (num.rem(factor) == 0) {
            list.add(factor)
            num /= factor
        } else factor++
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var num = n
    if (n == 0) return listOf(0)

    while (num > 0) {
        list.add(num.rem(base))
        num /= base
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val lst = (convert(n, base))
    val buff = StringBuilder()
    for (element in lst) {
        if (element <= 9) {
            buff.append(element)
        } else buff.append('a' - 10 + element)
    }
    return buff.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val list = mutableListOf<String>()
    for (i in digits.indices) {
        list.add(convertToString(digits[i], base))
    }
    return Integer.parseInt(list.joinToString(separator = "") + "", base)
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = Integer.parseInt(str + "", base)

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val arab = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val rome = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val list = mutableListOf<String>()

    var num = n
    var i = rome.size - 1

    while (num > 0) {
        if (arab[i] > num) {
            i--
        } else {
            list.add(rome[i])
            num -= arab[i]
        }
    }
    return list.joinToString(separator = "")
}


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val res = mutableListOf<String>()

    if (digitNumber(n) > 3) {
        res.add(makeHundred(n.div(1000), true))
        res.add(getThousand(n.div(1000)))
        if (n.rem(1000) > 0) {
            res.add(makeHundred(n.rem(1000), false))
        }
    } else res.add(makeHundred(n, false))

    return res.joinToString(separator = " ")
}

fun getThousand(dig: Int): String {
    return if (dig.rem(10) == 1) "тысяча"
    else if (dig in 5..20) "тысячи"
    else if (dig.rem(10) == 2 || dig.rem(10) == 3 || dig.rem(10) == 4) "тысячи"
    else "тысяч"
}

fun makeHundred(n: Int, fem: Boolean): String {

    val res = mutableListOf<String>()
    var num = n
    var digit = digitNumber(n)

        if (digit == 3) {
            res.add(getHundreds(num.div(100)))
            num = num.rem(100)
            digit--
        }

        if (digit == 2 && num < 20 && num >= 10) {
            res.add(getTeens(num.rem(10)))
        }

        if (digit == 2 && num >= 20) {
            res.add(getDecades(num.div(10)))
            digit--
            num = num.rem(10)
        }

        if (num != 0 && (digit == 1 || (num < 10 && digit == 2))) {
            res.add(getUnits(num, fem))
        }
    return res.joinToString(separator = " ")
}

fun digitNumber(n: Int): Int = if (n == 0) 1 else ceil(log10(kotlin.math.abs(n) + 0.5)).toInt()

fun getHundreds(dig: Int): String = when (dig) {
    1 -> "сто"
    2 -> "двести"
    3 -> "триста"
    4 -> "четыреста"
    5 -> "пятьсот"
    6 -> "шестьсот"
    7 -> "семьсот"
    8 -> "восемьсот"
    9 -> "девятьсот"
    else -> ""
}

fun getDecades(dig: Int): String = when (dig) {
    1 -> "десять"
    2 -> "двадцать"
    3 -> "тридцать"
    4 -> "сорок"
    5 -> "пятьдесят"
    6 -> "шестьдесят"
    7 -> "семьдесят"
    8 -> "восемьдесят"
    9 -> "девяносто"
    else -> ""
}

fun getUnits(dig: Int, rang: Boolean): String = when (dig) {
    1 -> if (rang) "одна" else "один"
    2 -> if (rang) "две" else "два"
    3 -> "три"
    4 -> "четыре"
    5 -> "пять"
    6 -> "шесть"
    7 -> "семь"
    8 -> "восемь"
    9 -> "девять"
    else -> ""
}

fun getTeens(dig: Int): String {
    var s = ""
    when (dig) {
        1 -> s = "один"
        2 -> s = "две"
        3 -> s = "три"
        4 -> s = "четыр"
        5 -> s = "пят"
        6 -> s = "шест"
        7 -> s = "сем"
        8 -> s = "восем"
        9 -> s = "девят"
    }
    return s + "надцать"
}
