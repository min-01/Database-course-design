package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class dao {
    public static void dao() throws RowsExceededException, WriteException, IOException {

        //1. 导出Excel的路径
        String filePath = "C:\\Users\\28126\\Desktop\\list.xls";
        WritableWorkbook wwb =null;

        try {
            wwb = Workbook.createWorkbook(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建Excel表的"学生"区域的数据
        WritableSheet sheet = wwb.createSheet("学生信息",0);//或者rwb.getSheet(0)获取第一个区域
        //设置titles
        String[] titles={"学号","姓名","性别","班级","院系","生日","地址"};
        //单元格
        Label label=null;
        //第一行设置列名
        for(int i=0;i<titles.length;i++){

            label=new Label(i,0,titles[i]);
            //7：添加单元格
            sheet.addCell(label);
            System.out.println("成功");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String url = "jdbc:mysql://localhost:3306/student_management?serverTimezone=Asia/Shanghai";
            String sql = "select studentid,name,sex,class,department,birthday,native_place from student";
//            String sql = "select * from student学生个人信息表";
            con = DriverManager.getConnection(url, "root", "12116szh");
            ps = con.prepareStatement(sql);// SQL预处理
            rs = ps.executeQuery();
            //ResultSet是数据库中的数据，将其转换为List类型
            List<stu> list = new ArrayList<>();
            while(rs.next()){
                stu stu = new stu();
                stu.setId(rs.getInt("studentid"));
                stu.setName(rs.getString("name"));
                stu.setSex(rs.getString("sex"));
                stu.setBanji(rs.getString("class"));
                stu.setDepartment(rs.getString("department"));
                stu.setBirthday(rs.getString("birthday"));
                stu.setNative_place(rs.getString("native_place"));

                list.add(stu);
            }
            ps.close();
            con.close();
            for(int i = 0; i<list.size(); i++){
                //Number对应数据库的int类型数据
                sheet.addCell(new Number(0,i+1,list.get(i).getId()));//0 列，i+1行
                //Label对应数据库String类型数据
                sheet.addCell(new Label(1,i+1,list.get(i).getName()));//1列，i+1行
                //Label对应数据库String类型数据
                sheet.addCell(new Label(2,i+1,list.get(i).getSex()));//2列，i+1行
                sheet.addCell(new Label(3,i+1,list.get(i).getBanji()));
                sheet.addCell(new Label(4,i+1,list.get(i).getDepartment()));
                sheet.addCell(new Label(5,i+1,list.get(i).getBirthday()));
                sheet.addCell(new Label(6,i+1,list.get(i).getNative_place()));
            }
            wwb.write();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            wwb.close();
        }


    }
}