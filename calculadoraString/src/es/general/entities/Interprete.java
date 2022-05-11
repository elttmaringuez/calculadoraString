/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.general.entities;

import es.general.main.Calculadora;
import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class Interprete {

    Calculator calc = new Calculator();

    public String buscaParentesis(String operacion) {
        int posAbierto = 0;
        int posCerrado;
        String prioridad;

        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '(') {
                posAbierto = i + 1;
            } else if (operacion.charAt(i) == ')') {
                posCerrado = i;
                prioridad = operacion.substring(posAbierto, posCerrado);
                return prioridad;
            }
        }
        return null;
    }

    public int posicionMultiplicacion(String operacion) {
        int posicion = 0;

        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '*') {
                posicion = i;
            }
        }

        return posicion;

    }

    public int posicionDivision(String operacion) {
        int posicion = 0;

        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '/') {
                posicion = i;
            }
        }

        return posicion;

    }

    public int posicionResta(String operacion) {
        int posicion = 0;

        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '-') {
                posicion = i;
            }
        }

        return posicion;
    }

    public int posicionSuma(String operacion) {
        int posicion = 0;

        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '+') {
                posicion = i;
            }
        }

        return posicion;
    }

    public String revierteString(String cadena) {
        String reverso = "";
        for (int j = cadena.length(); j > 0; j--) {
            reverso += cadena.substring(j - 1, j);
        }
        return reverso;
    }

    public String sacaOperacion(String operacion) {
        char simbolo = 0;
        int posicion = 0;
        int contador = 0;
        int pos1 = 0;
        int pos2 = 0;
        Double num1 = 0.0;
        Double num2 = 0.0;
        String aux = "";
        Double resultado = 0.0;
        String resultadoString;
        boolean continua = true;

        if (posicionMultiplicacion(operacion) != 0 && posicionDivision(operacion) != 0) {
            if (posicionMultiplicacion(operacion) < posicionDivision(operacion)) {
                simbolo = '*';
                posicion = posicionMultiplicacion(operacion);
            } else {
                simbolo = '/';
                posicion = posicionDivision(operacion);
            }
        } else if (posicionMultiplicacion(operacion) == 0 && posicionDivision(operacion) != 0) {
            simbolo = '/';
            posicion = posicionDivision(operacion);
        } else if (posicionMultiplicacion(operacion) != 0 && posicionDivision(operacion) == 0) {
            simbolo = '*';
            posicion = posicionMultiplicacion(operacion);
        } else if ((posicionSuma(operacion) != 0 && posicionResta(operacion) != 0)) {
            if (posicionSuma(operacion) < posicionResta(operacion)) {
                simbolo = '+';
                posicion = posicionSuma(operacion);
            } else {
                simbolo = '-';
                posicion = posicionResta(operacion);

            }
        } else if (posicionSuma(operacion) != 0 && posicionResta(operacion) == 0) {
            simbolo = '+';
            posicion = posicionSuma(operacion);

        } else if (posicionSuma(operacion) == 0 && posicionResta(operacion) != 0) {
            simbolo = '-';
            posicion = posicionResta(operacion);
        }

        contador = posicion - 1;
        while (continua && contador >= 0) {
            if ((operacion.charAt(contador) == '-' || operacion.charAt(contador) == '+' || operacion.charAt(contador) == '.' || Character.isDigit(operacion.charAt(contador))) && contador >= 0) {
                aux += operacion.charAt(contador);
            }
            if (operacion.charAt(contador) == '-' || operacion.charAt(contador) == '+' || operacion.charAt(contador) == '*' || operacion.charAt(contador) == '/'
                    || operacion.charAt(contador) == '(' || operacion.charAt(contador) == ')') {
                num1 = Double.parseDouble(revierteString(aux));
                if (num1.toString().charAt(0) == '-') {
                    pos1 = posicion - aux.length();
                } else {
                    pos1 = posicion - aux.length() + 1;
                }

                aux = "";
                continua = false;
            }
            
            if(contador == 0){
                num1 = Double.parseDouble(revierteString(aux));
                pos1 = posicion - aux.length();
                aux = "";
                continua = false;
            }
            contador--;
        }

        if (continua) {
            num1 = Double.parseDouble(revierteString(aux));
            if (num1.toString().charAt(0) == '-') {
                pos1 = posicion - aux.length();
            } else {
                pos1 = posicion - aux.length() + 1;
            }
            aux = "";
        } else {
            continua = true;
        }
        contador = posicion + 1;

        while (continua && contador < operacion.length()) {
            if ((operacion.charAt(contador) == '-' || operacion.charAt(contador) == '+') && contador == posicion + 1) {
                aux += operacion.charAt(contador);
            } else if (Character.isDigit(operacion.charAt(contador)) || operacion.charAt(contador) == '.') {
                aux += operacion.charAt(contador);

            } else {
                num2 = Double.parseDouble(aux);
                pos2 = posicion + aux.length() + 1;
                aux = "";
                continua = false;
            }

            if ((operacion.charAt(contador) == '*' || operacion.charAt(contador) == '/' || operacion.charAt(contador) == '('
                    || operacion.charAt(contador) == ')') && !"".equals(aux)) {
                num2 = Double.parseDouble(aux);
                pos2 = posicion + aux.length();
                aux = "";
                continua = false;
            }

            contador++;
        }

        if (continua) {
            num2 = Double.parseDouble(aux);
            pos2 = posicion + aux.length() + 1;
            aux = "";
        }

        switch (simbolo) {
            case '+':
                resultado = calc.sumar(num1, num2);
                break;
            case '-':
                resultado = calc.restar(num1, num2);
                break;
            case '*':
                resultado = calc.multiplicar(num1, num2);
                break;
            case '/':
                resultado = calc.dividir(num1, num2);
                break;

        }

        if (num1.toString().charAt(0) == '-' && resultado.toString().charAt(0) != '-') {
            resultadoString = "+" + resultado.toString();
        } else {
            resultadoString = resultado.toString();
        }

        operacion = sustituyeTexto(operacion, operacion.substring(pos1, pos2), resultadoString);
        num1 = 0.0;
        num2 = 0.0;
        continua = true;
        contador = 0;
        return operacion;

    }

    public String resuelveBloque(String bloque) {
        do {
            bloque = sacaOperacion(bloque);
        } while (!bloque.matches("^[\\+\\-]*\\d+\\.\\d+"));
        return bloque;
    }

    public String sustituyeTexto(String original, String objetivo, String sustitucion) {
        String str2 = original.replace(objetivo, sustitucion);
        return str2;
    }
}
