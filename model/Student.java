package model;

import java.sql.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Student {
    static String driverName = "com.mysql.cj.jdbc.Driver";
    //可用useSSL=false&useUnicode=true&characterEncoding=UTF-8
    static String dbURL="jdbc:mysql://localhost:3306/student_management?serverTimezone=Asia/Shanghai";
    static String userName = "root";
    static String userPwd = "12116szh";
    public String id;
    public String name;
    public String sex;
    public String grade;
    public String college;
    public String birthday;
    public String native_place;
    public String awardlabel;
    public String punishlabel;
    public String reward_punish;
    public String changestatus;
    public String changetime;

    public Student(String id, String name, String sex, String grade, String college, String birthday, String native_place, String awardlabel, String punishlabel, String changestatus, String changetime){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
        this.college = college;
        this.birthday = birthday;
        this.native_place = native_place;
        this.awardlabel = awardlabel;
        this.punishlabel = punishlabel;
        this.changestatus = changestatus;
        this.changetime = changetime;
    }
    public Student(String name, String reward_punish){
        this.name = name;
        this.reward_punish = reward_punish;
    }

    public static void add(String id, String name, String sex, String grade, String college, String birthday,String native_place){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            String sql = "insert into student value ('"+id+"','"+name+"','"+sex+"','"+grade+"','"+college+"','"+birthday+"','"+native_place+"','"+id+"');";
            System.out.println("添加成功");
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    public static Student query(String id){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            String sql = "select studentid,name,sex,class,department,birthday,native_place from student where studentid="+id;
            String sql1 = "select reward_levels.DESCRIPTION from reward_levels INNER JOIN student,reward " +
                    "where student.STUDENTID="+id+" and student.STUDENTID=reward.STUDENTID AND reward.LEVELS=reward_levels.CODE";
            String sql2 = "select punish_levels.DESCRIPTION from punish_levels INNER JOIN student,punishment " +
                    "where student.STUDENTID="+id+" and student.STUDENTID=punishment.STUDENTID AND punishment.LEVELS=punish_levels.CODE";
            String sql3 = "select change_code.description,`change`.REC_TIME from `change`,`change_code` where `change`.STUDENTID="+id+" and `change`.`CHANGE`=`change_code`.`CODE`";
            String name = "";
            String sex = "";
            String grade = "";
            String college = "";
            String birthday = "";
            String native_place = "";
            String awardlabel = "";
            String punishlabel = "";
            String changestatus = "";
            String changetime = "";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                id = resultSet.getString("studentid");
                name = resultSet.getString("name");        //在getString前必须要next()
                sex = resultSet.getString("sex");
                grade = resultSet.getString("class");
                college = resultSet.getString("department");
                birthday = resultSet.getString("birthday");
                native_place = resultSet.getString("native_place");
            }
            if(name == ""){
                id = null;
            }
            ResultSet resultSet1 = statement.executeQuery(sql1);
            while (resultSet1.next()){
                awardlabel += resultSet1.getString("reward_levels.DESCRIPTION")+",";
            }
            ResultSet resultSet2 = statement.executeQuery(sql2);
            while (resultSet2.next()){
                punishlabel += resultSet2.getString("punish_levels.DESCRIPTION")+",";
            }
            ResultSet resultSet3 = statement.executeQuery(sql3);
            while (resultSet3.next()){
                changestatus = resultSet3.getString("change_code.description");
                changetime = resultSet3.getString("change.REC_TIME");
            }
            Student student = new Student(id,name,sex,grade,college,birthday,native_place,awardlabel,punishlabel,changestatus,changetime);
            resultSet.close();
            resultSet1.close();
            resultSet2.close();
            statement.close();
            connection.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
        }
        return null;
    }

    public static Student reward(String id,String choice,String desciption){
        try {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            String sql = "select name from student where studentid="+id;
            String name = null;
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                name = resultSet.getString("name");
            }
            String level = null;
            ResultSet resultSet1 = statement.executeQuery("select CODE from reward_levels where description='"+choice+"';");
            while (resultSet1.next()){
                level = resultSet1.getString("code");
            }
            Date now = new Date();
            // java.util.Date -> java.time.LocalDate
            LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // java.time.LocalDate -> java.sql.Date
            Date newDate=java.sql.Date.valueOf(localDate);
            statement.execute("insert into reward (studentid,levels,rec_time,description) values ('"+id+"','"+level+"','"+newDate+"','"+desciption+"');");
            String reward = "";
            ResultSet resultSet2 = statement.executeQuery("select reward_levels.description from reward_levels inner join reward where reward.STUDENTID=1905040204 and reward.LEVELS=reward_levels.CODE;");
            while (resultSet2.next()){
                reward += resultSet2.getString("reward_levels.description")+",";
            }
            resultSet.close();
            resultSet1.close();
            resultSet2.close();
            statement.close();
            connection.close();
            return new Student(name, reward);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student punish(String id,String choice,String desciption){
        try {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            String sql = "select name from student where studentid="+id;
            String name = null;
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                name = resultSet.getString("name");
            }
            String level = null;
            ResultSet resultSet1 = statement.executeQuery("select CODE from punish_levels where description='"+choice+"';");
            while (resultSet1.next()){
                level = resultSet1.getString("code");
            }
            Date now = new Date();
            LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date newDate=java.sql.Date.valueOf(localDate);
            statement.execute("insert into punishment (studentid,levels,rec_time,enable,description) values ('"+id+"','"+level+"','"+newDate+"','是','"+desciption+"');");
            String punish = "";
            ResultSet resultSet2 = statement.executeQuery("select punish_levels.description from punish_levels inner join punishment where punishment.STUDENTID=1905040204 and punishment.LEVELS=punish_levels.CODE;");
            while (resultSet2.next()){
                punish += resultSet2.getString("punish_levels.description")+",";
            }
            resultSet.close();
            resultSet1.close();
            resultSet2.close();
            statement.close();
            connection.close();
            return new Student(name, punish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(String item,String content,String id){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            statement.execute("update student set "+item+"='"+content+"' where studentid="+id);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void status(String id, String choice, String description) {
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            String code = "";
            ResultSet resultSet = statement.executeQuery("select code from change_code where description='"+choice+"';");
            while (resultSet.next()){
                code = resultSet.getString("code");
            }
            Date now = new Date();
            LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date newDate=java.sql.Date.valueOf(localDate);
            statement.execute("insert into change (studentid, `CHANGE`, rec_time, description) values ('"+id+"','"+code+"','"+newDate+"','"+description+"');");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void delete(String id){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
//            String code = "";
            statement.execute("delete from student where studentid="+id+"");
            statement.close();
            connection.close();

//            statement.execute("insert into change (studentid, `CHANGE`, rec_time, description) values ('"+id+"','"+code+"','"+newDate+"','"+description+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Boolean login(String password,String hao){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
//            String code = "";
            ResultSet resultSet = statement.executeQuery("select password from user where account="+hao);
            while (resultSet.next()){
                if (password.equals(resultSet.getString("password"))){
                    return true;
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void register(String password,String hao){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            statement.execute("insert into user value ('"+hao+"','"+password+"')");
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Boolean studentlogin(String password, String hao) {
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select password from student where studentid="+hao);
            while (resultSet.next()){
                if (password.equals(resultSet.getString("password"))){
                    return true;
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updatehao(String hao,String old,String newmima){
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(dbURL,userName,userPwd);
            Statement statement = connection.createStatement();
            statement.execute("update student set password='"+newmima+"' where studentid='"+hao+"' and password='"+old+"'");
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}