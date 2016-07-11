package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ca.mcgill.cs.konaila.Main;

public class DatabaseKonailaNodeTypes {
	
	enum AlwaysLeaf {
		PackageDeclaration, ImportDeclaration,
		BasicBlockComment, JavaDocComment, LineComment,
		SwitchLabel;
	}

	enum Either {
		SimpleStatement, LocalVariableDeclarationStatement, FieldDeclaration,
		AnonymousClassCreation, 
		CallUnit, ConstructorUnit;
	}

	enum AlwaysNonLeaf {
		TypeSignature, InterfaceSignature,
		MethodSignature, ConstructorSignature,
		SwitchWrapper, TryWrapper, CatchWrapper, FinallyWrapper, SynchronizedWrapper,
		IfWrapper, ElseWrapper, ForWrapper, WhileWrapper, DoWhileWrapper;
	}

	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		Connection c = Database.getInstance().getConnection();
		
		createNodeTypeTable(c);		

		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
	
	public static void createNodeTypeTable(Connection conn) throws SQLException, IOException {
		int numberOfTypes = 
				AlwaysLeaf.values().length + Either.values().length + AlwaysNonLeaf.values().length;
			

		conn.setAutoCommit(false);	
		
		 PreparedStatement d = conn.prepareStatement(
					"DROP TABLE IF EXISTS konailaNodeTypes ");
		 d.executeUpdate();
		 
		 PreparedStatement s = conn.prepareStatement(
					"CREATE TABLE konailaNodeTypes (konailaNodeType REFERENCES selectionUnits(konailaNodeType), category)");
		 s.executeUpdate();	 
		 

		 PreparedStatement i = conn.prepareStatement(
				"INSERT INTO konailaNodeTypes VALUES(?,?)" );
		 for( AlwaysLeaf l : AlwaysLeaf.values()) {
			 i.setString(1, l.name());
			 i.setString(2, "AlwaysLeaf");
			 i.executeUpdate();		
		 }	
		 for( Either l : Either.values()) {
			 i.setString(1, l.name());
			 i.setString(2, "Either");
			 i.executeUpdate();		
		 }
		 for( AlwaysNonLeaf l : AlwaysNonLeaf.values()) {
			 i.setString(1, l.name());
			 i.setString(2, "AlwaysNonLeaf");
			 i.executeUpdate();		
		 }		
		 
		 conn.commit();
	}

}