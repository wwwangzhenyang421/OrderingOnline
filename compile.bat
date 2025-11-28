@echo off
chcp 65001 >nul
echo 正在编译项目...
echo Compiling project...

if not exist "bin" mkdir bin

javac -encoding UTF-8 -d bin -sourcepath src src\App.java src\view\*.java src\controller\*.java src\model\*.java

if %errorlevel% equ 0 (
    echo.
    echo ✓ 编译成功！
    echo ✓ Compilation successful!
) else (
    echo.
    echo ✗ 编译失败，请检查错误信息
    echo ✗ Compilation failed, please check the error messages
    exit /b 1
)

pause

