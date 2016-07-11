package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import ca.mcgill.cs.konaila.chopper.SelectionUnit.Element;
import ca.mcgill.cs.konaila.selection.features.Property;

public class DatabaseAddProperties {
	
	public static void addProperties(Connection conn, Collection<Property> properties) throws SQLException, IOException {
		
		PreparedStatement unitStat = conn
				.prepareStatement("INSERT INTO astProperties VALUES(?,?,?,?,?,?,?,?,?,?);");	
		
		for ( Property p : properties ) {			
//			System.out.println("Adding property to the DB - " + p.getCid() + "," + p.getAntlrProperty());
			int cid = p.getCid();
			Element e = p.getElement();
			String antlrProperty = p.getAntlrProperty();
			String konailaProperty = p.getKonailaProperty();

			unitStat.setInt(1, cid);
			unitStat.setInt(2, -1); // DEFAULT value for enclosingUnitUid
			unitStat.setString(3, antlrProperty);
			unitStat.setString(4, konailaProperty);
			unitStat.setInt(5, e.getStartLine());
			unitStat.setInt(6, e.getEndLine());
			unitStat.setInt(7, e.getStartCharPositionInLine());
			unitStat.setInt(8, e.getEndCharPositionInLine());
			unitStat.setInt(9, e.getStartChar());
			unitStat.setInt(10, e.getEndChar());    
			unitStat.executeUpdate();								
		}		
		unitStat.close();
	}

}
