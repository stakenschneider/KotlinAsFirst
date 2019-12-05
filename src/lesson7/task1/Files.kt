@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import java.io.File.separator
import java.lang.Integer.*
import kotlin.math.pow


/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val testString = File(inputName).readLines().joinToString(separator = "")
    val res = mutableMapOf<String,Int>()

    for (i in substrings.indices){
        val replaceAllCase2: Int = testString.length - testString.replace(substrings[i], "",true).length
        res[substrings[i]] = replaceAllCase2.div(substrings[i].length)
    }

    return res
}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
// возвращает то что вычитают
fun deductible(div: Int, rhv: Int): MutableList<Int> {
    val deductible = mutableListOf<Int>()
    val divList = mutableListOf<Int>()

    for (element in div.toString().toList()) {
        divList.add(parseInt(element.toString()))
    }

    for (element in div.toString()) {
        deductible.add(parseInt(element.toString()) * rhv)
    }
    return deductible
}

fun residualStr(a: String, b: Int): String {
    val countOfB = b.toString().length
    val countOfA = a.length

    val res = a.substring(countOfB, countOfA)

    if (parseInt(a.substring(0, countOfB)) - b != 0)
        return (parseInt(a) - b * 10.0.pow(countOfA - countOfB).toInt()).toString()

    if (countOfA <= countOfB) {
        return a
    }

    return res
}

fun getResidual(a: String, b: Int, c: Int, d: Int): String {
    var res = "0"

    if (c - d == 1) {
        res = a
    } else if (c != d) {
        if (a.substring(0, 1) == "0") return a.substring(1, b.toString().length + 1)
        res = a.substring(0, (b * 10).toString().length)
    }

    return (parseInt(res) - b * 10).toString()
}

fun printDeductible(i: Int, ded: Int, str1: String, div: Int, tab3: Int): String = if (i == 0) { // если это первое вычитаемое то пищем еще и результат от деления
    "-" + ded.toString() +
            MutableList(str1.length - ded.toString().length - 1) { " " }.joinToString(separator = "") +
            div + "\n"
} else {
    MutableList(tab3) { " " }.joinToString(separator = "") +
            "-" +
            ded + "\n"
}


fun printLine(i: Int, tab3: Int, tab2: Int, ded: Int, resid: MutableList<Int>): String = if (i != 0) {
    MutableList(min(tab3, tab2)) { " " }.joinToString(separator = "") +
            MutableList(max(ded.toString().length + 1, resid[i - 1].toString().length)) { "-" }.joinToString(separator = "") + "\n"
} else {
    MutableList(min(tab3, tab2)) { " " }.joinToString(separator = "") +
            MutableList(ded.toString().length + 1) { "-" }.joinToString(separator = "") + "\n"
}


fun printResidual(i: Int, deductibleList: MutableList<Int>,
                  residualList: MutableList<Int>, lhv: Int, rhv: Int, tab1: Int, tab2: Int, tab3: Int): Pair<String,Int> {
    var res: Int
    if (i != deductibleList.size - 1) { //все кроме последнего
        if (i != 0 && deductibleList[i] == residualList[i - 1] ||
                (i == 0 && lhv.toString().substring(0, deductibleList[0].toString().length).toInt() == deductibleList[0])) //если это первый после черты ноль
        { //когда надо приписать лишний ноль
            return Pair(MutableList(tab1 - 1) { " " }.joinToString(separator = "") +
                    "0" +
                    residualList[i] + "\n",tab1)
        } else {
            res = if (deductibleList[i] == 0) { //если вычитаемое было ноль то позицию не меняем
                tab2
            } else tab1
            return Pair(MutableList(res) { " " }.joinToString(separator = "") +
                    residualList[i] + "\n",res)
        }
    } else {//отдельно для последнего
        return if (lhv <= rhv) { // 0 когда делитель больше делимого
            Pair(MutableList(tab3 + deductibleList[i].toString().length) { " " }.joinToString(separator = "") +
                    residualList[i] + "\n",tab1)
        } else {
            if (i == 0) { //если был всего лишь один элемент
                Pair(MutableList(deductibleList[i].toString().length - residualList[i].toString().length + 1) { " " }.joinToString(separator = "") +
                        residualList[i] + "\n",tab1)
            } else {
                Pair(MutableList(residualList[i - 1].toString().length - residualList[i].toString().length + tab2) { " " }.joinToString(separator = "") +
                        residualList[i] + "\n",tab1)
            }
        }
    }
}

fun print(lhv: Int,rhv: Int,deductibleList: MutableList<Int>,residualList: MutableList<Int>): String{
    val div = lhv.div(rhv)

    var tab1 = 1 // расстоение до остатка residualList
    var tab2 = 0 // tab1 с прошлой итерации
    var tab3: Int // расстояние до вычитаемоего deductibleList

    var resultString: String
    var str1 = " $lhv | "
    var str2 = "$rhv\n"

//    если первое вычитаемое меньше по количеству цифр
    if (parseInt(lhv.toString().substring(0, deductibleList[0].toString().length)) < deductibleList[0]) {
        str1 = "$lhv | "
        str2 = "$rhv\n"
        tab1--
    }

    resultString = str1 + str2
    for (i in 0 until deductibleList.size) {

        tab1 += deductibleList[i].toString().length - residualList[i].toString().length + 1
        tab3 = if (i != 0) {
            tab2 + residualList[i - 1].toString().length - deductibleList[i].toString().length - 1 //минус один = знак минуса
        } else {
            tab2
        }

        if (i != 0 && deductibleList[i] == 0 && residualList[i - 1] != 0) {
            tab1 = tab3
        }

        resultString += printDeductible(i, deductibleList[i], str1, div, tab3)
        resultString += printLine(i, tab3, tab2, deductibleList[i], residualList)
        val k = printResidual(i, deductibleList, residualList, lhv, rhv, tab1, tab2, tab3)
        resultString += k.first
        tab1 = k.second
        tab2 = tab1
    }

    return resultString
}

fun residual(lhv: Int,rhv: Int,deductibleList: MutableList<Int>): MutableList<Int>{
    var number = lhv.toString()
    val residualList = mutableListOf<Int>()

    for (i in 0 until deductibleList.size) {
        val residual = getResidual(number, deductibleList[i], deductibleList.size - 1, i)
        if (i != deductibleList.size - 1) {
            residualList.add(parseInt(residual))
        }
        number = residualStr(number, deductibleList[i])
    }
    residualList.add(lhv.rem(rhv)) //остаток от деления

    return residualList
}

fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    val deductibleList = deductible(lhv.div(rhv), rhv)
    val residualList = residual(lhv,rhv,deductibleList)

    val inputFile = File(outputName).bufferedWriter()
    inputFile.write(print(lhv, rhv, deductibleList, residualList))
    inputFile.close()
}
