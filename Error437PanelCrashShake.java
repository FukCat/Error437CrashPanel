// Decompiled with: Procyon 0.6.0
// Class Version: 8
// Load 437deobf.jar
import net.minecraft.client.Minecraft;
import java.awt.Rectangle;
import java.awt.Frame;

public class Error437PanelCrashShake extends Thread //加载437游戏窗口摇晃的线程
{
    private Frame mcframe;
    private Rectangle angleRect;
    
    public Error437PanelCrashShake(Frame mcframe) {
        this.mcframe = mcframe;
        this.angleRect = mcframe.getBounds();
    }
    
    @Override
    public fvoid run() {
        while (true) {
            Rectangle bounds;
            Rectangle rectangle = bounds = (Rectangle)this.angleRect.clone();
            rectangle.x += (Minecraft.clientRand.nextBoolean() ? -10 : 10);
            Rectangle rectangle2 = bounds;
            rectangle2.y += (Minecraft.clientRand.nextBoolean() ? -10 : 10);
            this.mcframe.setBounds(bounds);
        }
    }
}
