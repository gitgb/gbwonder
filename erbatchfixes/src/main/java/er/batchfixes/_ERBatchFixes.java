// DO NOT EDIT.  Make changes to ERBatchFixes.java instead.
package er.batchfixes;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _ERBatchFixes extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "ERBatchFixes";

  // Attribute Keys
  public static final ERXKey<Boolean> FINISHED = new ERXKey<Boolean>("finished");
  public static final ERXKey<String> FIX_LOG = new ERXKey<String>("fixLog");
  public static final ERXKey<String> FIXNAME = new ERXKey<String>("fixname");
  public static final ERXKey<NSTimestamp> R_CREATE_TIME = new ERXKey<NSTimestamp>("rCreateTime");
  public static final ERXKey<NSTimestamp> R_LAST_MODIFIED = new ERXKey<NSTimestamp>("rLastModified");
  public static final ERXKey<Boolean> STARTED = new ERXKey<Boolean>("started");
  // Relationship Keys

  // Attributes
  public static final String FINISHED_KEY = FINISHED.key();
  public static final String FIX_LOG_KEY = FIX_LOG.key();
  public static final String FIXNAME_KEY = FIXNAME.key();
  public static final String R_CREATE_TIME_KEY = R_CREATE_TIME.key();
  public static final String R_LAST_MODIFIED_KEY = R_LAST_MODIFIED.key();
  public static final String STARTED_KEY = STARTED.key();
  // Relationships

  private static Logger LOG = Logger.getLogger(_ERBatchFixes.class);

  public ERBatchFixes localInstanceIn(EOEditingContext editingContext) {
    ERBatchFixes localInstance = (ERBatchFixes)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean finished() {
    return (Boolean) storedValueForKey(_ERBatchFixes.FINISHED_KEY);
  }

  public void setFinished(Boolean value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating finished from " + finished() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.FINISHED_KEY);
  }

  public String fixLog() {
    return (String) storedValueForKey(_ERBatchFixes.FIX_LOG_KEY);
  }

  public void setFixLog(String value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating fixLog from " + fixLog() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.FIX_LOG_KEY);
  }

  public String fixname() {
    return (String) storedValueForKey(_ERBatchFixes.FIXNAME_KEY);
  }

  public void setFixname(String value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating fixname from " + fixname() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.FIXNAME_KEY);
  }

  public NSTimestamp rCreateTime() {
    return (NSTimestamp) storedValueForKey(_ERBatchFixes.R_CREATE_TIME_KEY);
  }

  public void setRCreateTime(NSTimestamp value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating rCreateTime from " + rCreateTime() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.R_CREATE_TIME_KEY);
  }

  public NSTimestamp rLastModified() {
    return (NSTimestamp) storedValueForKey(_ERBatchFixes.R_LAST_MODIFIED_KEY);
  }

  public void setRLastModified(NSTimestamp value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating rLastModified from " + rLastModified() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.R_LAST_MODIFIED_KEY);
  }

  public Boolean started() {
    return (Boolean) storedValueForKey(_ERBatchFixes.STARTED_KEY);
  }

  public void setStarted(Boolean value) {
    if (_ERBatchFixes.LOG.isDebugEnabled()) {
    	_ERBatchFixes.LOG.debug( "updating started from " + started() + " to " + value);
    }
    takeStoredValueForKey(value, _ERBatchFixes.STARTED_KEY);
  }


  public static ERBatchFixes createERBatchFixes(EOEditingContext editingContext, String fixname
) {
    ERBatchFixes eo = (ERBatchFixes) EOUtilities.createAndInsertInstance(editingContext, _ERBatchFixes.ENTITY_NAME);    
		eo.setFixname(fixname);
    return eo;
  }

  public static ERXFetchSpecification<ERBatchFixes> fetchSpec() {
    return new ERXFetchSpecification<ERBatchFixes>(_ERBatchFixes.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<ERBatchFixes> fetchAllERBatchFixeses(EOEditingContext editingContext) {
    return _ERBatchFixes.fetchAllERBatchFixeses(editingContext, null);
  }

  public static NSArray<ERBatchFixes> fetchAllERBatchFixeses(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _ERBatchFixes.fetchERBatchFixeses(editingContext, null, sortOrderings);
  }

  public static NSArray<ERBatchFixes> fetchERBatchFixeses(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<ERBatchFixes> fetchSpec = new ERXFetchSpecification<ERBatchFixes>(_ERBatchFixes.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<ERBatchFixes> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static ERBatchFixes fetchERBatchFixes(EOEditingContext editingContext, String keyName, Object value) {
    return _ERBatchFixes.fetchERBatchFixes(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ERBatchFixes fetchERBatchFixes(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<ERBatchFixes> eoObjects = _ERBatchFixes.fetchERBatchFixeses(editingContext, qualifier, null);
    ERBatchFixes eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ERBatchFixes that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ERBatchFixes fetchRequiredERBatchFixes(EOEditingContext editingContext, String keyName, Object value) {
    return _ERBatchFixes.fetchRequiredERBatchFixes(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ERBatchFixes fetchRequiredERBatchFixes(EOEditingContext editingContext, EOQualifier qualifier) {
    ERBatchFixes eoObject = _ERBatchFixes.fetchERBatchFixes(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ERBatchFixes that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ERBatchFixes localInstanceIn(EOEditingContext editingContext, ERBatchFixes eo) {
    ERBatchFixes localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
