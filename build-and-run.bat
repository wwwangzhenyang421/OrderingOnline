@echo off
chcp 65001 >nul
echo ========================================
echo   在线订餐系统 - 一键编译并运行
echo   OrderingOnline - Build and Run
echo ========================================
echo.

call compile.bat
if %errorlevel% neq 0 (
    pause
    exit /b 1
)

echo.
echo 启动应用程序...
echo Starting application...
echo.

call run.bat

