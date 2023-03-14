
package projects.dao;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jdk.jfr.Category;
import projects.exception.DbException;
import projects.entity.Material;
import projects.entity.Project; 
//				In this section, you will be adding constants into the ProjectDao class. 	
//              a constant is specified using static final
import projects.entity.Step;




@SuppressWarnings("restriction")
public class ProjectDao extends DaoBase {
  private static final String CATEGORY_TABLE = "category";
  private static final String MATERIAL_TABLE = "material";
  private static final String PROJECT_TABLE = "project";
  private static final String PROJECT_CATEGORY_TABLE = "project_category";
  private static final String STEP_TABLE = "step";

  

//				 There are several steps that must be taken to save the project details		  
//First, you must create the SQL statement. Then you will obtain a Connection and start a transaction. Next you will obtain a PreparedStatement and set the parameter values from the Project object. Finally, you will save the data and commit the transaction. Follow the steps below to save the project details.
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Create a method fetchAllProjects in projectDao. This method will simply return the results of the method call to the DAO 
//class. The service class in out small application does not do very much. But it allows us to properly separate concerns of input/output, business logic, and database reads and writes. If you always structure your code like this it will be much easier to understand and make changes if needed
  

	
//week 10 : In the method fetchAllProjects() write the sequal statment to return all projects not including material,
//steps,or categories. Order the results by project name.	
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Write the SQL statement to return all projects not including materials, steps, or categories. Order the results by project name.
//Add a try-with-resource statement to obtain the Connection object. 		
		  public List<Project> fetchAllProjects() {
				String sql = "SELECT * FROM " + PROJECT_TABLE + " ORDER BY project_name";

try (Connection conn = DbConnection.getConnection()) {
	startTransaction(conn); // starts to get connection with this line
	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		try (ResultSet rs = stmt.executeQuery()) { //at this point there should be no errrors
			List<Project> projects = new LinkedList<>(); //go through our result set

			while (rs.next()) {
//if nothing comes out then a empty list is returned
				projects.add(extract(rs, Project.class));
//this type specifies what class we wanna get project details
}
			
//declaration for a exception from SQL which calls roll back transcation
				return projects;
		}

			} 			
				catch (Exception e) 	{
				rollbackTransaction(conn);
					throw new DbException(e);
						}
							}
					catch (SQLException e) {
						throw new DbException(e);
				
						}		  
		  			}

		  

			public Optional<Project> fetchProjectById(Integer projectId) {
				String sql = "SELECT * FROM " + PROJECT_TABLE + " WHERE project_id = ?";
				
				try(Connection conn = DbConnection.getConnection()){
					startTransaction(conn);
					
					try {
						Project project = null;
						
						try(PreparedStatement stmt = conn.prepareStatement(sql)){
							setParameter(stmt, 1, projectId, Integer.class);
							
							try(ResultSet rs = stmt.executeQuery()){
								if(rs.next()) {
									project = extract(rs, Project.class);
								}
							}
						}
						if (Objects.nonNull(project)) {
							project.getMaterials().addAll(fetchMaterialsForProject(conn, projectId));
							project.getSteps().addAll(fetchStepsForProject(conn, projectId));
							project.getCategories().addAll(fetchCategoriesForProject(conn, projectId));
						}
						
						commitTransaction(conn);
						return Optional.ofNullable(project);
					}
					catch(Exception e) {
						rollbackTransaction(conn);
						throw new DbException(e);
					}
				}
				catch(SQLException e) {
					throw new DbException(e);
				}
			}


private List<Category> fetchCategoriesForProject(Connection conn, Integer projectId) {
				String sql = ""
						+ "SELECT c.* FROM " + CATEGORY_TABLE + " c "
						+ "JOIN " + PROJECT_CATEGORY_TABLE + " pc USING (category_id) "
						+ "WHERE project_id = ?";
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)){
					setParameter(stmt, 1, projectId, Integer.class);
					
					try(ResultSet rs = stmt.executeQuery()){
						List<Category> categories = new LinkedList<>();
						
						while(rs.next()) {
							categories.add(extract(rs, Category.class));
						}
						return categories;
					}
				}
				catch(SQLException e) {
					throw new DbException(e);
				}
			}

private List<Step> fetchStepsForProject(Connection conn, Integer projectId) throws SQLException {
	String sql =  "SELECT * FROM " + STEP_TABLE + " WHERE project_id = ?";

//this is the connection to the program

	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		setParameter(stmt, 1, projectId, Integer.class);

		try (ResultSet rs = stmt.executeQuery()) {
//	returns a list of categorys					
			List<Step> steps = new LinkedList<>(); //list of steps we wanna return
//gets rid of compliers
			while (rs.next()) {
				steps.add(extract(rs, Step.class));
			}

			return steps;
		}
	}

}  
private List<Material> fetchMaterialsForProject(Connection conn, Integer projectId) throws SQLException {
	String sql = "SELECT * FROM " + MATERIAL_TABLE + " WHERE project_id = ?";

	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		setParameter(stmt, 1, projectId, Integer.class);

		try (ResultSet rs = stmt.executeQuery()) {
			List<Material> materials = new LinkedList<>();

			while (rs.next()) {
				materials.add(extract(rs, Material.class));
			}

			return materials;
		}
	}
}

}



//In here you will write the methods that will return materials, steps, and categories as Lists
				




