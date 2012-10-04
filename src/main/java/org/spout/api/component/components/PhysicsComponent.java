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
package org.spout.api.component.components;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

import org.spout.api.data.Data;
import org.spout.api.entity.Entity;
import org.spout.api.geo.discrete.Point;
import org.spout.api.math.MathHelper;
import org.spout.api.math.Vector3;

/**
 * A component that represents the physics object that is a motion of the entity within the world.
 *
 * 	TODO Not thread safe at all...
 */
public class PhysicsComponent extends EntityComponent {
	private float mass;
	private CollisionObject collisionObject = new CollisionObject();
	private CollisionObject collisionObjectLive = new CollisionObject();
	private MotionState state;

	private Vector3 angularVelocity;
	private Vector3 angularVelocityLive;
	private Vector3 linearVelocity;
	private Vector3 linearVelocityLive;

	@Override
	public void onAttached() {
		state = new SpoutDefaultMotionState(getHolder());
	}

	@Override
	public boolean isDetachable() {
		return false;
	}

	public CollisionObject getCollisionObject() {
		return collisionObject;
	}

	public CollisionObject getCollisionObjectLive() {
		return collisionObjectLive;
	}

	public void setCollisionObject(CollisionObject collisionObject) {
		if (collisionObject == null) {
			throw new IllegalStateException("Collision object is NOT allowed to be null!");
		}
		this.collisionObjectLive = collisionObject;
	}

	public CollisionShape getCollisionShape() {
		return collisionObject.getCollisionShape();
	}

	public void setCollisionShape(CollisionShape shape) {
		collisionObject.setCollisionShape(shape);
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public Vector3 getAngularVelocity() {
		return angularVelocity;
	}

	public Vector3 getAngularVelocityLive() {
		return angularVelocityLive;
	}

	public Vector3 getLinearVelocity() {
		return linearVelocity;
	}

	public Vector3 getLinearVelocityLive() {
		return linearVelocityLive;
	}

	public void setAngularVelocity(Vector3 velocity) {
		angularVelocityLive = velocity;
	}

	public void setLinearVelocity(Vector3 velocity) {
		linearVelocityLive = velocity;
	}

	public void setVelocity(Vector3 velocity) {
		setAngularVelocity(velocity);
		setLinearVelocity(velocity);
	}

	public MotionState getMotionState() {
		return state;
	}

	public void setMotionState(MotionState state) {
		this.state = state;
	}

	public boolean isVelocityDirty() {
		return !angularVelocity.equals(angularVelocityLive) && !linearVelocity.equals(linearVelocityLive);
	}

	public boolean isCollisionObjectDirty() {
		return !collisionObject.equals(collisionObjectLive);
	}

	public void copySnapshot() {
		angularVelocity = angularVelocityLive;
		linearVelocity = linearVelocityLive;
	}

	public void updateCollisionVelocity() {
		getCollisionObject().setInterpolationAngularVelocity(MathHelper.toVector3f(angularVelocityLive));
		getCollisionObject().setInterpolationLinearVelocity(MathHelper.toVector3f(linearVelocityLive));
	}

	//TODO Thread safety!! I think
	private static class SpoutDefaultMotionState extends DefaultMotionState {
		private final Entity entity;

		public SpoutDefaultMotionState(Entity entity) {
			this.entity = entity;
		}

		@Override
		public Transform getWorldTransform(Transform transform) {
			org.spout.api.geo.discrete.Transform spoutTransform = entity.getTransform().getTransformLive();
			transform.set(new Matrix4f(MathHelper.toQuaternionf(spoutTransform.getRotation()), MathHelper.toVector3f(spoutTransform.getPosition()), 1));
			return transform;
		}

		@Override
		public void setWorldTransform(Transform transform) {
			org.spout.api.geo.discrete.Transform spoutTransform = entity.getTransform().getTransformLive();
			spoutTransform.setPosition(new Point(MathHelper.toVector3(transform.origin), entity.getWorld()));
			spoutTransform.setRotation(MathHelper.toQuaternion(transform.getRotation(new Quat4f())));
		}
	}
}