package model;

import java.util.ArrayList;

public class Matter {
    private String codeMatter;
    private String description;
    private byte credits;
    private ArrayList<StudentMatter> students;

    public Matter(String codeMatter, String description, byte credits) {
        this.codeMatter = codeMatter;
        this.description = description;
        this.credits = credits;
        students = new ArrayList<>();
    }

    public String getCodeMatter() {
        return codeMatter;
    }

    public void setCodeMatter(String codeMatter) {
        this.codeMatter = codeMatter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getCredits() {
        return credits;
    }

    public void setCredits(byte credits) {
        this.credits = credits;
    }

    public StudentMatter findStudent(String code){
        return students.stream().filter( studentAux -> studentAux.getStudent().getCode().equals(code)).findFirst().map(studentAux -> studentAux).orElse(null);
    }

    public boolean addStudent( Student student ){
        if ( findStudent( student.getCode() ) == null ){
            students.add( new StudentMatter(student, this ) );

            return true;
        }

        return false;
    }

    public float calcAverage(){
        float avg = 0.0f;
        for( StudentMatter student : students ){
            avg += student.calcDefinitive();
        }

        return (float)( Math.round( avg / students.size() * 10.0 ) / 10.0 );
    }

    public boolean addGrade( Student student,float grade ){
        StudentMatter studMatter = findStudent(student.getCode());
        if (studMatter != null ){
            studMatter.addGrade( grade );

            return true;
        }

        return false;
    }

    public ArrayList<StudentMatter> getStudents() {
        return (ArrayList<StudentMatter>) students.clone();
    }

    @Override
    public String toString() {
        return "Matter{" +
                "codeMatter='" + codeMatter + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                '}';
    }
}
