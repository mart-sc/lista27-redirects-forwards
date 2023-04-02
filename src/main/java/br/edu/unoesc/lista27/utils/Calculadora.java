package br.edu.unoesc.lista27.utils;

public class Calculadora {
    
    public static double somar(double num1, double num2) {
        return num1 + num2;
    }
    
    public static double subtrair(double num1, double num2) {
        return num1 - num2;
    }
    
    public static double multiplicar(double num1, double num2) {
        return num1 * num2;
    }
    
    public static double dividir(double num1, double num2) throws ArithmeticException {
        if (num2 == 0.0) {
            throw new ArithmeticException("Divisão por zero");
        }
        return num1 / num2;
    }
    
    public static double media(double num1, double num2) {
        return (num1 + num2) / 2;
    }
    
    public static double potencia(double num1, double num2) {
        return Math.pow(num1, num2);
    }
    
    public static double raizQuadrada(double num) throws ArithmeticException {
        if (num < 0.0) {
            throw new ArithmeticException("Raiz quadrada de número negativo");
        }
        return Math.sqrt(num);
    }
    
}