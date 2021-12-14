Zadanie 1: Pokrycie zbioru
Wprowadzenie

Pokrycie zbioru to przykład problemu optymalizacyjnego .

Instancją problemu jest zbiór Z i rodzina zbiorów R, numerowanych kolejnymi liczbami całkowitymi od 1.

Rozwiązaniem problemu jest pokrycie zbioru Z rodziną R, czyli wybór z rodziny R jak najmniejszej liczby zbiorów tak, by ich suma teoriomnogościowa była nadzbiorem zbioru Z.

Reprezentacją rozwiązania jest, uporządkowany rosnąco, ciąg numerów wybranych zbiorów.

Znalezienie rozwiązania optymalnego, czyli takiego, którego reprezentacja jest najkrótsza, wymaga zastosowania kosztownego algorytmu siłowego, opartego na metodzie prób i błędów.

Dla ustalenia uwagi przyjmujemy, że algorytm ten, dalej nazywany algorytmem dokładnym, daje to spośród rozwiązań optymalnych, którego reprezentacja jest pierwsza w porządku leksykograficznym .

Jeśli rozwiązanie optymalne nie jest wymagane, to zamiast algorytmu dokładnego możemy zastosować heurystykę .

    Heurystyka naiwna rozważa zbiory rodziny R w kolejności rosnących numerów. Wybiera aktualny zbiór jeśli należy do niego jakiś element zbioru Z, który nie należy do żadnego z dotąd wybranych zbiorów.

    Heurystyka zachłanna w kolejnych krokach wybiera z rodziny R zbiór, w którym jest największa liczba elementów zbioru Z nie należących do żadnego z dotąd wybranych zbiorów. Jeśli warunek ten spełnia wiele zbiorów, to wybierany jest spośród nich zbiór o najmniejszym numerze.

Polecenie

Napisz w Javie program, który rozwiązuje problem pokrycia zbioru.

Zaimplementuj algorytm dokładny oraz heurystykę naiwną i heurystykę zachłanną.

Klasę wykonywalną programu, o nazwie Main, umieść w pakiecie cover.
Dane

Na standardowym wejściu programu jest ciąg wierszy ze spacjami i liczbami całkowitymi zapisanymi dziesiętnie.

Spacje i końce wiersza nie są znaczące. Dane programu to ciąg liczb całkowitych.

Składnię danych określa poniższa gramatyka bezkontekstowa, w rozszerzonej notacji BNF , z symbolem początkowym Dane:

Dane ::= { Zbiór | Zapytanie }
Zbiór ::= { Składnik } 0
Składnik ::= Element | Nieskończony | Skończony
Element ::= d
Nieskończony ::= d u
Skończony ::= d u u
Zapytanie ::= u d

W gramatyce nawiasy klamrowe oznaczają powtórzenie zero lub więcej razy a kreska pionowa oznacza alternatywę.

Symbol końcowy d reprezentuje dodatnią, czyli większą od zera, liczbę całkowitą. Symbol końcowy u reprezentuje ujemną liczbę całkowitą.

Dodatkowo wymagamy, by symbol d po prawej stronie produkcji symbolu Zapytanie reprezentował liczbę 1, 2 lub 3.

Ciąg liczb wyprowadzalny z symbolu Zbiór określa element rodziny zbiorów R.

Zbiory rodziny R są numerowane w kolejności wystąpienia w danych.

Zawartość zbioru jest sumą składników w jednej z poniższych postaci:

    a

    jedynym elementem składnika jest liczba a,

    a b

    składnik zawiera wszystkie wyrazy nieskończonego ciągu arytmetycznego, zaczynającego się od a, w którym każdy kolejny wyraz jest większy od poprzedniego o -b,

    a b c

    składnik zawiera wszystkie, nie przekraczające -c, wyrazy ciągu arytmetycznego, zaczynającego się od a, w którym każdy kolejny wyraz jest większy od poprzedniego o -b.

Para liczb a b, wyprowadzalna z symbolu Zapytanie, określa instancję problemu pokrycia zbioru i sposób rozwiązania.

Instancją problemu jest zbiór Z zawierający wszystkie liczby całkowite od 1 do -a i rodzina R wszystkich zbiorów, wyprowadzalnych z symbolu Zbiór, które wystąpiły w danych przed tym zapytaniem.

Liczba b wskazuje sposób rozwiązania problemu:

    3

    heurystyka naiwna,

    2

    heurystyka zachłanna,

    1

    algorytm dokładny.

Wynik

Program pisze na standardowe wyjście wynik w postaci ciągu wierszy, po jednym dla każdego zapytania.

W wierszu wyniku są spacje oraz zapisane dziesiętnie liczby całkowite.

Między każdą parą liczb sąsiadujących w wierszu jest dokładnie jedna spacja.

Nie ma spacji ani na początku ani na końcu wiersza.

Każdy wiersz jest zakończony reprezentacją końca wiersza.

Jeśli problem, którego dotyczy zapytanie, ma rozwiązanie, to w wierszu jest reprezentacja rozwiązania, w przeciwnym przypadku jest tam liczba 0.
Przykład

Dla danych z pliku przykład.in :

2 0

   1      0 1

0 -3
1
0
2 3 2 0
-3 3
-3 2
-3 1
4 -1 -5 1000000000 0
2 -2 0
-6 3
-6 2
-6 1
1 6 0
-6 3
-6 2
-6 1

poprawnym wynikiem jest zawartość pliku przykład.out :

0
1 2 5
2 5
2 5
1 2 5 6 7
2 5 6 7
2 5 6 7
1 2 5 6 7
2 5 6 7
5 6 8

Uwagi

    Wolno założyć, że dane są poprawne.

    Wolno założyć, że wszystkie dane liczby mieszczą się w zakresie typu int.

    Efektywność rozwiązania nie będzie miała istotnego wpływu na ocenę. Należy jednak zadbać, by implementacja heurystyk miała koszt wielomianowy względem rozmiaru danych. Algorytm dokładny będzie miał koszt wykładniczy.

    Rozwiązanie "wzorcowe" wykonuje na students powyższy przykład w 0.6 sekundy. Dla porównania, program pusty wykonuje się 0.3 sekundy.

    Wolno korzystać ze wszystkiego, co jest w standardowej bibliotece klas Javy. Nie wolno korzystać z żadnych innych bibliotek.

    Rozwiązanie powinno się kompilować na students kompilatorem Javy 8 /usr/bin/javac lub Javy 11 /opt/jdk-11.0.2/bin/javac.

    Jako rozwiązanie należy wysłać archiwum .tar.gz, .zip lub .jar, zawierające kod źródłowy programu.


