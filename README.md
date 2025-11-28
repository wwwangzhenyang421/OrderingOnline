# 在线订餐系统 (OrderingOnline)

一个基于 Java Swing 开发的在线订餐管理系统，支持多角色用户（学生、教师、商家、管理员）的订餐业务管理。

## 📋 项目简介

在线订餐系统是一个面向校园或机构的订餐管理平台，采用 Java Swing 构建图形用户界面，实现了完整的订餐业务流程。系统采用 MVC（Model-View-Controller）架构设计，支持用户注册、登录、菜品管理、订单管理等核心功能。

## 🎯 主要功能

### 用户角色
- **学生 (Student)**：浏览菜单、查看订单、编辑个人信息
- **教师 (Teacher)**：浏览菜单、查看订单、编辑个人信息
- **商家 (Seller)**：管理菜品（添加、编辑、删除）、查看订单
- **管理员 (Administrator)**：管理用户、管理订单、系统维护

### 核心功能模块
1. **用户认证**
   - 用户注册
   - 用户登录（支持四种角色）
   - 密码验证（最多5次错误尝试）

2. **菜品管理**
   - 浏览菜品菜单
   - 添加新菜品（商家）
   - 编辑菜品信息（商家）
   - 删除菜品（商家）
   - 菜品信息包括：名称、价格、窗口名称、食材、口味、重量、库存状态

3. **订单管理**
   - 查看订单列表
   - 创建新订单
   - 删除订单
   - 订单信息包括：菜品信息、客户姓名、地址、电话

4. **个人信息管理**
   - 查看个人信息
   - 编辑个人信息

## 🏗️ 项目结构

```
OrderingOnline/
├── src/                          # 源代码目录
│   ├── App.java                  # 应用程序主入口
│   ├── model/                    # 数据模型层
│   │   ├── User.java            # 用户抽象基类
│   │   ├── Student.java         # 学生模型
│   │   ├── Teacher.java         # 教师模型
│   │   ├── Seller.java          # 商家模型
│   │   ├── Administrator.java   # 管理员模型
│   │   ├── Dish.java            # 菜品模型
│   │   └── Order.java           # 订单模型
│   ├── view/                     # 视图层（GUI界面）
│   │   ├── MainFrame.java       # 主登录界面
│   │   ├── StudentsPanel.java   # 学生功能面板
│   │   ├── TeachersPanel.java   # 教师功能面板
│   │   ├── SellerPanel.java     # 商家功能面板
│   │   └── AdministratorPanel.java # 管理员功能面板
│   └── controller/               # 控制器层（业务逻辑）
│       ├── AddUser.java         # 添加用户
│       ├── CheckInfo.java       # 验证用户信息
│       ├── AddDishes.java       # 添加菜品
│       ├── DeleteDishes.java    # 删除菜品
│       ├── DishesEditInfo.java  # 编辑菜品信息
│       ├── DishView.java        # 查看菜品
│       ├── OrderView.java       # 查看订单
│       ├── DeleteOrder.java     # 删除订单
│       ├── EditInfo.java        # 编辑个人信息
│       ├── Info.java            # 查看个人信息
│       └── DeleteUser.java      # 删除用户
├── bin/                          # 编译输出目录
├── data/                         # 数据存储目录
│   ├── student.txt              # 学生数据
│   ├── teacher.txt              # 教师数据
│   ├── seller.txt               # 商家数据
│   ├── administrator.txt        # 管理员数据
│   ├── dishes.txt               # 菜品数据
│   └── orders.txt               # 订单数据
├── images/                       # 项目截图和图片资源
├── compile.bat                   # 编译脚本
├── run.bat                       # 运行脚本
├── build-and-run.bat             # 一键编译并运行脚本
└── README.md                     # 项目说明文档（本文件）
```

## 🚀 快速开始

### 环境要求
- **JDK**: Java Development Kit 8 或更高版本
- **操作系统**: Windows（批处理脚本）或支持 Java 的其他操作系统
- **编码**: UTF-8

### 安装步骤

1. **克隆或下载项目**
   ```bash
   # 如果使用 Git
   git clone <repository-url>
   cd OrderingOnline
   ```

2. **检查 Java 环境**
   ```bash
   java -version
   javac -version
   ```
   确保已安装 JDK 并配置好环境变量。

3. **编译项目**
   
   **方式一：使用批处理脚本（推荐，Windows）**
   ```bash
   compile.bat
   ```
   
   **方式二：手动编译**
   ```bash
   javac -encoding UTF-8 -d bin -sourcepath src src\App.java src\view\*.java src\controller\*.java src\model\*.java
   ```

