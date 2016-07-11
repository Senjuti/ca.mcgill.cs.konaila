package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import ca.mcgill.cs.konaila.chopper.Chopper;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.selection.features.PropertyExtractor;

public class MainMultiThreadedStudyPopulation {

	public static final String DB = Main.DB;

	private static final String source = "stackoverflow";
	private static final boolean useDatabase = true;

	private static final int numberOfThreads = 1;

	static boolean chop = false;
	static boolean consolidate = false;
	static boolean summarize = false;

	public static void main(String[] args) throws Exception {

		Database.setDatabaseString(MainMultiThreadedStudyPopulation.DB);
		Database.getInstance().getConnection().setAutoCommit(false);

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-chop"))
				chop = true;
			else if (args[i].equals("-consolidate"))
				consolidate = true;
			else if (args[i].equals("-summarize"))
				summarize = true;
		}

		if (chop)
			chop();

		if (consolidate)
			consolidate();

		if (summarize)
			summarize();

		Database.getInstance().getConnection().commit();
		Database.getInstance().closeConnection();
	}

	public static void chop() throws Exception {

		final List<Integer> sortedCids = Collections.synchronizedList(Database
				.getInstance().getStudyCids());

		for (int i = 0; i < numberOfThreads; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!sortedCids.isEmpty()) {
						int cid = -1;
						try {
							cid = sortedCids.remove(0);
						} catch (IndexOutOfBoundsException e) {
							break;
						}
						try {
							doChoppingCodeFragmentsFromDatabase(cid);
						} catch (Exception e) {
							System.err.println("CHOP top-level exception: " + e);
							e.printStackTrace(System.err);
						}
					}
				}
			});
			t.start();
		}

		while (!sortedCids.isEmpty()) {
			Thread.sleep(1000);
		}

		DatabaseChopperStats.populateStats(Database.getInstance()
				.getConnection(), source);

		Database.getInstance().getConnection().commit();
	}

	public static void consolidate() throws Exception {

		Database.getInstance().consolidateFeaturesExceptDataFlow();

		final List<Integer> sortedCids = Collections.synchronizedList(Database
				.getInstance().getStudyCids());

		for (int i = 0; i < numberOfThreads; i++) {
			final int server = i;
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!sortedCids.isEmpty()) {
						int cid = -1;
						try {
							cid = sortedCids.remove(0);
						} catch (IndexOutOfBoundsException e) {
							break;
						}
						try {
							Database.getInstance().consolidateFeaturesDataflow(
									cid, server);
						} catch (Exception e) {
							System.err.println("CONSOLIDATE top-level exception: " + e);
							e.printStackTrace(System.err);
						}
					}
				}
			});
			t.start();
		}

		while (!sortedCids.isEmpty()) {
			Thread.sleep(1000);
		}

	}

	public static void summarize() throws Exception {

		Database.getInstance().populateCodeFragmentCategorization();
		Database.getInstance().populateConfigurationBasedPredictions();

		final List<Integer> sortedCids = Collections.synchronizedList(Database
				.getInstance().getStudyCids());

		for (int i = 0; i < numberOfThreads; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!sortedCids.isEmpty()) {
						int cid = -1;
						try {
							cid = sortedCids.remove(0);
						} catch (IndexOutOfBoundsException e) {
							break;
						}
						try {
							Database.getInstance().summarize(cid);
						} catch (Exception e) {
							System.err.println("SUMMARIZE top-level exception: " + e);
							e.printStackTrace(System.err);
						}
					}
				}
			});
			t.start();
		}

		while (!sortedCids.isEmpty()) {
			Thread.sleep(1000);
		}
	}

	public static void doChoppingCodeFragmentsFromDatabase(int cid)
			throws SQLException, IOException {

		String code = Database.getInstance().getCodeFragment(cid);
		String query = Database.getInstance().getQuery(cid);

		Chopper.chopFile(cid, source, code, useDatabase);
		PropertyExtractor.addFeatures(cid, source, code, query, useDatabase, 0);

		Database.getInstance().getConnection().commit();
	}
}
