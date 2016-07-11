package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.antlr.v4.runtime.ParserRuleContext;

import ca.mcgill.cs.konaila.chopper.MultiLineStatementSelectionUnit;
import ca.mcgill.cs.konaila.chopper.SelectionUnit;
import ca.mcgill.cs.konaila.chopper.SelectionUnit.Element;
import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;

public class DatabaseAddUnits {
	
	public static void addUnits(Connection conn, Collection<SelectionUnit> unitInfos) throws SQLException, IOException {
		
		conn.setAutoCommit(false);
		
		PreparedStatement unitStat = conn
				.prepareStatement("INSERT INTO selectionUnits VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");

		PreparedStatement elementStat = conn
				.prepareStatement("INSERT INTO elements VALUES(?,?,?,?,?,?,?,?,?,?);");	

		PreparedStatement holeStat = conn
				.prepareStatement("INSERT INTO holes VALUES(?,?,?,?,?,?,?,?,?,?);");
		
		int uidCounter = 1;
		for ( SelectionUnit u : unitInfos ) {
			
//			System.out.println("Adding subunits to the DB - " + u.getInternalId() + "," + uidCounter);
			
			if( u instanceof MultiLineStatementSelectionUnit ) {
				ParserRuleContext node = ((MultiLineStatementSelectionUnit)u).getNode();
				System.out.println( "multi-line unit: " +node.getStart().getLine() + "," + node.getStop().getLine());				
			}
					
			addUnit(uidCounter, u.getInternalId(), u.getCodeDisplayWhole(), 
					u.getAntlrNodeType(), u.getKonailaNodeType(), 
					u.getStartLine(), u.getEndLine(), 
					u.getStartCharPositionInLine(), u.getEndCharPositionInLine(),
					u.getStartChar(), u.getEndChar(),
					unitStat);
			
			int elementOrderInUnit = 1;
			for( Element element : u.getElementsWithoutHoles()) {				
				addElement(uidCounter, u.getInternalId(), element.getCode(),
							element.getStartLine(), element.getEndLine(), 
							element.getStartCharPositionInLine(), element.getEndCharPositionInLine(),
							element.getStartChar(), element.getEndChar(),
							elementOrderInUnit, elementStat);

				elementOrderInUnit += 1;
			}
			
			if( u instanceof MultiLineStatementSelectionUnit ) {
				for( HoleElement h : ((MultiLineStatementSelectionUnit)u).getHoles()) {				
					addHole(uidCounter, u.getInternalId(), h.getOriginalString(),
								h.getStartLine(), h.getEndLine(), 
								h.getStartCharPositionInLine(), h.getEndCharPositionInLine(),
								h.getStartChar(), h.getEndChar(), holeStat);
				}
			}			
			
			uidCounter += 1;								
		}		
		unitStat.close();
		elementStat.close();
		
		conn.commit();
	}
	
	private static void addUnit(int uid, int cid, String codeDisplayWhole, 
			String antlrNodeType, String konailaNodeType,
			int lineStart, int lineEnd, int lineCharStart,	int lineCharEnd, int charStart, int charEnd,
			PreparedStatement unitStat)
			throws SQLException, IOException {
		unitStat.setInt(1, cid);
		unitStat.setInt(2, uid);
		unitStat.setString(3, codeDisplayWhole);
		unitStat.setString(4, antlrNodeType);
		unitStat.setString(5, konailaNodeType);
		unitStat.setInt(6, -1); // DEFAULT value
		unitStat.setInt(7, lineStart);
		unitStat.setInt(8, lineEnd);
		unitStat.setInt(9, lineCharStart);
		unitStat.setInt(10, lineCharEnd);
		unitStat.setInt(11, charStart);
		unitStat.setInt(12, charEnd);		
		unitStat.executeUpdate();	
	}

	
	private static void addElement(int uid, int cid, String code, 
			int lineStart, int lineEnd, int lineCharStart,	int lineCharEnd, int charStart, int charEnd,
			int elementOrInUnit, PreparedStatement stat)
			throws SQLException, IOException {

		stat.setInt(1, cid);
		stat.setInt(2, uid);
		stat.setString(3, code);
		stat.setInt(4, lineStart);
		stat.setInt(5, lineEnd);
		stat.setInt(6, lineCharStart);
		stat.setInt(7, lineCharEnd);
		stat.setInt(8, charStart);
		stat.setInt(9, charEnd);
		stat.setInt(10, elementOrInUnit);
		stat.executeUpdate();
	}
	
	private static void addHole(int enclosingUid, int cid, String code, 
			int lineStart, int lineEnd, int lineCharStart,	int lineCharEnd, int charStart, int charEnd,
			PreparedStatement stat)
			throws SQLException, IOException {

		stat.setInt(1, cid);
		stat.setInt(2, -1);
		stat.setInt(3, enclosingUid);
		stat.setString(4, code);
		stat.setInt(5, lineStart);
		stat.setInt(6, lineEnd);
		stat.setInt(7, lineCharStart);
		stat.setInt(8, lineCharEnd);
		stat.setInt(9, charStart);
		stat.setInt(10, charEnd);
		stat.executeUpdate();
	}
	
	public static void addEllipses(Connection conn, Collection<SelectionUnit> ellipses) throws SQLException, IOException {

		conn.setAutoCommit(false);
		
		PreparedStatement stat = conn
				.prepareStatement("INSERT INTO ellipses VALUES(?,?,?,?,?,?,?,?)");
		for ( SelectionUnit u : ellipses ) {
			
			addEllipsis(u.getInternalId(), u.getCodeDisplayWhole(), 
					u.getStartLine(), u.getEndLine(), 
					u.getStartCharPositionInLine(), u.getEndCharPositionInLine(),
					u.getStartChar(), u.getEndChar(), stat);							
			
		}
		stat.close();

		conn.commit();
	}
	
	
	private static void addEllipsis(int cid, String code, 
			int lineStart, int lineEnd, int lineCharStart,	int lineCharEnd, int charStart, int charEnd, PreparedStatement stat)
			throws SQLException, IOException {
		stat.setInt(1, cid);
		stat.setString(2, code);
		stat.setInt(3, lineStart);
		stat.setInt(4, lineEnd);
		stat.setInt(5, lineCharStart);
		stat.setInt(6, lineCharEnd);
		stat.setInt(7, charStart);
		stat.setInt(8, charEnd);

		stat.executeUpdate();
	
	}
	
}
