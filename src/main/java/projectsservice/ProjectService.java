package projectsservice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;
/**
 * This class implements the service the 3-tier application, CRUD operations as a pass-through from the input
 * layer to the data layer.
 * 
 * @author Promineo
 *
 */

	public class ProjectService {
	
	
	private ProjectDao projectDao = new ProjectDao();
	  
	public Project addProject(Project project) {
	    return projectDao.insertProject(project);
	  }
//Have Eclipse create the method fetchAllProjects() in the ProjectService class, or create it yourself.
//////////////////////////////////////////////////////////////////////////////////////////////////////
public List<Project> fetchAllProjects() {
	//@formatter:off	
	return projectDao.fetchAllProjects() 
		.stream()
		.sorted((r1,r2) -> r1.getProjectId() - r2.getProjectId())
		.collect(Collectors.toList());
		//@formatter on:
	}
//				This method fetches project details from the project Dao .  
//				calling service returning projects DAO / can also throw a new such element exception	
	 public Project fetchProjectById(Integer projectId) {
		 return projectDao.fetchProjectById(projectId).orElseThrow(
		       () -> new NoSuchElementException("Project with project ID=" + projectId + " does not exist."));

//			create service class to call the DAO class. This method will simply return the 
//			results of the method call to the DAO class.			
//////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////			
//Have Eclipse create the method fetchAllProjects() in the ProjectService class, or create it yourself
//////////////////////////////////////////////////////////////////////////////////////////////////////	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 		Call projectDao.modifyProjectDetails(). Pass the Project object as a parameter. The DAO method returns 
//		a boolean that indicates whether the UPDATE operation was successful. Check the return value. If it is false, throw a DbException with a message that says the project does not exist.
	 public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) { 
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist."); 
		
	}
}
	 public void deleteProject(Integer projectId) {
			if (!projectDao.deleteProject(projectId)) {
				throw new DbException("Project with ID=" + projectId + " does not exisit."); 
				}
			}
	 
	}


		
	

