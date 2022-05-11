/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.general.main;

import es.general.entities.Interprete;

/**
 *
 * @author Nitropc
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //String operacion = "(24+(98-(89-3*(87/98-(23*2-3)))))";
        //String operacion = "((34+43*2)-(89/50))";
        //String operacion = "(89-3*-42.11224489795919)";
        //String operacion = "((24+(50/(11+14)))+4-(9-7))";
        String operacion = "(663*2-(23*4))";
        Interprete interprete = new Interprete();
        boolean niegaParentesis = false;

        if (operacion.contains("(")) {

            do {
                String prioridad = interprete.buscaParentesis(operacion);
                int posicionParentesis = operacion.indexOf(prioridad);

                System.out.println(prioridad);

                String prioridadResuelta = interprete.resuelveBloque(prioridad);
                if (posicionParentesis - 2 >= 0) {
                    if (prioridadResuelta.charAt(0) == '-' && operacion.charAt(posicionParentesis - 2) == '-') {
                        prioridadResuelta = interprete.sustituyeTexto(prioridadResuelta, "-", "+");
                        operacion = interprete.sustituyeTexto(operacion, Character.toString(operacion.charAt(posicionParentesis - 2)), "");
                    }
                }
                System.out.println(prioridadResuelta);
                prioridad = "(" + interprete.buscaParentesis(operacion) + ")";
                operacion = interprete.sustituyeTexto(operacion, prioridad, prioridadResuelta);
                System.out.println(operacion);
            } while (operacion.contains("("));

        }

    }
}
