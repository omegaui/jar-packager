package omega.jarpackager;
import omega.utils.IconManager;

import java.awt.geom.RoundRectangle2D;

import omega.comp.FlexPanel;
import omega.comp.TextComp;
import omega.comp.NoCaretField;
import omega.comp.EdgeComp;
import omega.comp.SwitchComp;

import javax.swing.JFrame;

import static omega.utils.UIManager.*;
import static omega.comp.Animations.*;
public class JarPackager extends JFrame{
	
	private TextComp titleComp;
	private TextComp iconComp;
	private TextComp dirComp;
	private TextComp classLabelComp;
	private TextComp buildComp;
	private TextComp closeComp;

	private NoCaretField jarNameField;
	private NoCaretField classNameField;
	
	private EdgeComp binLabelComp;
	private EdgeComp resLabelComp;
	private EdgeComp buildEdgeComp;

	private SwitchComp binSwitch;
	private SwitchComp resSwitch;
	
	public JarPackager(){
		super("Jar Packager");
		setUndecorated(true);
		setResizable(false);
		setSize(400, 300);
		setLocationRelativeTo(null);
		FlexPanel panel = new FlexPanel(null, back2, c2);
		panel.setPaintGradientEnabled(true);
		panel.setArc(12, 12);
		setShape(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
		setContentPane(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
		setVisible(true);
	}

	public void init(){
		titleComp = new TextComp("Create Java Artifact", ALPHA, ALPHA, glow, null);
		titleComp.setBounds(30, 0, getWidth() - 30, 30);
		titleComp.setClickable(false);
		titleComp.setArc(0, 0);
		titleComp.setFont(PX14);
		titleComp.attachDragger(this);
		add(titleComp);

		iconComp = new TextComp(IconManager.fluentrocketbuildImage, 25, 25, ALPHA, ALPHA, ALPHA, null);
		iconComp.setBounds(0, 0, 30, 30);
		iconComp.setClickable(false);
		iconComp.setArc(0, 0);
		iconComp.attachDragger(this);
		add(iconComp);

		dirComp = new TextComp(IconManager.fluentplainfolderImage, 25, 25, TOOLMENU_COLOR1_SHADE, ALPHA, ALPHA, null);
		dirComp.setBounds(10, 40, 25, 25);
		dirComp.setArc(2, 2);
		add(dirComp);

		jarNameField = new NoCaretField("", "Jar Name", TOOLMENU_COLOR3, c2, TOOLMENU_COLOR2);
		jarNameField.setBounds(40, 40, getWidth() - 50, 25);
		jarNameField.setFont(PX14);
		add(jarNameField);

		classLabelComp = new TextComp("Main-Class", TOOLMENU_COLOR1_SHADE, c2, TOOLMENU_COLOR1, null);
		classLabelComp.setBounds(10, 75, 100, 25);
		classLabelComp.setClickable(false);
		classLabelComp.setFont(PX14);
		add(classLabelComp);

		classNameField = new NoCaretField("omega.IDE", "Specify Main-Class", TOOLMENU_COLOR2, c2, TOOLMENU_COLOR3);
		classNameField.setBounds(120, 75, getWidth() - 130, 25);
		classNameField.setFont(PX14);
		add(classNameField);

		binLabelComp = new EdgeComp("Include Codes", glow, TOOLMENU_GRADIENT, glow, null);
		binLabelComp.setBounds(10, 110, 145, 25);
		binLabelComp.setFont(PX14);
		binLabelComp.setUseFlatLineAtBack(true);
		add(binLabelComp);

		binSwitch = new SwitchComp(true, TOOLMENU_COLOR4, TOOLMENU_COLOR2, TOOLMENU_COLOR3_SHADE, (value)->{});
		binSwitch.setBounds(200, 107, 100, 30);
		binSwitch.setInBallColor(glow);
		add(binSwitch);

		resLabelComp = new EdgeComp("Include Resources", glow, TOOLMENU_GRADIENT, glow, null);
		resLabelComp.setBounds(10, 145, 145, 25);
		resLabelComp.setFont(PX14);
		resLabelComp.setUseFlatLineAtBack(true);
		add(resLabelComp);

		resSwitch = new SwitchComp(true, TOOLMENU_COLOR4, TOOLMENU_COLOR2, TOOLMENU_COLOR3_SHADE, (value)->{});
		resSwitch.setBounds(200, 142, 100, 30);
		resSwitch.setInBallColor(glow);
		add(resSwitch);

		buildEdgeComp = new EdgeComp("Click Build to Continue", glow, back3, glow, null);
		buildEdgeComp.setBounds(getWidth()/2 - 180, getHeight() - 40, 360, 30);
		buildEdgeComp.setFont(PX14);
		buildEdgeComp.setLookLikeLabel(true);
		add(buildEdgeComp);

		buildComp = new TextComp("Build", TOOLMENU_COLOR2_SHADE, c2, TOOLMENU_COLOR2, null);
		buildComp.setBounds(40, getHeight() - 70 - 25, 100, 25);
		buildComp.setFont(PX14);
		add(buildComp);

		closeComp = new TextComp("Close", TOOLMENU_COLOR2_SHADE, c2, TOOLMENU_COLOR2, ()->setVisible(false));
		closeComp.setBounds(getWidth() - 40 - 100, getHeight() - 70 - 25, 100, 25);
		closeComp.setFont(PX14);
		add(closeComp);
	}

	public static void main(String[] args){
		new JarPackager();
	}	
}
