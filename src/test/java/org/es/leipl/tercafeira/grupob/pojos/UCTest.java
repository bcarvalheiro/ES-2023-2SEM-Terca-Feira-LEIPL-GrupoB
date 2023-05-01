package org.es.leipl.tercafeira.grupob.pojos;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 The UCTest class contains JUnit test cases for the UC class.
 It tests the various methods of the UC class such as creation of UC object, setting the name of a UC object,
 adding a Turno object to an UC object, and adding a list of turns to a UC object.
 The test cases check if the UC class methods are working correctly or not.
 @see UC
 @see Turno
 */
public class UCTest {
    private final String nome = "JUnit Testing";

    private List<Turno> turnoList = new ArrayList<>();
    private final Turno turno = new Turno("turno1", null, "50",null);
    private final UC uc = new UC(nome, turno);

    private final UC emptyUC = new UC("", null);

    /**
     Test case to verify that an instance of UC without a name has a null name
     and an empty list of turns.
     */
    @Test
    @DisplayName("UC without nome is null")
    public void withoutNomeAndTurnoTest() {
        assertAll("uc",
                () -> assertNull(emptyUC.getNome()),
                () -> assertTrue(emptyUC.getTurnosList().isEmpty())
        );
    }
    /**
     Test case for the creation of UC object.
     Verify that an instance of UC without a name has a null name
     and an empty list of turns.
     */
    @Test
    @DisplayName("UC creation Test")
    public void ucCreationTest() {
        assertAll("uc",
                () -> assertEquals(nome, uc.getNome()),
                () -> assertEquals(turno, uc.getTurnosList().get(0))
        );
    }

    /**
     Test method for setting the name of a UC object
     @param emptyUC The UC object to set the name of
     @param nome The name to set the UC object to
     Checks that the name of the UC object is correctly set to the given name.
     */
    @Test
    @DisplayName("UC setNome")
    public void ucsetNomeTest() {
        emptyUC.setNome(nome);
        assertEquals(nome, uc.getNome());
    }

    /**
     Tests the addition of a Turno object to an UC object.
     It adds a Turno object to an empty UC object and checks if the list of Turno objects is updated correctly.
     @see UC
     @see Turno
     */
    @Test
    @DisplayName("UC turno add")
    public void ucTurnoAddTest() {
        emptyUC.addTurno(turno);
        turnoList.add(turno);
        assertEquals(turnoList, uc.getTurnosList());
    }

    /**
     Tests the addTurnos method of the UC class, which should add a list of turns to the UC.
     This test sets an empty list of turns to the UC, adds a list of turns to it, and then
     checks if the list of turns is the same as the one added to the UC.
     */
    @Test
    @DisplayName("UC turno add")
    public void ucTurnosListAddTest() {
        uc.setTurnosList(new ArrayList<>());
        turnoList.add(turno);
        turnoList.add(turno);
        uc.addTurnos(turnoList);
        assertEquals(turnoList, uc.getTurnosList());
    }

}
