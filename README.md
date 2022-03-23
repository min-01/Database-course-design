## 课题————学生信息管理系统

#### 目的：

​        学校每年都有新生入学、老生毕业，还有其他各种人事变动。为有效地管理这些学生的信息，帮助学校和老师掌握学生的情况。  
​        通过课程设计，培养综合运用本门课程及有关先修课程的基本知识去解决某一实际问题的实际本领，加深对该课程知识的理解。  
​        主要培养以下能力：查阅资料：搜集与本设计有关的资料（包括从已发表的文献中和从生产现场中搜集)的能力；方案的选择：树立既考虑技术上的先进性与可行性，又考虑经济上的合理性，并注意提高分析和解决实际问题的能力；迅速准确的进行工程计算的能力，计算机应用能力；用简洁的文字，清晰的图表来表达自己设计思想的能力。

#### 总体设计：

​        总体设计采用JavaFX+FXML+CSS+MySQL，设计包含主登录在内总共8个界面，由主页五个按钮分别跳转至五个fxml界面，fxml界面设计使用Scene builder，每个fxml界面由对应其相应控制器操控事件，由主界面控制器程序对按钮事件调控，操控界面跳转。后端代码设计引入jdbc模块使用SQL对MySQL数据库进行操作，创建student_management数据库并建有9个数据表分别将学生各类信息存入数据表中保存，以确保其安全稳定。

项目结构：![结构图](https://github.com/min-01/Database-course-design/blob/main/figure/structure.png)

#### 数据流程图：

![流程图](https://github.com/min-01/Database-course-design/blob/main/figure/flow.png)

#### 数据库需求分析

根据数据流程图，可以列出以下记录学生信息所需的数据项和数据结构：

* 学生：学号、姓名、性别、生日、籍贯、所在院系、所在班级。

* 处罚记录：记录号、级别、处罚对象、记录时间、详细描述、是否生效。

* 奖励记录：记录号、级别、奖励对象、记录时间、详细描述。

* 学籍变更记录：记录号、变更情况、记录对象、记录时间、详细描述。

所需的外部数据支持：

* 班级：班级编号、班级名称、所属院系。

* 院系：代码、名称。

#### 数据库概念结构设计：

本系统所需数据的 E-R 模型图：

![E-R模型](https://github.com/min-01/Database-course-design/blob/main/figure/E-R.png)

#### 界面设计：

登录界面分为学生登录和管理员登录，学生登录只能查询学生基本学籍信息和修改账号密码，学生账号固定为学号，密码初始默认学号，登录后可以修改。

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu01.png" alt="01" style="zoom: 67%;" />

![02](https://github.com/min-01/Database-course-design/blob/main/figure/stu02.png)

管理员登录后进入主页界面，CSS修饰主页

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu03.png" alt="03" style="zoom: 80%;" />

学生个人信息输入

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu04.png" alt="04" style="zoom: 80%;" />

查询修改功能

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu05.png" alt="05" style="zoom:67%;" />

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu06.png" alt="06" style="zoom:67%;" />

学籍变更和奖罚

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu07.png" alt="07" style="zoom:67%;" />

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu08.png" alt="08" style="zoom:67%;" />

<img src="https://github.com/min-01/Database-course-design/blob/main/figure/stu09.png" alt="09" style="zoom:67%;" />

##### 使用说明：

本系统设计为初学者基本知识运用，设计较简单操作便捷，在此不做过多描述。
