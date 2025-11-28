/**
 * 在线订餐系统 - 应用程序唯一入口
 * OrderingOnline Application - Single Entry Point
 */
public class App {
    
    /**
     * 应用程序主入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 使用 SwingUtilities.invokeLater 确保线程安全
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 启动主界面
                new view.MainFrame();
            }
        });
    }
}

