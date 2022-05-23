/*
 * JarPackager
 * Copyright (C) 2022 Omega UI

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package omega.jarpackager;
import java.awt.Frame;

import omega.instant.support.java.parser.JavaSyntaxParser;

import omega.ui.dialog.FileSelectionDialog;

import omega.io.IconManager;

import omegaui.component.TextComp;
import omegaui.component.NoCaretField;
import omegaui.component.EdgeComp;
import omegaui.component.SwitchComp;
import omegaui.component.FlexPanel;

import java.util.LinkedList;
import java.util.Enumeration;

import omega.Screen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JDialog;

import static omega.io.UIManager.*;
import static omegaui.component.animation.Animations.*;
public class JarPackager extends JDialog{
	
	private TextComp titleComp;
	private TextComp iconComp;
	private TextComp dirComp;
	private TextComp classLabelComp;
	private TextComp buildComp;
	private TextComp closeComp;

	private NoCaretField jarNameField;
	private NoCaretField classNameField;
	
	private EdgeComp libLabelComp;
	private EdgeComp resLabelComp;
	private EdgeComp manifestLabelComp;
	private EdgeComp buildEdgeComp;

	private SwitchComp libSwitch;
	private SwitchComp resSwitch;
	private SwitchComp manifestSwitch;
	
	public JarPackager(Frame frame){
		super(frame, true);
		setTitle("Jar Packager");
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
	}

	public void init(){
		titleComp = new TextComp("Create Java Artifact", ALPHA, ALPHA, glow, null);
		titleComp.setBounds(30, 0, getWidth() - 30, 30);
		titleComp.setClickable(false);
		titleComp.setArc(0, 0);
		titleComp.setFont(PX14);
		titleComp.attachDragger(this);
		add(titleComp);

		iconComp = new TextComp(IconManager.fluentwindRoseImage, 25, 25, ALPHA, ALPHA, ALPHA, null);
		iconComp.setBounds(0, 0, 30, 30);
		iconComp.setClickable(false);
		iconComp.setArc(0, 0);
		iconComp.attachDragger(this);
		add(iconComp);

		FileSelectionDialog fs = new FileSelectionDialog(Screen.getScreen());
		fs.setTitle("Select a Directory");
		fs.setCurrentDirectory(new File(Screen.getProjectFile().getProjectPath() + File.separator + "out"));

		dirComp = new TextComp(IconManager.fluentpackageImage, 25, 25, TOOLMENU_COLOR1_SHADE, ALPHA, ALPHA, ()->{
			LinkedList<File> files = fs.selectDirectories();
			if(!files.isEmpty()){
				dirComp.setToolTipText(files.getFirst().getAbsolutePath());
			}
		});
		dirComp.setBounds(10, 40, 25, 25);
		dirComp.setArc(2, 2);
		dirComp.setToolTipText(Screen.getProjectFile().getProjectPath() + File.separator + "out");
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

		libLabelComp = new EdgeComp("Include Libraries", glow, TOOLMENU_GRADIENT, glow, null);
		libLabelComp.setBounds(10, 110, 145, 25);
		libLabelComp.setFont(PX14);
		libLabelComp.setUseFlatLineAtBack(true);
		add(libLabelComp);

		libSwitch = new SwitchComp(true, TOOLMENU_COLOR4, TOOLMENU_COLOR2, TOOLMENU_COLOR3_SHADE, (value)->{});
		libSwitch.setBounds(200, 107, 100, 30);
		libSwitch.setInBallColor(TOOLMENU_GRADIENT);
		add(libSwitch);

		resLabelComp = new EdgeComp("Include Resources", glow, TOOLMENU_GRADIENT, glow, null);
		resLabelComp.setBounds(10, 145, 145, 25);
		resLabelComp.setFont(PX14);
		resLabelComp.setUseFlatLineAtBack(true);
		add(resLabelComp);

		resSwitch = new SwitchComp(true, TOOLMENU_COLOR4, TOOLMENU_COLOR2, TOOLMENU_COLOR3_SHADE, (value)->{});
		resSwitch.setBounds(200, 142, 100, 30);
		resSwitch.setInBallColor(TOOLMENU_GRADIENT);
		add(resSwitch);

		manifestLabelComp = new EdgeComp("Include Manifest", glow, TOOLMENU_GRADIENT, glow, null);
		manifestLabelComp.setBounds(10, 180, 145, 25);
		manifestLabelComp.setFont(PX14);
		manifestLabelComp.setUseFlatLineAtBack(true);
		add(manifestLabelComp);

		manifestSwitch = new SwitchComp(true, TOOLMENU_COLOR4, TOOLMENU_COLOR2, TOOLMENU_COLOR3_SHADE, (value)->{});
		manifestSwitch.setBounds(200, 177, 100, 30);
		manifestSwitch.setInBallColor(TOOLMENU_GRADIENT);
		add(manifestSwitch);

		buildEdgeComp = new EdgeComp("Click Build to Continue", glow, back3, glow, null);
		buildEdgeComp.setBounds(getWidth()/2 - 180, getHeight() - 40, 360, 30);
		buildEdgeComp.setFont(PX14);
		buildEdgeComp.setLookLikeLabel(true);
		add(buildEdgeComp);

		buildComp = new TextComp("Build", TOOLMENU_COLOR5_SHADE, TOOLMENU_COLOR5_SHADE, TOOLMENU_COLOR5, this::build);
		buildComp.setBounds(40, getHeight() - 70 - 12, 100, 25);
		buildComp.setFont(PX14);
		add(buildComp);

		closeComp = new TextComp("Close", TOOLMENU_COLOR2_SHADE, TOOLMENU_COLOR2_SHADE, TOOLMENU_COLOR2, ()->setVisible(false));
		closeComp.setBounds(getWidth() - 40 - 100, getHeight() - 70 - 12, 100, 25);
		closeComp.setFont(PX14);
		add(closeComp);
	}

	public void build(){
		if(!Screen.isNotNull(jarNameField.getText())){
			buildEdgeComp.setText("Specify Jar Name");
			return;
		}

		if(!new File(dirComp.getToolTipText()).exists()){
			buildEdgeComp.setText("Folder Doesn't Exists");
			return;
		}
		new Thread(()->{
			buildEdgeComp.setText("Starting Build ...");
	
			try{
				File targetZipFile = new File(dirComp.getToolTipText() + File.separator + jarNameField.getText());
				
				targetZipFile.delete();
				
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetZipFile));
				LinkedList<String> byteCodes = new LinkedList<>();
				JavaSyntaxParser.loadFiles(byteCodes, new File(Screen.getProjectFile().getProjectPath() + File.separator + "bin"), ".class");
				if(!byteCodes.isEmpty()){
					for(String path : byteCodes){
						setProgress("Adding Byte Codes", ((byteCodes.indexOf(path) + 1) * 100) / byteCodes.size());
						add(new File(path), zos, Screen.getProjectFile().getProjectPath() + File.separator + "bin");
					}
				}
				if(libSwitch.isOn()){
					if(!Screen.getProjectFile().getProjectManager().jars.isEmpty()){
						for(String path : Screen.getProjectFile().getProjectManager().jars){
							setProgress("Adding Libraries", ((Screen.getProjectFile().getProjectManager().jars.indexOf(path) + 1) * 100) / Screen.getProjectFile().getProjectManager().jars.size());
							ZipFile libFile = new ZipFile(new File(path));
							Enumeration entries = libFile.entries();
							while(entries.hasMoreElements()){
								ZipEntry entry = (ZipEntry)entries.nextElement();
								entry.setCompressedSize(-1);
								add(libFile.getInputStream(entry), zos, entry);
							}
							libFile.close();
						}
					}
				}
				
				if(resSwitch.isOn()){
					if(!Screen.getProjectFile().getProjectManager().resourceRoots.isEmpty()){
						for(String resPath : Screen.getProjectFile().getProjectManager().resourceRoots){
							LinkedList<String> resources = new LinkedList<>();
							JavaSyntaxParser.loadFiles(resources, new File(resPath), "");
							if(!resources.isEmpty()){
								for(String path : resources){
									setProgress("Adding Resource Roots (" + (Screen.getProjectFile().getProjectManager().resourceRoots.indexOf(resPath) + 1) + " of " + Screen.getProjectFile().getProjectManager().resourceRoots.size() + ")", ((resources.indexOf(path) + 1) * 100) / resources.size());
									add(new File(path), zos, resPath);
								}
							}
						}
					}
				}

				if(manifestSwitch.isOn()){
					buildEdgeComp.setText("Generating Manifest ...");
					String manifestInformation = "Manifest-Version: 1.0";
					if(Screen.isNotNull(Screen.getProjectRunner().mainClass)){
						manifestInformation += "\nMain-Class: " + Screen.getProjectRunner().mainClass + "\n";
					}
					ZipEntry entryX = new ZipEntry("META-INF/MANIFEST.MF");
					entryX.setCompressedSize(-1);
					zos.putNextEntry(entryX);
					zos.write(manifestInformation.getBytes());
					zos.flush();
					zos.closeEntry();
				}
				
				zos.finish();
				zos.close();
				buildEdgeComp.setText("Jar Created Successfully!");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}).start();
	}

	public void add(File file, ZipOutputStream zos, String parentPath){
		try{
			String name = file.getAbsolutePath();
			name = name.substring(parentPath.length() + 1);
			ZipEntry entry = new ZipEntry(name);
			entry.setCompressedSize(-1);
			zos.putNextEntry(entry);
			InputStream ins = new FileInputStream(file);
			while(ins.available() > 0){
				zos.write(ins.read());
			}
			zos.flush();
			zos.closeEntry();
			ins.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void add(InputStream ins, ZipOutputStream zos, ZipEntry entry){
		if(entry.getName().contains("META-INF"))
			return;
		try{
			zos.putNextEntry(entry);
			while(ins.available() > 0){
				zos.write(ins.read());
			}
			zos.flush();
			zos.closeEntry();
			ins.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setProgress(String status, int progress){
		buildEdgeComp.setText(status + " " + progress + "%");
	}
}
