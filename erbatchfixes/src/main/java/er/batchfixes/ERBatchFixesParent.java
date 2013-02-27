package er.batchfixes;

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.StringWriter;
import java.io.Writer;
import er.extensions.eof.ERXEC;

import org.apache.log4j.Layout;
import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author gb A parent class from which your batch job/fixer should inherit from; implement the abstract doit() method, and the records in the db table _ERBatchFixes will
 *         record which fixes have been done, so they will not be attempted if completed. If the fix has been applied, the constructor will throw ERBatchFixFinished and kick
 *         you out.<p/>
 *         There is also a log4j logger, "log", the parent creates. It is setup to store only 10,000,000 (or 10.000.000) bytes of logging information with the batch job.
 *  	   Throws IllegalStateException if you try to use the parent without the system property er.batchfixes.dofixes = true.
 */
public abstract class ERBatchFixesParent {

	protected static Logger log = Logger.getLogger(ERBatchFixesParent.class);
	private boolean done = true; // state of done-ness; 
	private String _fixname = null; // remember name
	private EOEditingContext ec = ERXEC.newEditingContext(); // only for parent stuff, get your own!
	private ERBatchFixes fixrecord = null; // db record of fixes
	protected ERBatchFixesAppender appender = new ERBatchFixesAppender() ;


	/**
	 * Made to be called by its children, Fix0, Fix1, etc.  If the fix "fixname" has already been recorded as finished, you will
	 * not be able to construct one of these, as it will throw a BatchFixFinished exception.
	 * 
	 * @param fixname
	 *            - name of fix, eg, Fix0
	 * @throws Exception 
	 * 
	 */
	public ERBatchFixesParent(String fixname) throws ERBatchFixFinished, IllegalStateException {
		_fixname = fixname;

		// check props, do they want fixes?
		String propflag = System.getProperty("er.batchfixes.dofixes");
		if( !(propflag != null && propflag.equalsIgnoreCase("true")) ) throw new IllegalStateException(); // don't ask if you don't want

		log.addAppender(appender); // add our appender to this logger
		log.debug("Application is checking to see if " + fixname + " has been done");

		try { // examine or create fixrecord
			fixrecord = ERBatchFixes.fetchRequiredERBatchFixes(ec, ERBatchFixes.FIXNAME.eq(fixname));
		} catch (NoSuchElementException no) {
			// well, better start one, create a record
			fixrecord = ERBatchFixes.createERBatchFixes(ec, fixname);
			fixrecord.setFinished(false);
			fixrecord.setStarted(true);
			log.info("Will execute " + _fixname);
			done = false;
		} catch (IllegalStateException illegal) { // too many?
			log.error("Database has multiple fix records for " + fixname, illegal);
			done = true; // prevent running the doit
			throw illegal ; // we are outta here
		}  



		if (fixrecord.finished()) {
			done = true;
			log.info(fixname + " has already been finished.");
			throw  new ERBatchFixFinished(fixname + " has already been finished."); // we are done here
		} else {
			log.info("Will execute " + fixname);
			done = false;				
		}

		if(fixrecord != null)	fixrecord.setFixLog( appender.getString() ) ;
		ec.saveChanges(); // commit to db

	}

	/**
	 * Mark the db record for the fix as done
	 */
	public void markFixFinshed() {
		if(done) {
			log.error("Already considered done, con't mark finished! " + _fixname );	
			return;
		}
		log.info("Application is marking " + _fixname + " as done");
		if(fixrecord == null){
			log.error("Trying to markFixFinished, but there is no fix record to mark! ");
		} else {
			fixrecord.setFinished(true);
			fixrecord.setFixLog(appender.getString());
			ec.saveChanges(); // commit to db
			ec.dispose(); // clear
			appender.setWriter(new StringWriter() ); // release buff
		}

	}

	/**
	 * is the job ready to be executed? If there are problems, either setting up or
	 * if the job is already finished, it will not be ready to do.
	 * @return -  true if ready to go, else false, don't bother.
	 */
	public boolean isReadyToDo() {
		return !done; // not done is ready to do
	}

	/**
	 * Actually do it, fix it.
	 */
	public abstract void doit()  ;



	/**
	 * @param layout - fancy layout to use for outputting text.
	 */
	public void setLayout(Layout layout){
		appender.setLayout(layout);
	}

	/**
	 * @return - the current layout in use.
	 */
	public Layout getLayout(){
		return appender.getLayout();

	}

}//eoc
