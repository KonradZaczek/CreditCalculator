import java.util.*;


public class Main {

    private static final String SZCZEGOLY = "s";
    private static final String SKLADOWE_RATY = "r";
    private static final String TABELA_RAT = "t";
    private static final String OPROCENTOWANIE = "%";
    private static final String NADPLATA = "n";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Map<String, String> allValues = new LinkedHashMap<>();

        System.out.print("Podaj aktualne oprocentowanie w %: ");
        CreditDetails.oprocentowanieWSkaliRoku = input.nextDouble();
        allValues.put("Oprocentowanie w skali roku", CreditDetails.oprocentowanieWSkaliRoku + " %");
        CreditDetails.oprocentowanieWSkaliRoku = CreditDetails.oprocentowanieWSkaliRoku / 100;

        System.out.print("Podaj okres kredytowania w latach: ");
        CreditDetails.okresKredytowaniaWLatach = input.nextInt();
        allValues.put("Okres kredytowania", CreditDetails.okresKredytowaniaWLatach + " lat");

        System.out.print("Podaj liczbę rat pozostałych do spłaty kredytu: ");
        CreditDetails.liczbaRatDoKoncaKredytu = input.nextInt();
        allValues.put("Liczba rat do końca kredytu", String.valueOf(CreditDetails.liczbaRatDoKoncaKredytu));

        System.out.print("Podaj aktualną wartość kredytu: ");
        CreditDetails.aktualnaWartoscKredytu = input.nextDouble();
        allValues.put("Aktualna wartość kredytu", CreditDetails.aktualnaWartoscKredytu + " zł");

        allValues.put("Aktualna wysokość raty: ", String.format("%.2f zł", CreditDetails.rata()));


        while (true) {
            System.out.print("-------------------------------\n" +
                    "FUNKCJE: \n" +
                    "1) Wpisz [s] jeśli chcesz wyświetlić szczegóły kredytu\n" +
                    "2) Wpisz [r] jeśli chcesz wyświetlić składowe obecnej raty\n" +
                    "3) Wpisz [t] jeśli chcesz wyświetlić szczegółową tablę spłaty kredytu\n" +
                    "4) Wpisz [n] jeśli chcesz zasymulować nadpłatę kredytu\n" +
                    "5) Wpisz [%] jeśli chcesz zmienić oprocentowanie\n" +
                    ": ");
            String function = input.next();
            System.out.print("-------------------------------");

            if (function.equals(SZCZEGOLY)) {
                System.out.println("\nSZCZEGÓŁY KREDYTU:");
                allValues.forEach((key, value) -> System.out.printf("- %s: %s%n", key, value));
            }

            if (function.equals(SKLADOWE_RATY)) {
                System.out.printf("\nAktualna wysokość raty: %.2f zł%n", CreditDetails.rata());
                System.out.printf("Kwota raty odsetek: %.2f zł%n", CreditDetails.odsetki());
                System.out.printf("Kwota raty kapitału: %.2f zł%n", CreditDetails.rata() - CreditDetails.odsetki());
            }

            if (function.equals(OPROCENTOWANIE)) {
                System.out.print("\nPodaj nowe oprocentowanie: ");
                CreditDetails.oprocentowanieWSkaliRoku = input.nextDouble() / 100;
                allValues.put("Aktualna wysokość raty: ", String.format("%.2f zł", CreditDetails.rata()));
                allValues.put("Oprocentowanie w skali roku", String.format("%.2f %s", CreditDetails.oprocentowanieWSkaliRoku, "%"));

            }

            if (function.equals(NADPLATA)) {
                System.out.print("\nPodaj kwotę nadpłaty: ");
                int kwotaNadplaty = input.nextInt();
                CreditDetails.aktualnaWartoscKredytu -= (double) kwotaNadplaty;
                allValues.put("Aktualna wysokość raty: ", String.format("%.2f zł", CreditDetails.rata()));
                allValues.put("Aktualna wartość kredytu", String.valueOf(CreditDetails.aktualnaWartoscKredytu));

            }

            if (function.equals(TABELA_RAT)) {
                int liczbaRat = CreditDetails.liczbaRatDoKoncaKredytu;
                double wysokoscRatyStalej = CreditDetails.rata();
                double sumaSplaconegoKapitalu = 0;

                System.out.printf("%n| %3s | %20s | %14s | %15s | %25s | %15s |",
                        "Lp", "Wysokość raty stałej", "Rata odsetkowa", "Rata kapitałowa", "Suma spłaconego kapitału", "Wartość kredytu");

                for (int i = 1; i <= liczbaRat; i++) {
                    double rataOdsetkowa = CreditDetails.odsetki();
                    double rataKapitalowa = wysokoscRatyStalej - rataOdsetkowa;
                    sumaSplaconegoKapitalu += rataKapitalowa;
                    double nowaWartoscKredytu = CreditDetails.aktualnaWartoscKredytu - rataKapitalowa;
                    CreditDetails.aktualnaWartoscKredytu = nowaWartoscKredytu;

                    System.out.printf("%n| %3d | %17.2f zł | %11.2f zł | %12.2f zł | %22.2f zł | %12.2f zł |",
                            i, wysokoscRatyStalej, rataOdsetkowa, rataKapitalowa, sumaSplaconegoKapitalu, nowaWartoscKredytu);
                }
                System.out.println();
            }

        }
    }
}


