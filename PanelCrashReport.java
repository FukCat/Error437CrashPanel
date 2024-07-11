// Decompiled with: Procyon 0.6.0
// Class Version: 8
// Load 437deobf.jar
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.io.File;
import org.lwjgl.opengl.GL11;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import java.awt.Panel;

public class PanelCrashReport extends Panel //崩溃后的面板
{
    public PanelCrashReport(CrashReport crashReport) {
        this.setBackground(new Color(Minecraft.e437crash_eventDeath ? 0 : 3028036));
        //背景的颜色,崩溃后变成红色的背景.其中的e437crash_eventDeath用来控制事件(死亡后会开启)
        this.setLayout(new BorderLayout());
        String s = "";
        String s5;
        if (!Minecraft.e437crash_eventDeath) {//这里没开启的话需要正常显示报错日志
            StringWriter stringWriter = new StringWriter();
            crashReport.func_71505_b().printStackTrace(new PrintWriter(stringWriter));
            String string = stringWriter.toString();
            String glGetString = "";
            String s2 = "";
            try {
                s2 = String.valueOf(s2) + "Generated " + new SimpleDateFormat().format(new Date()) + "\n";
                s2 = String.valueOf(s2) + "\n";
                StringBuilder sb = new StringBuilder(String.valueOf(s2));
                StringBuilder sb2 = new StringBuilder();
                crashReport.getSections(sb2);
                s2 = sb.append(sb2.toString()).toString();
                glGetString = GL11.glGetString(7936);
            }
            catch (Throwable t) {
                s2 = String.valueOf(s2) + "[failed to get system properties (" + t + ")]\n";
            }
            String string2 = String.valueOf(new StringBuilder(String.valueOf(s2)).append("\n\n").toString()) + string;
            String string3 = String.valueOf(new StringBuilder(String.valueOf(s)).append("\n").toString()) + "\n";
            String s3;
            if (string.contains("Pixel format not accelerated")) {
                s3 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(string3)).append("      Bad video card drivers!      \n").toString())).append("      -----------------------      \n").toString())).append("\n").toString())).append("Minecraft was unable to start because it failed to find an accelerated OpenGL mode.\n").toString()) + "This can usually be fixed by updating the video card drivers.\n";
                if (glGetString.toLowerCase().contains("nvidia")) {
                    s3 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(s3)).append("\n").toString())).append("You might be able to find drivers for your video card here:\n").toString()) + "  http://www.nvidia.com/\n";
                }
                else if (glGetString.toLowerCase().contains("ati")) {
                    s3 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(s3)).append("\n").toString())).append("You might be able to find drivers for your video card here:\n").toString()) + "  http://www.amd.com/\n";
                }
            }
            else {
                String string4 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(string3)).append("      Minecraft has crashed!      \n").toString())).append("      ----------------------      \n").toString())).append("\n").toString()) + "Minecraft has stopped running because it encountered a problem; " + crashReport.getDescription() + "\n";
                File file;
                if ((file = crashReport.getFile()) == null) {
                    crashReport.saveToFile(new File(new File(Minecraft.getMinecraftDir(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt"));
                    file = crashReport.getFile();
                }
                String s4;
                if (file != null) {
                    s4 = String.valueOf(string4) + "This error has been saved to " + file.getAbsolutePath() + " for your convenience. Please include a copy of this file if you report this crash to anyone.";
                }
                else {
                    s4 = String.valueOf(string4) + "We were unable to save this report to a file.";
                }
                s3 = String.valueOf(s4) + "\n";
            }
            String string5 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(s3)).append("\n").toString())).append("\n").toString()) + "\n";
            String string6 = String.valueOf(new StringBuilder(String.valueOf(string5)).append("--- BEGIN ERROR REPORT ").append(Integer.toHexString(string5.hashCode())).append(" --------\n").toString()) + string2;
            s5 = String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(string6)).append("--- END ERROR REPORT ").append(Integer.toHexString(string6.hashCode())).append(" ----------\n").toString())).append("\n").toString()) + "\n";
        }
        else {//控制崩溃后的样子
            StringBuilder sb3 = new StringBuilder("\r\n **** ***  ***   ***  ***     **  ***  *****\r\n *    *  * *  * *   * *  *   * * *   * *   *\r\n *    *  * *  * *   * *  *  *  *     *     *\r\n **** ***  ***  *   * ***  *   *   **     *\r\n *    *  * *  * *   * *  * *****     *   *\r\n *    *  * *  * *   * *  *     * *   *   *\r\n **** *  * *  *  ***  *  *     *  ***    *\r\n\r\n");
            for (int i = 0; i < 32767; i = (short)(i + 1)) {//加载error437的logo
                sb3.append((char)Minecraft.clientRand.nextInt());//生成随机字符
            }
            s5 = sb3.toString();
        }
        TextArea textArea;
        (textArea = new TextArea(s5, 0, 0, 1)).setFont(new Font("Monospaced", 0, 12));
        if (Minecraft.e437crash_eventDeath) {
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(new Color(6684672));//设置文字颜色和背景颜色
            Minecraft.x();
            new Error437PanelCrashShake(Minecraft.mcFrame).start();//使得游戏窗口摇晃,注意mcFrame需要单独弄一个public变量
        }
        this.add(new CanvasMojangLogo(), "North");
        this.add(new CanvasCrashReport(80), "East");
        this.add(new CanvasCrashReport(80), "West");
        this.add(new CanvasCrashReport(100), "South");
        this.add(textArea, "Center");
    }
}
