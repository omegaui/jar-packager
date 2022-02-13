/*
 * JarPackagerPlugin
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
import omega.io.IconManager;

import omega.ui.popup.OPopupItem;

import omega.Screen;

import omega.plugin.Plugin;
import omega.plugin.PluginCategory;

import java.util.LinkedList;

import java.net.URL;
public class JarPackagerPlugin implements Plugin{
	
	private JarPackager jarPackager;
	private OPopupItem item;

	public static LinkedList<URL> urls = new LinkedList<>();
	static {
		try{
			urls.add(new URL("https://raw.githubusercontent.com/omegaui/jar-packager/main/images/screenshot0.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean enable() {
		Screen.getScreen().getToolMenu().toolsPopup.addItem(item);
		return true;
	}
	
	@Override
	public boolean disable() {
		Screen.getScreen().getToolMenu().toolsPopup.removeItem("Jar Packager");
		return true;
	}
	
	@Override
	public boolean init() {
		jarPackager = new JarPackager(Screen.getScreen());
		item = new OPopupItem(Screen.getScreen().getToolMenu().toolsPopup, "Jar Packager", IconManager.fluentwindRoseImage, ()->jarPackager.setVisible(true));
		return true;
	}
	
	@Override
	public URL getImage() {
		try{
			return new URL("https://raw.githubusercontent.com/omegaui/omegaide/main/res/fluent-icons/icons8-wind-rose-64.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public LinkedList<URL> getScreenshots() {
		return urls;
	}
	
	@Override
	public String getName() {
		return "Jar Packager";
	}
	
	@Override
	public String getVersion() {
		return "v2.2";
	}

	@Override
	public String getPluginCategory(){
		return PluginCategory.UTILITY;
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
	public String getLicense() {
		return "GNU GPL v3";
	}
	
	@Override
	public String getSizeInMegaBytes() {
		return "0.76 MB";
	}
	
	@Override
	public boolean needsRestart() {
		return false;
	}
}
