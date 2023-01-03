public class CreditDetails {

    public static double oprocentowanieWSkaliRoku;
    public static int okresKredytowaniaWLatach;
    public static int liczbaRatDoKoncaKredytu;
    public static double aktualnaWartoscKredytu;
    public static double wysokoscRaty;

    public static double rata() {
        double q = 1 + (oprocentowanieWSkaliRoku / 12);
        double A = aktualnaWartoscKredytu;
        double o = liczbaRatDoKoncaKredytu;
        return wysokoscRaty = (A*(Math.pow(q, o))*(q-1))/((Math.pow(q, o))-1);
    }

    public static double odsetki () {
        return (aktualnaWartoscKredytu * oprocentowanieWSkaliRoku) / 12;
    }

}
