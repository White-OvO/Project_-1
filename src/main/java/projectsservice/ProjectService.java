package projectsservice;

import java.util.List;

import projects.dao.ProjectDao;
import projects.entity.Project;

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
	public Project fetchProjectById(Integer projectId) {
			return projectDao.fetchProjectById() + "does not exist" ) ) ;
//			create service class to call the DAO class. This method will simply return the 
//			results of the method call to the DAO class.			
//////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////			
//Have Eclipse create the method fetchAllProjects() in the ProjectService class, or create it yourself
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	


	}

		
	}

