@echo off
chcp 65001 >nul
echo 启动在线订餐系统...
echo Starting OrderingOnline Application...
echo.

if not exist "bin\App.class" (
    echo 错误：未找到编译文件，请先运行 compile.bat 进行编译
    echo Error: Compiled files not found, please run compile.bat first
    pause
    exit /b 1
)

cd /d "%~dp0"
java -cp bin App

if %errorlevel% neq 0 (
    echo.
    echo 程序运行出错
    echo Application error occurred
    pause
)

