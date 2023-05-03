package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 This test class tests the behavior of the {@link Sala} class in scenarios
 where the lotacao attribute is either empty or unparsable.
 */

@DisplayName("Tests Sala POJO")
public class SalaTest {
    private final String designacao = "sala1";
    private final int lotacao = 50;

    private final String designacaoEmpty = "Aguarda atribuição de sala";

    private final Sala sala = new Sala("", Integer.toString(lotacao));
    private final Sala salaUnparsableLotacao = new Sala(designacao, "b");

    /**
     This method tests the behavior of the Sala class when an empty designation is provided, but the lotacao is parsable.
     It uses JUnit's @Test annotation to indicate that it is a test method, and @DisplayName annotation to provide a
     description of the test.
     The assertAll method is used to group multiple assertions together, and to ensure that all assertions are executed
     even if one of them fails. Two assertions are performed: one to verify that the lotacao attribute of the Sala object
     is equal to zero, and another to verify that the designacao attribute of the Sala object is equal to the expected
     value of "empty".
     */
    @Test
    @DisplayName("Tests Sala with parsable lotacao, but empty designacao.")
    public void designationEmpty() {
        assertAll("sala",
                () -> assertEquals(0, sala.getLotacao()),
                () -> assertEquals(designacaoEmpty, sala.getDesignacao())
        );
    }

    /**
     Test case that checks if a Sala object with an unparsable lotacao attribute
     returns the expected values. The lotacao value is expected to be -1 when it
     is not parsable.
     It sets a designacao and an unparsable lotacao to a new Sala object and then
     it checks if the getters return the expected values using assertAll.
     */

    @Test
    @DisplayName("Tests Sala with unparsable lotacao, return must be -1")
    public void parsingErrorTest() {
        assertAll("sala",
                () -> assertEquals(-1, salaUnparsableLotacao.getLotacao()),
                () -> assertEquals(designacao, salaUnparsableLotacao.getDesignacao())
        );
    }

    /**
     This class contains a JUnit test for the method toString, setLotacao and setDesignacao of the class Sala.
     */
    @Test
    @DisplayName("Tests Sala toString and setLotacao & setDesignacao")
    public void toStringTest() {
        sala.setLotacao(lotacao);
        sala.setDesignacao(designacao);
        assertEquals("Sala: " + designacao + " Capacidade: " + lotacao +"\n",
                sala.toString());

    }

}