package projectsservice;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;
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
		return projectDao.fetchAllProjects() ;
		
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

		
	}

