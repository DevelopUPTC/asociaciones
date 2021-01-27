package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Student {
    private String code;
    private String name;
    private String lastName;
    private boolean gender;
    private LocalDate dateBrithday;
    private ArrayList<StudentMatter> matters;

    public Student(String code, String name, String lastName, boolean gender, LocalDate dateBrithday) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.dateBrithday = dateBrithday;
        matters = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getDateBrithday() {
        return dateBrithday;
    }

    public void setDateBrithday(LocalDate dateBrithday) {
        this.dateBrithday = dateBrithday;
    }

    public int getAge(){
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(dateBrithday,dateNow);

        return period.getYears();
    }

    public StudentMatter findMatter( String matter ){
        return matters.stream().filter(matterAux -> matterAux.getMatter().getCodeMatter().equals(matter)).findFirst().map(matterAux -> matterAux).orElse(null);
    }

    public boolean addMatter( Matter matter ){
        if ( findMatter( matter.getCodeMatter()) == null ){
            matters.add( new StudentMatter(this, matter));

            return true;
        }

        return false;
    }

    public boolean addGrade(Matter matter,  float grade ){
        StudentMatter studentMatter = findMatter( matter.getCodeMatter( ) );
        if( studentMatter != null ){
            studentMatter.addGrade( grade );

            return true;
        }

        return false;
    }

    public float calcAverage(){
        float avg = 0.0f;
        for( StudentMatter matter : matters ){
            avg += matter.calcDefinitive();
        }

        return ( float ) (Math.round(avg / matters.size() * 10.0) / 10.0 );
    }

    public byte getSumCredits(){
        byte credits = 0;
        for ( StudentMatter matter : matters ){
            credits += matter.getMatter().getCredits();
        }

        return credits;
    }

    public ArrayList<Matter> getMatters(){
        ArrayList<Matter> mattersOut = new ArrayList<>();
        matters.forEach( ( matter ) -> mattersOut.add( matter.getMatter()));

        return mattersOut;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dateBrithday=" + dateBrithday +
                '}';
    }
}
