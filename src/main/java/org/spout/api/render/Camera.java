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

import org.spout.api.math.Matrix;
import org.spout.api.math.Vector3;

/**
 * Represents a Camera to be used for rendering.
 *
 */
public interface Camera {
	/**
	 * Get the projection matrix associated with this camera
	 * @return 4x4 matrix representing the projection
	 */
	public Matrix getProjection();
	
	/**
	 * Gets the view matrix
	 * @return
	 */
	public Matrix getView();
	
	/**
	 * Update the view matrix.  
	 */
	public void updateView();
	
	/**
	 * Gets the view frustum of this Camera.
	 * 
	 * @return
	 */
	public ViewFrustum getFrustum();
	
	public void setSpeed(float speed);
	
	public void setSpeed(Vector3 speed);
	
	public Vector3 getSpeed();
}
