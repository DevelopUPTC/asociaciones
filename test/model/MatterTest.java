package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class MatterTest {
    private Student camilo;
    private Student maria;
    private Student andrea;
    private Matter calculo;
    private Matter progra1;
    private Matter algebra;
    private Matter software;
    private Matter redes;

    private void setupOne(){
        camilo = new Student("2017217834","Camilo Andrés","Diaz Jiménez",true, LocalDate.of(1998, Month.APRIL,14));
        maria = new Student("2014210432","María José","Correa Vianchá",false,LocalDate.of(1996,Month.AUGUST,3));
        andrea = new Student("201820619","Andrea del Pilar","López Barrera",false,LocalDate.of(2003,Month.MARCH,27));

        calculo = new Matter("256984","Cálculo Diferencial",(byte)3 );
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

    private void setupTwo(){
        setupOne();
        calculo.addStudent( camilo );
        calculo.addStudent( andrea );

        progra1.addStudent( camilo );

        algebra.addStudent( camilo );

        software.addStudent(maria);
        software.addStudent(andrea);

        redes.addStudent( maria );
        redes.addStudent( andrea );

        calculo.addGrade( camilo, (float)3.0 );
        calculo.addGrade( camilo, (float)2.0 );
        calculo.addGrade( camilo, (float)2.5 );

        calculo.addGrade( andrea,(float)3.2 );
        calculo.addGrade( andrea,(float)3.6 );
        calculo.addGrade( andrea,(float)2.9 );
        calculo.addGrade( andrea,(float)4.0 );

        progra1.addGrade( camilo, (float)3.7 );
        progra1.addGrade( camilo, (float)3.2 );
        progra1.addGrade( camilo, (float)3.5 );

        algebra.addGrade( camilo, (float)2.5 );
        algebra.addGrade( camilo, (float)3.4 );

        software.addGrade( maria, (float)4.5 );
        software.addGrade( maria, (float)3.8 );
        software.addGrade( maria, (float)4.2 );

        software.addGrade( andrea, (float)2.5 );
        software.addGrade( andrea, (float)2.0 );
        software.addGrade( andrea, (float)1.5 );

        redes.addGrade( maria, (float ) 4.0 );
        redes.addGrade( maria, (float ) 3.3 );

        redes.addGrade( andrea, (float) 2.0 );
        redes.addGrade( andrea, (float) 3.2 );
        redes.addGrade( andrea, (float) 2.5 );
    }

    @Test
    public void findStudent() {
        setupTwo();
        assertNotNull( calculo.findStudent( camilo.getCode()));
        assertNotNull( calculo.findStudent( andrea.getCode()));
        assertNull( calculo.findStudent( maria.getCode()));

        assertNotNull( progra1.findStudent( camilo.getCode()));
        assertNull( progra1.findStudent( andrea.getCode()));
        assertNull( progra1.findStudent( maria.getCode()));
    }

    @Test
    public void testAddStudent(){
        setupTwo();
        assertTrue( progra1.addStudent( maria ) );
        assertTrue( progra1.addStudent( andrea ) );
        assertFalse( progra1.addStudent( camilo ));
        assertEquals(3, progra1.getStudents().size());

        assertFalse(redes.addStudent( maria ) );
        assertFalse(redes.addStudent( andrea ) );
    }

    @Test
    public void testCalcAverage(){
        setupTwo();
        assertEquals( 3.0, calculo.calcAverage( ),0.1 );
        assertEquals( 3.5, progra1.calcAverage( ), 0.1 );
        assertEquals( 3.0, algebra.calcAverage( ), 0.1 );
        assertEquals( 3.1, software.calcAverage( ), 0.1 );
        assertEquals( 3.2, redes.calcAverage( ), 0.1 );
    }

    @Test
    public void testGetStudents(){
        setupTwo();
        assertEquals(2,calculo.getStudents().size());
        assertEquals(1,progra1.getStudents().size());
        assertEquals("2017217834",progra1.getStudents().get(0).getStudent().getCode());
    }

}