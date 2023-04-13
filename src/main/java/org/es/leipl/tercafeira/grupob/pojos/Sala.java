package org.es.leipl.tercafeira.grupob.pojos;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

/**
 * Java Object for representation of the classroom
 */
public class Sala {

    /**
     * designacao is the name of the classroom
     */
    private String designacao;

    /**
     * lotacao is the classroom student capacity
     */
    private int lotacao;

    public Sala (String designacao, String lotacao) {
        if (designacao == null || designacao.trim().isEmpty() || lotacao == null) {
            this.designacao = "Aguarda atribuição de sala";
            this.lotacao=0;

        }
        else {
            this.designacao = designacao;
            this.lotacao = parseNumeroLotacao(lotacao);
        }
    }

    /**
     * Tries to parse a string to Integer
     *
     * @param lotacao
     *
     * @return The Integer value of the param or -1 if the String is not parsable
     */
    private int parseNumeroLotacao(String lotacao) {
        try{
            return Integer.parseInt(lotacao);
        }catch(NumberFormatException e) {
            System.out.println("lotacao is not parsable" + e);
            return -1;
        }
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    @Override
    public String toString() {
        //To Do
        return "Sala: " + designacao + " Capacidade: " + lotacao +"\n";
    }


}
