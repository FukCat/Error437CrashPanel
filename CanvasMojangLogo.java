// Decompiled with: Procyon 0.6.0
// Class Version: 8
// Load 437deobf.jar
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

class CanvasMojangLogo extends Canvas //加载崩溃界面的logo
{
    private BufferedImage logo;
    
    public CanvasMojangLogo() {
        try {
            this.logo = ImageIO.read(PanelCrashReport.class.getResource(Minecraft.e437crash_eventDeath ? "/gui/crash_logo2.png" : "/gui/crash_logo.png"));
        }//控制正常的logo和事件的logo
        catch (IOException ex) {}
        this.setPreferredSize(new Dimension(100, 100));
        this.setMinimumSize(new Dimension(100, 100));
    }
    
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(this.logo, this.getWidth() / 2 - this.logo.getWidth() / 2, 32, null);
    }
}
