package omega.utils;
import com.formdev.flatlaf.*;
import java.awt.*;
public class UIManager {
	
	public static Color c1 = new Color(0, 0, 255, 40);
	public static Color c2 = Color.WHITE;
     public static Color c3 = new Color(0, 0, 255, 160);
     public static Color glow = Color.BLACK;
	public static Color TOOLMENU_COLOR1 = new Color(26, 36, 219);
     public static Color TOOLMENU_COLOR1_SHADE = new Color(26, 36, 219, 40);
     public static Color TOOLMENU_COLOR2 = new Color(223, 33, 15);
     public static Color TOOLMENU_COLOR2_SHADE = new Color(223, 33, 15, 40);
     public static Color TOOLMENU_COLOR3 = new Color(126, 20, 219);
     public static Color TOOLMENU_COLOR3_SHADE = new Color(126, 20, 219, 40);
     public static Color TOOLMENU_COLOR4 = new Color(200, 103, 0);
     public static Color TOOLMENU_COLOR4_SHADE = new Color(200, 103, 0, 40);
     public static Color TOOLMENU_GRADIENT = new Color(200, 200, 200, 100);
	public static Color back1 = Color.decode("#dddddd");
	public static Color back2 = Color.decode("#eeeeee");
	public static Color back3 = Color.decode("#D3D4D4");
     public static Color ALPHA = new Color(0, 0, 0, 0);
	static{
		try{
               FlatLightLaf.install();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, UIManager.class.getResourceAsStream("/UbuntuMono-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, UIManager.class.getResourceAsStream("/Ubuntu-Bold.ttf")));
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public static final Font PX12 = new Font("Ubuntu Mono", Font.BOLD, 12);
	public static final Font PX14 = new Font("Ubuntu Mono", Font.BOLD, 14);
	public static final Font PX16 = new Font("Ubuntu Mono", Font.BOLD, 16);
	public static final Font PX18 = new Font("Ubuntu Mono", Font.BOLD, 18);
	public static final Font PX20 = new Font("Ubuntu Mono", Font.BOLD, 20);
	public static final Font PX22 = new Font("Ubuntu Mono", Font.BOLD, 22);
	public static final Font PX26 = new Font("Ubuntu Mono", Font.BOLD, 26);
	public static final Font PX28 = new Font("Ubuntu Mono", Font.BOLD, 28);
	public static final Font PX40 = new Font("Ubuntu Mono", Font.BOLD, 40);
	public static final Font UBUNTU_PX12 = new Font("Ubuntu", Font.BOLD, 12);
	public static final Font UBUNTU_PX14 = new Font("Ubuntu", Font.BOLD, 14);
	public static final Font UBUNTU_PX16 = new Font("Ubuntu", Font.BOLD, 16);

     public static boolean isDarkMode(){
     	return false;
     }
}
