package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student camilo;
    private Student maria;
    private Student andrea;
    private Matter calculo;
    private Matter progra1;
    private Matter algebra;
    private Matter software;
    private Matter redes;

    private void setup(){
        camilo = new Student("2017217834","Camilo Andrés","Diaz Jiménez",true,LocalDate.of(1998,Month.APRIL,14));
        maria = new Student("2014210432","María José","Correa Vianchá",false,LocalDate.of(1996,Month.AUGUST,3));
        andrea = new Student("201820619","Andrea del Pilar","López Barrera",false,LocalDate.of(2003,Month.MARCH,27));

        calculo = new Matter("256984","Cáculo Diferencial",(byte)3 );
        progra1 = new Matter("435464","Programación I",(byte)4);
        algebra = new Matter("225416","Algebra Lineal",(byte) 3);
        software = new Matter("937445","Software II",(byte) 4);
        redes = new Matter("789424","Redes y Comunicaciones",(byte)4);

        camilo.addMatter( calculo );
        camilo.addMatter( progra1 );
        camilo.addMatter( algebra );

        maria.addMatter( redes );
        maria.addMatter( software );

        andrea.addMatter( software );
        andrea.addMatter( redes );
        andrea.addMatter( calculo );

        camilo.addGrade( calculo, (float)3.0 );
        camilo.addGrade( calculo, (float)2.0 );
        camilo.addGrade( calculo, (float)2.5 );
        camilo.addGrade( progra1,(float)3.7);
        camilo.addGrade( progra1,(float)3.2);
        camilo.addGrade( progra1,(float)3.5);
        camilo.addGrade( algebra,(float)2.5);
        camilo.addGrade( algebra,(float)3.4);

        maria.addGrade( redes,(float)4.0 );
        maria.addGrade( redes,(float)3.3 );
        maria.addGrade( software,(float)4.5 );
        maria.addGrade( software,(float)3.8 );
        maria.addGrade( software,(float)4.2 );

        andrea.addGrade( software,( float ) 2.5 );
        andrea.addGrade( software,( float ) 2.0 );
        andrea.addGrade( software,( float ) 1.5 );
        andrea.addGrade( redes,( float ) 2.0 );
        andrea.addGrade( redes,( float ) 3.2 );
        andrea.addGrade( redes,( float ) 2.5 );
        andrea.addGrade( calculo,( float ) 3.2 );
        andrea.addGrade( calculo,( float ) 3.6 );
        andrea.addGrade( calculo,( float ) 2.9 );
        andrea.addGrade( calculo,( float ) 4.0 );
    }


    @Test
    void getAge() {
        Student lola = new Student("25234","Dolores","Cabezas", false,LocalDate.of(1968, Month.JUNE,25));
        assertEquals(52,lola.getAge());
    }

    @Test
    public void testFindMatter(){
        setup();
        assertNotNull( camilo.findMatter( calculo.getCodeMatter()));
        assertNotNull( camilo.findMatter( progra1.getCodeMatter()));
        assertNotNull( camilo.findMatter( algebra.getCodeMatter()));
        assertNull( camilo.findMatter( redes.getCodeMatter()));
        assertEquals("789424",maria.findMatter(redes.getCodeMatter()).getMatter().getCodeMatter());
    }

    @Test
    public void testAddMatter(){
        setup();
        assertFalse(camilo.addMatter( progra1 ) );
        assertTrue(camilo.addMatter( redes ) );
        assertEquals( 4, camilo.getMatters().size());
    }

    @Test
    public void testGetSumCredits(){
        setup();
        assertEquals( 10, camilo.getSumCredits());
        assertEquals( 8, maria.getSumCredits());
        assertEquals( 11, andrea.getSumCredits());
    }

    @Test
    public void testAddGrade(){
        setup();
        assertFalse( camilo.addGrade( redes,(float)4.6));
        assertFalse( andrea.addGrade( progra1,(float)4.0));
        assertTrue( andrea.addGrade( redes,(float)4.0));
        assertTrue( maria.addGrade( redes,(float)4.5));
        assertEquals(3, maria.findMatter( redes.getCodeMatter()).getGrades().size());
    }

    @Test
    public void testCalcDefinitive(){
        setup();
        assertEquals(2.5,camilo.findMatter(calculo.getCodeMatter()).calcDefinitive());
        assertEquals(3.0,camilo.findMatter(algebra.getCodeMatter()).calcDefinitive(),0.1);
        assertEquals(3.5,camilo.findMatter(progra1.getCodeMatter()).calcDefinitive(),0.1);

        assertEquals( 3.7,maria.findMatter(redes.getCodeMatter()).calcDefinitive(),0.1);
        assertEquals( 4.2,maria.findMatter(software.getCodeMatter()).calcDefinitive(),0.1);

        assertEquals( 2.0,andrea.findMatter(software.getCodeMatter()).calcDefinitive(),0.1);
        assertEquals( 2.6,andrea.findMatter(redes.getCodeMatter()).calcDefinitive(),0.1);
        assertEquals( 3.4,andrea.findMatter(calculo.getCodeMatter()).calcDefinitive(),0.1);
    }

    @Test
    public void testCalcAverage(){
        setup();
        assertEquals(3.0,camilo.calcAverage(),0.1);
        assertEquals(2.7,andrea.calcAverage(),0.1);
        assertEquals(4.0,maria.calcAverage(),0.1);
    }
}