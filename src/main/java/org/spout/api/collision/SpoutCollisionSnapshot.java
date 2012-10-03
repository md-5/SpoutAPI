package org.spout.api.collision;

import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.voxel.CollisionSnapshot;

public class SpoutCollisionSnapshot implements CollisionSnapshot {
	//TODO Populate the snapshot with the correct data
	@Override
	public boolean isColliding() {
		return false;
	}

	@Override
	public Object getUserData() {
		return null;
	}

	@Override
	public CollisionShape getCollisionShape() {
		return null;
	}

	@Override
	public Vector3f getCollisionOffset() {
		return null;
	}

	@Override
	public boolean isBlocking() {
		return false;
	}
}
