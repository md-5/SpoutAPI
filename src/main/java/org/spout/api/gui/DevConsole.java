/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.gui;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.chat.ChatArguments;
import org.spout.api.gui.component.LabelComponent;
import org.spout.api.gui.render.RenderPart;
import org.spout.api.math.Rectangle;
import org.spout.api.render.Font;

public class DevConsole {
	private float scroll = 0;
	private Font font;
	private List<Widget> lines = new ArrayList<Widget>();

	public DevConsole(Font font) {
		this.font = font;
	}

	public void appendMessage(ChatArguments msg) {
		Widget wid = new Widget();
		wid.setGeometry(new Rectangle(0, scroll, 0, 0));
		LabelComponent txt = wid.add(LabelComponent.class);

		txt.setFont(font);
		txt.setText(msg);

		scroll -= font.getCharHeight();
		lines.add(wid);
	}

	public List<RenderPart> getRenderParts() {
		List<RenderPart> ret = new ArrayList<RenderPart>();
		for (Widget line : lines) {
			ret.addAll(line.getRenderParts());
		}
		return ret;
	}
}
