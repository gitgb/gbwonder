package er.batchfixes;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

/**
 * @author gb
 * 
 */
public class ERBatchFixes extends _ERBatchFixes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	// This logger is from ERBatchFixesParent, so its added to the DB log.
	private static Logger log = Logger.getLogger(ERBatchFixesParent.class);
	
	/**
	 * This sets create and modified dates into newly created entities.
	 * 
	 */
	public void awakeFromInsertion(EOEditingContext ec) {
		// add some newly created initializaton
		super.awakeFromInsertion(ec);
		setRCreateTime(new NSTimestamp());
		setRLastModified(new NSTimestamp()); // TODO add default place?
	}
	
	/**
	 * Called before saveChanges is invoked on super (EOEditingContext). This would allow things such as setting the dateModified before
	 * saving an EO.
	 */
	@Override
	public void willUpdate() { // save modified timestamp
		super.willUpdate();
		setRLastModified(new NSTimestamp());
		return;
	}
	
	
}
