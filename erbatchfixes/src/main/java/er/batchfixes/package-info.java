/**
This ERBatchFixes framework allows you to have your application do batch jobs at startup time. 
Often after database migrations there may be needed modifications to the data in the database. This package
allows you to more easily create these changes through a batch job, run after a migration, but before your application
starts up.  These jobs
are remembered in a _ERBatchFixes db table. Also, a log4j logger is setup so you can log during the batch job, and
this log is stored with the batch job info, as is start time, end time. To use this,
create a subclass of ERBatchFixesParent, which you will then use to do whatever batch jobs/processing you want. 
The ERBatchFixesParent handles storing fix event information for you; things like: has the fix been applied, when? the error log, etc.
ERMigrations is used to create the db table _ERBatchFixes.
<p/>
To use this do 3 things:
<p/>
1. Make your fixer-upper class extend ERBatchFixesParent.<p/>
2. Modify your Application's didFinishLaunching() method to create and call your fixer class, <p/>
3. and modify your Application's Properties so memory of the changes will persist.<p/>
The intent is to run this batch job right after your app is finished initializing. Use something like: 

<pre>{@code
 public void   didFinishLaunching(){ // method in Application

	// All setup? lets fix/patchup our own lists of fixes
	String fixname = "MyFixes";
	try {
		MyFixes fn = new MyFixes(); // MyFixes is subclass of ERBatchFixesParent	
		if( fn.isReadyToDo() ){ // wise to check if it is ok
			fn.doit(); // do the fix(es)
    	}	
	} catch (ERBatchFixFinished e) {
		log.info("Fix is alredy finished: " + fixname, e);
	} catch (IllegalStateException e) {
		log.info("ERBatchFixes turned off by system property");
	} catch (Exception e) {
      log.error("Error during fix " + fixname, e);
  }
	
}
</pre>
The fixes will be remembered (along with an error log) in a chosen database. Set up
your Properties to point to the DB where you want the fix events persisted.

<pre>
# these migrations happen after main program initialization but before batch fixes..
er.migration.migrateAtStartup=true
er.migration.createTablesIfNecessary=true
er.migration.modelNames=ERBatchFixes,myOtherModels,etc
ERBatchFixes.MigrationClassPrefix=er.batchfixes.migrations.ERBatchFixes

# property must be true to use this
er.batchfixes.dofixes = true
ERBatchFixes.URL = jdbc:FrontBase://localhost/MyDBProduction
ERBatchFixes.DBUser = _system
ERBatchFixes.DBPassword = 
ERBatchFixes.DBDriver =
ERBatchFixes.DBPlugin =
ERBatchFixes.DBJDBCInfo =


</pre>
This framework will create a _ERBatchFixes table, via migrations to store fix event information.
With multiple applications., it is best to run one instance of your app to do the fixes, then run
with all instances. As ERXJDBCMigrationLock says: "if true, the tables and model rows will be created automatically.
* *ONLY SET THIS IF YOU ARE RUNNING IN DEVELOPMENT MODE OR WITH A SINGLE INSTANCE*. 
*If you are running multiple instances, the instances will not be able to acquire locks properly
* and you may end up with multiple instances attempting to create lock tables and/or failing to startup properly.
 */
package er.batchfixes;
