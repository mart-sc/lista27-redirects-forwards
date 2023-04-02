package br.edu.unoesc.lista27.utils;

public class ConversorNumerico {
    
    public static boolean ehNumerico(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str.replace(",", "."));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static double converteParaDouble(String str) {
        return Double.parseDouble(str.replace(",", "."));
    }
    
}