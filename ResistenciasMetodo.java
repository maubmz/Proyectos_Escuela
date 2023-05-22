package ProyectoChris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResistenciasMetodo {
    public static void main(String[] args) {
        String[] colores = { "negro", "marron", "rojo", "naranja", "amarillo", "verde", "azul", "violeta", "gris","blanco"};
        String[] tolerancia = {"marron", "rojo", "verde", "azul", "violeta", "gris", "oro","plata"};
        Scanner consola = new Scanner(System.in);
        System.out.println("Cuantas resistencias tiene el circuito: ");
        int resistenciasCircuito = consola.nextInt();

        switch (resistenciasCircuito) {
            case 1:
                System.out.println("Una resistencia en el circuito");
                int valor1 = calcularValorResistencia(colores);//este metodo solo regresara el valor en ohmios de la resistencia
                double valorTolerancia1 = calcularTolerancia(tolerancia);
                System.out.println("El valor de la resistencia es: " + valor1 + " ohmios " + "la tolerancia es de: " + valorTolerancia1);
                break;
            case 2:
                System.out.println("Dos resistencias en el circuito");
                ArrayList<Double> resistencias = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    int valorResistencia = calcularValorResistencia(colores);//este metodo solo regresara el valor en ohmios de la resistencia
                    double valorToleranciaResistencia = calcularTolerancia(tolerancia);
                    System.out.println("El valor de la " + (i + 1) + " resistencia es: " + valorResistencia + " ohmios " + "la tolerancia es de: " + valorToleranciaResistencia);
                    resistencias.add(Double.valueOf(String.valueOf(valorResistencia)));
                }
                System.out.println(resistencias.size());//Muestra si se guardaron las resistencias
                System.out.println("Las resistencias estan en: " + "\n1.Serie" + "\n2.Paralelo");
                int sumaResistencias = consola.nextInt();
                if (sumaResistencias == 1) {
                    sumarSerieResistencias(resistencias);
                } else {
                    sumarParaleloResistencias(resistencias);
                }
                break;
        }
        System.out.println("Quieres calcular incertidumbre? \n1.Si \nNo");
        int confirmacion = consola.nextInt();
        if (confirmacion == 1 ){
            System.out.println("Cuantas resistencias tiene la incertidumbre?:");
            int datos = consola.nextInt();
            ArrayList<Double> datosResistencias = new ArrayList<>();
            for (int i = 0; i < datos; i++){
                System.out.println("Dame el valor de la resistencia?:");
                Double valorResistencia = consola.nextDouble();
                datosResistencias.add(valorResistencia);
            }
            Double incertidumbre = calcularIncertidumbre(datosResistencias);
            System.out.println("Incertidumbre: " + incertidumbre);
        }

    }
    public static Double calcularIncertidumbre(ArrayList<Double> datos) {
        int n = datos.size();

        // Calcula la media
        double sum = 0;
        for (double dato : datos) {
            sum += dato;
        }
        double media = sum / n;

        // Calcula la suma de los cuadrados de las diferencias con la media
        double sumCuadradosDiferencias = 0;
        for (double dato : datos) {
            double diferencia = dato - media;
            sumCuadradosDiferencias += diferencia * diferencia;
        }

        // Calcula la desviación estándar (raíz cuadrada de la varianza)
        double varianza = sumCuadradosDiferencias / n;
        double desviacionEstandar = Math.sqrt(varianza);

        // Retorna la incertidumbre (desviación estándar)
        return desviacionEstandar;
    }

    public static void sumarSerieResistencias(ArrayList<Double> resistencias) {
        Double resistenciaTotal = 0.0;
        for (Double resistencia : resistencias) {
            resistenciaTotal += resistencia;
        }
        System.out.println("La resistencia total es de: " + resistenciaTotal);
    }

    public static void sumarParaleloResistencias(ArrayList<Double> resistencias) {
        Double resistenciaTotal = 0.0;
        for (Double resistencia : resistencias) {
            resistenciaTotal += 1 / resistencia;
        }
        resistenciaTotal = 1 / resistenciaTotal;
        System.out.println("La resistencia total es de: " + resistenciaTotal);
    }

    // Mapa que contiene los valores de las bandas
    public static final Map<String, Integer> bandaValores = new HashMap<>();
    static {
        bandaValores.put("negro", 0);
        bandaValores.put("marron", 1);
        bandaValores.put("rojo", 2);
        bandaValores.put("naranja", 3);
        bandaValores.put("amarillo", 4);
        bandaValores.put("verde", 5);
        bandaValores.put("azul", 6);
        bandaValores.put("violeta", 7);
        bandaValores.put("gris", 8);
        bandaValores.put("blanco", 9);
    }
    public static final Map<String, Double> bandaTolerancia = new HashMap<>();
    static {
        bandaTolerancia.put("marron",1.0);
        bandaTolerancia.put("rojo", 2.0);
        bandaTolerancia.put("verde", 0.5);
        bandaTolerancia.put("azul", 0.25);
        bandaTolerancia.put("violeta", 0.1);
        bandaTolerancia.put("gris", 0.05);
        bandaTolerancia.put("oro", 5.0);
        bandaTolerancia.put("plata", 10.0);
    }

    public static void menuColores() {
        //El metodo solo imprime un menu en consola para que el usuario ingrese un numero y se seleccione el color
        String[] menuColores = { "1.negro", "2.marron", "3.rojo", "4.naranja", "5.amarillo", "6.verde", "7.azul", "8.violeta", "9.gris","10.blanco","11.plata", "12.dorado"};
        for (int i = 0; i < menuColores.length; i++){
            System.out.println(menuColores[i]);
        }
    }
    public static void menuColoresTolerancia() {
        //El metodo solo imprime un menu en consola para que el usuario ingrese un numero y se seleccione el color para la tolerancia de la resistencia
        String[] menuColoresTolerancia = {"1.marron", "2.rojo", "3.verde", "4.azul", "5.violeta", "6.gris", "7.oro","8.plata"};
        for (int i = 0; i < menuColoresTolerancia.length; i++) {
            System.out.println(menuColoresTolerancia[i]);
        }
    }

    public static int calcularValorResistencia(String[] colores) {
        //Este metodo nos hace el calculo de la resistencia
        int valor = 0;
        menuColores();//Imprime en consola el menu de las resistencias

        if (colores.length >= 10) {
            //Entra a la condicion si colores(12) es mayor o igual a 12 = true
            Scanner consola = new Scanner(System.in);
            //El scanner se implementa para que el usuario nos de un numero y ese sea el color de la banda

            /*El "-1" en cada uno de las variable color es para que se encuentre el valor en el arreglo
            por que los arreglos inician en 0
            */
            System.out.println("Dame el numero del primer color de la banda: ");
            String primerColor = colores[consola.nextInt()-1];
            System.out.println("Dame el numero del color de la segunda banda: ");
            String segundoColor = colores[consola.nextInt()-1];
            System.out.println("Dame el numero del color de la tercer banda: ");
            String multiplicadorColor = colores[consola.nextInt()-1];

            if (bandaValores.containsKey(primerColor) && bandaValores.containsKey(segundoColor)
                    && bandaValores.containsKey(multiplicadorColor)) {
                int primerDigito = bandaValores.get(primerColor);
                int segundoDigito = bandaValores.get(segundoColor);
                int multiplicador = (int) Math.pow(10, bandaValores.get(multiplicadorColor));

                valor = (primerDigito * 10 + segundoDigito) * multiplicador;
            }

        }

        return valor;
    }

    public static double calcularTolerancia(String[] tolerancia) {
        double toleranciaResistencia = 0;
        menuColoresTolerancia();
        if (tolerancia.length >= 8){
            Scanner consola = new Scanner(System.in);
            System.out.println("Dame el numero de la tolerancia: ");
            String toleranciaBanda = tolerancia[consola.nextInt()-1];
            toleranciaResistencia = bandaTolerancia.get(toleranciaBanda);
        }
        return toleranciaResistencia;
    }


}
