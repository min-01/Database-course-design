package model;

public class stu {
    public int studentid;
    public String name;
    public String sex;
    public String banji;
    public String department;
    public String birthday;
    public String native_place;

    public int getId(){
        return studentid;
    }

    public void setId(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBanji(){
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNative_place(){
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
    }
}
