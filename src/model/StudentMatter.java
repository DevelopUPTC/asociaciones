package model;

import java.util.ArrayList;
import java.util.List;

public class StudentMatter {
    private Student student;
    private Matter matter;
    private ArrayList<Float> grades;

    public StudentMatter(Student student, Matter matter) {
        this.student = student;
        this.matter = matter;
        grades = new ArrayList<>();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public ArrayList<Float> getGrades() {
        return (ArrayList<Float>) grades.clone();
    }

    public void addGrade( float grade ){
        grades.add( grade );
    }

    public float calcDefinitive(){
        float avg = 0.0f;
        for( Float grade : grades ){
            avg += grade;
        }

        return (float) (Math.round(avg / grades.size() * 10.0) / 10.0);
    }

}
