package omega.jarpackager;
import omega.popup.OPopupItem;

import omega.Screen;

import java.util.LinkedList;

import java.awt.image.BufferedImage;

import omega.utils.ToolMenu;
import omega.utils.IconManager;


import omega.plugin.Plugin;
public class JarPackagerPlugin implements Plugin{
	
	private JarPackager jarPackager;
	private OPopupItem item;
	
	@Override
	public void enable() {
		Screen.getScreen().getToolMenu().toolsPopup.addItem(item);
	}
	
	@Override
	public void disable() {
		Screen.getScreen().getToolMenu().toolsPopup.removeItem("Jar Packager");
	}
	
	@Override
	public void init() {
		jarPackager = new JarPackager(getIDE());
		item = new OPopupItem(Screen.getScreen().getToolMenu().toolsPopup, "Jar Packager", IconManager.fluentrocketbuildImage, ()->jarPackager.setVisible(true));
	}
	
	@Override
	public BufferedImage getImage() {
		return IconManager.fluentrocketbuildImage;
	}
	
	@Override
	public LinkedList getImages() {
		return null;
	}
	
	@Override
	public String getName() {
		return "Jar Packager";
	}
	
	@Override
	public String getVersion() {
		return "v2.1";
	}
	
	@Override
	public String getDescription() {
		return "Simple Jar Packager";
	}
	
	@Override
	public String getAuthor() {
		return "Omega UI";
	}
	
	@Override
	public String getCopyright() {
		return "Copyright (C) 2021 Omega UI. All Right Reserved.";
	}
	
	@Override
	public String getSize() {
		return "7.6 KB";
	}
	
	@Override
	public boolean needsRestart() {
		return false;
	}
}
