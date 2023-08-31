package s5.coursJava.TP2a;

public class App {
    public static void main(String[] args) {
        Voiture v1 = new Voiture("noire", "Citroen", "C4", "Routiere");
        Voiture v2 = new Voiture("blanche", "Peugeot", "208 GTI", "Sportive");
        Voiture v3 = new Voiture("noire", "Renault", "Twingo", "Citadine");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        Moto m1 = new Moto("rouge", "Yamaha", "YBR", 125);
        Moto m2 = new Moto("blanche", "Suzuki", "RM-Z250", 225);
        Moto m3 = new Moto("noire", "Yamaha", "MT-03", 300);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
    }
}
