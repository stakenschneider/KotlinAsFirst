@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.Integer.max

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> = text.toMutableSet()

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res: MutableMap<String, String> = mutableMapOf()
    res.putAll(mapA)

    for ((name, phone) in mapB) {
        if (mapA.containsKey(name) && phone != mapA[name]) {
            res[name] = mapA[name] + ", " + phone
        } else res[name] = phone
    }
    return res
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val res = mutableMapOf<Int, List<String>>()

    for ((_, grade) in grades) {
        res[grade] = listOf()
    }

    for ((grade, _) in res) {
        val names = mutableListOf<String>()
        for ((k, _) in grades) {
            if (grades[k] == grade) {
                names.add(k)
            }
        }
        res[grade] = names
    }

    return res
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = b + a == b

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
// TODO я бы переделала
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val res = mutableMapOf<String, Double>()
    for (i in stockPrices.indices) {
        if (!res.containsKey(stockPrices[i].first)) {
            var sum = stockPrices[i].second
            var count = 1
            for (k in i + 1 until stockPrices.size) {
                if (stockPrices[i].first == stockPrices[k].first) {
                    count++
                    sum += stockPrices[k].second
                }
            }
            res[stockPrices[i].first] = sum / count
        }
    }
    return res
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var res = ""
    var min = Double.MAX_VALUE

    if (kind.isEmpty()) return ""

    for ((name, pair) in stuff) {
        if (pair.first == kind && min > pair.second) {
            min = pair.second
            res = name
        }
    }
    return if (res == "") null else res
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *       )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", ""),
 *          "Sveta" to setOf(vasd),
 *          "Mikhail" to setOf("Sveta", "")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val res = mutableMapOf<String, Set<String>>()
    var allFriends: MutableSet<String>
    var listSet: MutableList<String>
    var newFriends = mutableMapOf<String, Set<String>>()
    newFriends.putAll(friends)

    for ((name, setOfFriend) in newFriends) { // проходим по всем именам в friends Map
        allFriends = setOfFriend.toMutableSet() // в множество добавляем первое рукопожатие
        listSet = setOfFriend.toMutableList() // превращаем множество в лист
        if (listSet.isEmpty()) res[name] = setOfFriend // часный случай
        for (i in 0 until listSet.size) { // по всем элементам листа
            if (friends.containsKey(listSet[i])) { // если friends Map содержит этого человека
                if (!allFriends.containsAll(newFriends.getValue(listSet[i]) - name)) {
                    res[name] = allFriends.union(newFriends.getValue(listSet[i]) - name) // объединяем множества - свое имя
                    newFriends[name] = allFriends.union(newFriends.getValue(listSet[i]) - name)
                    newFriends = propagateHandshakes(newFriends.toMap()).toMutableMap()
                    return newFriends
                } else {
                    res[name] = allFriends.union(newFriends.getValue(listSet[i]) - name) // объединяем множества - свое имя
                    newFriends[name] = allFriends.union(newFriends.getValue(listSet[i]) - name)
                }
            } else res[name] = allFriends // оставляем множество таким какое было
            if (!res.containsKey(listSet[i])) { // если у кого то есть друг, которого не было в мапе
                res[listSet[i]] = setOf() // добавляем его без списка друзей
            }
        }
    }

    return res
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for ((key, value) in b) {
        if (a.containsKey(key) && a[key] == value) {
            a.remove(key, value)
        }
    }
}


/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.toSet().intersect(b.toSet()).toList()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean =
        if (word.isEmpty()) true else chars.joinToString(separator = "").toLowerCase().toSet().containsAll(word.toLowerCase().toSet())

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val res = mutableMapOf<String, Int>()
    var count = 1

    for (i in list.indices) {
        if (!res.containsKey(list[i])) {
            for (k in i + 1 until list.size) {
                if (list[i] == list[k]) {
                    count++
                }
            }
            if (count > 1) {
                res[list[i]] = count
                count = 1
            }
        }
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (i in words.indices) {
        for (k in i + 1 until words.size) {
            if ((words[i].toLowerCase().toSet() - words[k].toLowerCase().toSet()).isEmpty() ||
                    (words[k].toLowerCase().toSet() - words[i].toLowerCase().toSet()).isEmpty())
                return true
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in list.indices) {
        for (k in i + 1 until list.size) {
            if (list[i] + list[k] == number)
                return Pair(i, k)
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val res = mutableSetOf<String>()
    val wightList = mutableListOf<Int>()
    val pricesList = mutableListOf<Int>()
    val treasuresList = mutableListOf<String>()

    val prices = Array(treasures.size + 1) { Array(capacity + 1) { 0 } }
    var tmp = capacity

    for ((key, value) in treasures) {
        treasuresList.add(key)
        wightList.add(value.first)
        pricesList.add(value.second)
    }

    for (i in 0 until treasures.size) {
        for (k in 0..capacity) {
            if (wightList[i] <= k) {
                prices[i][k] = max(prices[i][k], prices[i][k - wightList[i]] + pricesList[i])
            } else {
                prices[i][k] = prices[i][k]
            }
        }
    }

    for (i in treasures.size downTo 1) {
        if (prices[i][tmp] != prices[i - 1][tmp]) {
            res.add(treasuresList[i - 1])
            tmp -= wightList[i - 1]
        }
    }

    return res
}
