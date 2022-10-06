package springcourse.models;



public class Human {
    private int human_id;
    private String name;
    private int year_of_birth;

    public int getHuman_id() {
        return human_id;
    }

    public void setHuman_id(int human_id) {
        this.human_id = human_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }


    public Human() {}

    public Human(String name, int year_of_birth) {
        this.name = name;
        this.year_of_birth = year_of_birth;
    }

}
