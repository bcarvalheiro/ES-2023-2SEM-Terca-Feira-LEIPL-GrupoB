package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests Sala POJO")
public class SalaTest {
    private final String designacao = "sala1";
    private final int lotacao = 50;

    private final String designacaoEmpty = "Aguarda atribuição de sala";

    private final Sala sala = new Sala("", Integer.toString(lotacao));
    private final Sala salaUnparsableLotacao = new Sala(designacao, "b");

    @Test
    @DisplayName("Tests Sala with parsable lotacao, but empty designacao.")
    public void designationEmpty() {
        assertAll("sala",
                () -> assertEquals(0, sala.getLotacao()),
                () -> assertEquals(designacaoEmpty, sala.getDesignacao())
        );
    }

    @Test
    @DisplayName("Tests Sala with unparsable lotacao, return must be -1")
    public void parsingErrorTest() {
        assertAll("sala",
                () -> assertEquals(-1, salaUnparsableLotacao.getLotacao()),
                () -> assertEquals(designacao, salaUnparsableLotacao.getDesignacao())
        );
    }

    @Test
    @DisplayName("Tests Sala toString and setLotacao & setDesignacao")
    public void toStringTest() {
        sala.setLotacao(lotacao);
        sala.setDesignacao(designacao);
        assertEquals("Sala: " + designacao + " Capacidade: " + lotacao +"\n",
                sala.toString());

    }

}