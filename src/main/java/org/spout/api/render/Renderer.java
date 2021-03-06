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
package org.spout.api.render;

public interface Renderer {

	/**
	 * Begins batching calls
	 * 
	 */
	public abstract void begin();

	/**
	 * Ends batching and flushes cache to the GPU
	 */
	public abstract void end();

	/**
	 * Renders the batch.
	 * @param material RenderMaterial to render with
	 */
	public abstract void render(RenderMaterial material);

	/**
	 * Renders the batch with a subset of the verticies in the buffer.  
	 * @param material
	 * @param startVert
	 * @param endVert
	 */
	public abstract void render(RenderMaterial material, int startVert, int endVert);

}