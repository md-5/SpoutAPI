package org.spout.api.component;

import org.spout.api.component.components.DatatableComponent;
import org.spout.api.tickable.Tickable;

public interface Component extends Tickable {
	/**
	 * Attaches to a component holder.
	 * @param holder the componet holder to attach to
	 * @return true if successful
	 */
	public boolean attachTo(ComponentHolder holder);

	/**
	 * Gets the component holder that is holding this component.
	 * @return the component holder
	 */
	public ComponentHolder getHolder();

	/**
	 * Called when this component is attached to a holder.
	 */
	public void onAttached();
	/**
	 * Called when this component is detached from a holder.
	 */
	public void onDetached();

	/**
	 * Specifies whether or not this component can be detached,
	 * after it has already been attached to a holder.
	 * @return true if it can be detached
	 */
	public boolean isDetachable();

	/**
	 * Called when the holder is set to be synchronized.
	 * <p/>
	 * This method is READ ONLY. You cannot update in this method.
	 */
	public void onSync();

	/**
	 * Gets the datatable component attached to the holder.
	 * This component exists in every holder.
	 * @return the datatable component
	 */
	public DatatableComponent getData();
}
