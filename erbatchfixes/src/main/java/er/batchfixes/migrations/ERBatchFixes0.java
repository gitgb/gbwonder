package er.batchfixes.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

/**
 * This creates a database migration from version none to version 0, using that
 * ERXMigrationDatabase stuff.
 * <p/>
 * Version 0 adds a _ERBatchFixes table. The _ERBatchFixes table record has info
 * on BatchFixes fixes applied to the db. The main app can have fixes or batch
 * jobs that check this table and optionally run batch jobs.
 * 
 * @author gbrown
 */
public class ERBatchFixes0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}

	@Override
	public void downgrade(EOEditingContext editingContext,
			ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	/*
	 * This upgrade, from none to v0, adds 1 table: BatchFixes
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * er.extensions.migration.ERXMigrationDatabase.Migration#upgrade(com.webobjects
	 * .eocontrol.EOEditingContext,
	 * er.extensions.migration.ERXMigrationDatabase)
	 */

	@Override
	public void upgrade(EOEditingContext editingContext,
			ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable eRBatchFixesTable = database
				.newTableNamed("_ERBatchFixes");
		eRBatchFixesTable.newIntBooleanColumn("finished", true);
		eRBatchFixesTable.newStringColumn("fixname", 255, false);
		eRBatchFixesTable.newIntegerColumn("id", false);
		eRBatchFixesTable.newStringColumn("fixLog", 10000000, true);
		eRBatchFixesTable.newTimestampColumn("rCreateTime", true);
		eRBatchFixesTable.newTimestampColumn("rLastModified", true);
		eRBatchFixesTable.newIntBooleanColumn("started", true);
		eRBatchFixesTable.create();
		eRBatchFixesTable.setPrimaryKey("id");
		eRBatchFixesTable.addUniqueIndex("fixname");
		System.out.println("Finished with  migration _ERBatchFixes ");
	}
}