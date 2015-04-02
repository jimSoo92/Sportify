package sportify.sportifyapp;

/**
 * Created by Dany on 2015-03-18.
 */
public class WorkoutSchedule {
    private int id;
    private String name_schedule;
    private String day_of_the_week;
    private String preset_schedule;
    private int num_of_weeks;

    public WorkoutSchedule() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_schedule(String name_schedule) {
        this.name_schedule = name_schedule;
    }

    public void setDay_of_the_week(String day_of_the_week) {
        this.day_of_the_week = day_of_the_week;
    }

    public void setPreset_schedule(String preset_schedule) {
        this.preset_schedule = preset_schedule;
    }

    public void setNum_of_weeks(int num_of_weeks) {
        this.num_of_weeks = num_of_weeks;
    }

    public int getId() {
        return id;
    }

    public String getName_schedule() {
        return name_schedule;
    }

    public int getNum_of_weeks() {
        return num_of_weeks;
    }

    public String getPreset_schedule() {
        return preset_schedule;
    }

    public String getDay_of_the_week() {
        return day_of_the_week;
    }

    public String toString() {
        return name_schedule + "\n" + day_of_the_week + "\n " + preset_schedule + "\n " + num_of_weeks + "\n";
    }
}