4. **运行项目**
   
   **方式一：一键编译并运行（推荐）**
   ```bash
   build-and-run.bat
   ```
   
   **方式二：仅运行（需先编译）**
   ```bash
   run.bat
   ```
   
   **方式三：手动运行**
   ```bash
   java -cp bin App
   ```

## 📖 使用说明

### 首次使用

1. **启动应用程序**
   - 运行 `build-and-run.bat` 或 `run.bat`
   - 系统将打开登录界面

2. **注册新用户**
   - 在登录界面点击 "register" 按钮
   - 选择用户角色（Student/Teacher/Seller/Administrator）
   - 填写用户ID、密码和姓名
   - 完成注册

3. **登录系统**
   - 在登录界面选择用户角色
   - 输入已注册的用户ID和密码
   - 点击 "login" 按钮
   - 注意：连续5次登录失败将自动退出系统

### 功能使用指南

#### 学生/教师用户
- **Menu Search**: 浏览和搜索菜品菜单
- **Order Search**: 查看个人订单历史
- **Edit Info**: 编辑个人基本信息
- **Student Info / Teacher Info**: 查看个人详细信息

#### 商家用户
- **Add Dishes**: 添加新菜品到菜单
- **Edit Dishes**: 编辑现有菜品信息
- **Delete Dishes**: 删除菜品
- **View Orders**: 查看所有订单
- **Edit Info**: 编辑个人信息

#### 管理员用户
- **User Management**: 管理所有用户（添加、删除）
- **Order Management**: 管理所有订单（查看、删除）
- **System Maintenance**: 系统维护功能

## 🔧 技术架构

### 设计模式
- **MVC 架构模式**：将业务逻辑、数据模型和用户界面分离
  - **Model**: 数据模型层（`src/model/`）
  - **View**: 视图层（`src/view/`）
  - **Controller**: 控制器层（`src/controller/`）

### 核心技术
- **Java Swing**: 图形用户界面框架
- **文件 I/O**: 使用文本文件进行数据持久化
- **面向对象设计**: 继承、多态等 OOP 特性

### 数据存储
- 数据以文本文件（`.txt`）形式存储在 `data/` 目录
- 每个实体类型对应一个数据文件
- 数据格式：空格分隔的字段

## 📝 开发说明

### 代码规范
- 包结构：`model`、`view`、`controller`
- 类命名：采用大驼峰命名法（PascalCase）
- 方法命名：采用小驼峰命名法（camelCase）
- 编码格式：UTF-8

### 扩展开发
如需添加新功能：
1. 在对应的包中创建新类
2. 遵循 MVC 架构原则
3. 更新相应的数据文件格式（如需要）
4. 在对应的 Panel 中添加功能入口

## ⚠️ 注意事项

1. **数据文件位置**
   - 确保 `data/` 目录存在且包含必要的数据文件
   - 数据文件路径为相对路径：`data/xxx.txt`

2. **编码问题**
   - 编译时使用 `-encoding UTF-8` 参数
   - 批处理脚本已设置 UTF-8 编码（`chcp 65001`）

3. **登录安全**
   - 系统限制连续5次登录失败后自动退出
   - 密码以明文形式存储（生产环境需加密）

4. **线程安全**
   - 主入口使用 `SwingUtilities.invokeLater` 确保线程安全

## 🐛 常见问题

**Q: 编译时出现编码错误？**  
A: 确保使用 `compile.bat` 脚本，它已配置 UTF-8 编码。或手动添加 `-encoding UTF-8` 参数。

**Q: 运行时找不到类文件？**  
A: 确保已运行 `compile.bat` 编译项目，`bin/` 目录应包含所有 `.class` 文件。

**Q: 数据文件读取失败？**  
A: 检查 `data/` 目录是否存在，且包含相应的 `.txt` 文件。

**Q: 界面显示乱码？**  
A: 确保系统支持 UTF-8 编码，批处理脚本已自动设置编码。

## 📄 许可证

本项目为课程作业项目，仅供学习和参考使用。

## 👥 贡献

本项目为软件工程课程项目，欢迎提出改进建议。

## 📞 联系方式

如有问题或建议，请通过项目仓库提交 Issue。

---

**最后更新**: 2024年  
**版本**: 1.0  
**开发语言**: Java  
**GUI框架**: Java Swing

