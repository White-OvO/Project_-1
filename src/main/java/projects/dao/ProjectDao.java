
package projects.dao;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import projects.exception.DbException;
import projects.entity.Project; 
//				In this section, you will be adding constants into the ProjectDao class. 	
//              a constant is specified using static final

public class ProjectDao extends DaoBase {
  private static final String CATEGORY_TABLE = "category";
  private static final String MATERIAL_TABLE = "material";
  private static final String PROJECT_TABLE = "project";
  private static final String PROJECT_CATEGORY_TABLE = "project_category";
  private static final String STEP_TABLE = "step";
  

//				 There are several steps that must be taken to save the project details		  
//First, you must create the SQL statement. Then you will obtain a Connection and start a transaction. Next you will obtain a PreparedStatement and set the parameter values from the Project object. Finally, you will save the data and commit the transaction. Follow the steps below to save the project details.
		  
		  
		  public Project insertProject(Project project) {
			    // @formatter: off
			    String sql = ""
			        + "INSERT INTO " + PROJECT_TABLE + " "
			        + "(project_name, estimated_hours, actual_hours, difficulty, notes) "
			        + "VALUES "
			        + "(?, ?, ?, ?, ?)";  
			    
			    // @formatter: on
			    
			    
//Obtain a connection from DbConnection.getConnection(). Assign it a variable of type Connection named conn in a try-with-resource statement			    
			    // @formatter :on
			    
			    try (Connection conn = DbConnection.getConnection()) {
			      startTransaction(conn); //start transaction
			      
			      try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			        setParameter(stmt, 1, project.getProjectName(), String.class);
			        setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
			        setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
			        setParameter(stmt, 4, project.getDifficulty(), Integer.class);
			        setParameter(stmt, 5, project.getNotes(), String.class);
			        
			        stmt.executeUpdate();
			        Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
			        
			        commitTransaction(conn);
			        project.setProjectId(projectId);
			        return project;
			      }
			      catch(Exception e) {
			        rollbackTransaction(conn); //rollback transaction
			        throw new DbException(e);
			      }
			    }
			    catch(SQLException e) {
			      throw new DbException(e);
			    }
			    
			  }
	}
