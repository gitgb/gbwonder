package er.batchfixes;

import java.io.StringWriter;

import org.apache.log4j.Layout;

import er.extensions.logging.ERXPatternLayout;

/**
 * @author gbrown
 *  adds logging characters to a string buffer. The string can
 *  be fetched via getString(). Use your own layout, or the default, which is the ERXPatternLayout.
 *
 */
public class ERBatchFixesAppender extends org.apache.log4j.WriterAppender {
	
	StringWriter sw = new StringWriter();
	ERXPatternLayout layout = new ERXPatternLayout();

	/**
	 *  use default layout
	 */
	public ERBatchFixesAppender() {
		super();
		super.setWriter(sw);
		super.setLayout(layout);
	}


	/**
	 * @param layout - for logging
	 */
	public ERBatchFixesAppender(Layout layout) {
		super();
		super.setWriter(sw);
		super.setLayout(layout);
		
	}
	
	/**
	 * @return - the appender output as one big string.
	 */
	protected String getString(){
		return sw.toString();
	}
	
}
