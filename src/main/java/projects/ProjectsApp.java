package projects;

import java.nio.file.Files;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


import projects.dao.DbConnection;
import projects.entity.Project;
import projects.exception.DbException;
import projectsservice.ProjectService;

public class ProjectsApp {
	//public static void main(String[] args) {
		// i will need this line for week7 video
		// as welll as the import
		
		//DbConnection.getConnection();
		
	private ProjectService projectService = new ProjectService();
	private Project curProject;

		
///	 To prevent the Eclipse formatter from reformatting the list, surround the variable declaration with // @formatter:off and // @formatter:on so
		//@formatter:off
		
//		 Add a private instance variable named "operations". The type is List<String>. Initialize it using List
		private List<String> operations = List.of(			
				"1) Add a project ",
				"2) List projects ",
				"3) Select a project",
				"4) Update project details",
			    "5) Delete a Project"
				);
		// @formatter:on
		
//                  
//		In this step you will use a Scanner to obtain input from a user from the Java console	
		private Scanner scanner = new Scanner(System.in) ;

		
//		In this step you will call the method that processes the menu. In the main() method, create a new ProjectsApp object and call the method: processUserSelections() method

		
	public static void main(String[] args) {
		
new ProjectsApp().processUserSelection();  // displaysMenu .. The method takes zero parameters and returns nothing.


//		Now you can create the processUserSelections() method as an instance method. 

	}
	private void processUserSelection() { 
		boolean done = false; 
		
		
//	Add a while loop below the local variable. Loop until the variable done is true.
		
		while(!done) 
		{
			try {
int selection = getUserSelection();
				
				switch(selection) {
					case -1:
						done = exitMenu();
						break; 
					case 1: 
			            createProject();
						break; 
					case 2:
			            listProjects();
						break;
					case 3:
			            selectProject();
			            break;

					case 4:
						updateProjectDetails();
						break;

					case 5:
						deleteProject();
						break;	

//			Back in the method processUserSelections():
//			Add a switch statement below the method call to getUserSelection(). Create a switch statement to switch on the value in the local variable selection.

					default:
						System.out.println("\n"+ selection + " is not a valid selection. Try again.");
						break;
			}
		}

			
//			Inside the while loop, add a try/catch block. The catch block should catch Exception. 
	
		catch(Exception e) {
				System.out.println("\nError: " + e + " try again");
			}
		}
	}
	
	private void deleteProject() {
		listProjects();

		
		Integer projectId = getIntInput("Enter the ID of the project to delete");
			projectService.deleteProject(projectId);
			System.out.println("Project " + projectId + " was deleted successfully.");

		if (Objects.nonNull(curProject) && curProject.getProjectId().equals(projectId)) {
			curProject = null;

			}
		}
	

	 
	
	
	
//				In method updateProjectDetails():
//				Check to see if curProject is null. If so, print a message "\nPlease select a project." and return from the method.

private void updateProjectDetails() {
	  if(Objects.isNull(curProject)) {
	      System.out.println("\nPlease select a project.");
	      return;
	    	
	  }
	String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]");
		  
		  BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours [" + curProject.getEstimatedHours() + "]"); 
		  
		  BigDecimal actualHours = getDecimalInput("Enter actual hours [" + curProject.getActualHours() + "]"); 
		  
		  Integer difficulty = getIntInput("Enter the project difficulty (1-5) [" + curProject.getDifficulty() + "]"); 
		  
		  String notes = getStringInput("Enter the project notes [" + curProject.getNotes() + "]"); 
		  

//			Create a new Project object. If the user input for a value is not null, add the value to the Project object.
//			If the value is null, add the value from curProject. Repeat for all Project variables
	Project project = new Project(); 	  
	 
	  project.setProjectId(curProject.getProjectId()); 
	 
	  project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);

	  		project.setEstimatedHours(Objects.isNull(estimatedHours) ? curProject.getEstimatedHours() : estimatedHours); 
	   
			  project.setActualHours(Objects.isNull(actualHours) ? curProject.getActualHours() : actualHours); 
			  project.setDifficulty(Objects.isNull(difficulty) ? curProject.getDifficulty() : difficulty); 
			  project.setNotes(Objects.isNull(notes) ? curProject.getNotes() : notes); 
			 
	  projectService.modifyProjectDetails(project); 
	  curProject = projectService.fetchProjectById(curProject.getProjectId()); 	  
		  
		  
		  
	 
	 
	

	
//			Create a new Project object. If the user input for a value is not null, add the value to the Project object. 
//			If the value is null, add the value from curProject. Repeat for all Project variables	
	 projectService.modifyProjectDetails(project); 
		  curProject = projectService.fetchProjectById(curProject.getProjectId()); 
}





private Object setEstimatedHours(Object object) {
	// TODO Auto-generated method stub
	return null;
}

/**
 * This method allows the user to select a "current" project. The current
 * project is one on which you can add materials, steps, and categories.
 */






/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//METHODS NOTES:																																													 ////
//a bunch of code in one line																																									     ////
// ////
//public static void main (String [] args) { 																																				 ////
//int a = 5;                 int b = 10;																																							 ////
//  ////
//System.out.println(a *b);																																										 ////
//
//RESULT : 50
//changing variables will get a different result
//
//int c = 2          int d = 3;
//
//System.0ut.println(c * d);
//Result : 6
//
//Lets do that again with different variableas
//
//int e = 6;           int f = 8;
//System.out.println(e * f);
//
//result : 48;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Now lets put this in a method where we dont have to type it over and over again
//
//public static void multiply() { 			
//
//To pass values in a method you input then in the () 
//public static void multiply ( int a , int b) { 
//System.out.println( a * b) 
//Now we can just apply the method
//multiply(5,10);
//RESULT: 50
//multiply(10, 5)
//RESULT : 50
//
//MORE EXAMPLES (FREEWRITING<WE<METHOD_EXAMPLES
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
//	In this step you will create the method, selectProject(). This method will list the project 
//	IDs and names so that the user can select a project ID. Once the ID is entered, the service is called to return the project details	
/**
	 * This method allows the user to select a "current" project. The current project is one on which
	 * you can add materials, steps, and categories.
*/
	  private void selectProject() {
		  if (Objects.isNull(curProject)) {
				System.out.println("\nYou are not working with a project.");
			} else {
				System.out.println("\nYou are working with project: " + curProject);
			}

	    listProjects();
	    Integer projectId = getIntInput("Enter a project ID to select a project");
	    curProject = null;
	    /* This will throw an exception if an invalid project ID is entered. */
	    curProject = projectService.fetchProjectById(projectId);
	  }

	  /**
	   * This method calls the project service to retrieve a list of projects from the projects table.
	   * It then uses a Lambda expression to print the project IDs and names on the console. 
	   */	
 
//create a variable to hold List of Projects named projects.
	 //List<project> projects
	 
//Assign the variable the results of a method call to projectService.fetcuALLProjects().
	 // List<project> projects = projectService.fetchAllProjects();
	
	
	
	
	
	
// methoth list projects. It should take no parameters and should return nothing.
//	private void listProjects() {
	
	
	 private void listProjects() {
		 List<Project> projects = projectService.fetchAllProjects();
		 
//		Print "\nProjects:" (without quotes) to the console
		//		Sysout(\nProjects:");		 
		 System.out.println("\nProjects: ") ;
		 
		 
// 		for each projects 	
		 
		 projects.forEach(project -> System.out.println(" " + project.getProjectId() + " : " 
				 + project.getProjectName())) ;
	 

}

	 
	 
	private boolean exitMenu() {
System.out.println("\nExiting the menu."); 
		return true;
	}
	//                    a user input and changes to bigDecimal


private void createProject() {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
	BigDecimal actualHours = getDecimalInput("Enter the actual hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	String notes = getStringInput("Enter the project notes");

	Project project = new Project();
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);

	Project dbProject = projectService.addProject(project); 

	System.out.println("You have successfully created project: " + dbProject);
	return;
	}


	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return new BigDecimal(input).setScale(2);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	}




	
	
// Create the method getUserSelection(). It takes no parameters and returns an int	
			private int getUserSelection() {
				printOperations();
	//^ NOTE: It takes no parameters and returns an int.
	//^ This method willprint the operations and then accept user input as an Integer				
	
					Integer input = getIntInput("Enter menu selection");
				
					return Objects.isNull(input) ? -1 : input;
					
			}

	// ^ Make a method call to the method printOperations(). This method takes no parameters and returns nothing.
//Add a method call to getIntInput(). Assign the results of the method call to a variable 
//named input of type Integer. The method getIntInput(), which you haven't written yet. It will return the user's 
//menu selection. The value may be null. Pass the String literal "Enter a menu selection" as a parameter to the method.
//Add a return statement that checks to see if the value in local variable input is null. If 
//so, return -1. 
				
				
//Create the method printOperations(). 
				private void printOperations() {
			        System.out.println("\nThese are the available selections. Press the Enter key to quit:");
			        operations.forEach(line -> System.out.println("     " + line));
				}
					
// ^ Print all the available menu selections, one on each line
			        
//	Create the method getIntInput . It takes a single parameter of type String named prompt. 
// This method accepts input from the user and converts it to an Integer, which may be null.
//It is called by getUserSelection() and will be called by other data collection methods that 
//require an Integer. Inside the method body:

	

					private Integer getIntInput(String prompt) {
						String input = getStringInput(prompt); 
						if(Objects.isNull(input)){
							return null; 
						}
						try {
							return Integer.valueOf(input);
						}catch(NumberFormatException e) {
							throw new DbException(input + " is not a valid number");
						}
					}
//  ^ Create a try/catch block to test that the value returned by getStringInput() can be 
//converted to an Integer. The catch block should accept a parameter of type NumberFormatException.
//In the try block, convert the value of input, which is a String, to an Integer and return it. 
//If the conversion is not possible, a NumberFormatException is thrown. The message in the NumberFormatException 
//is totally obscure so it will get fixed in the catch block	
	
	
	
	
// the method that really prints the prompt and gets the input from the user. Create the method getStringInput()	
					private String getStringInput(String prompt) {
						System.out.println(prompt + ": ");
						String input = scanner.nextLine();
						return input.isBlank() ? null : input.trim(); 
					}
	
// Assign a String variable named input to the results of a method call to scanner.nextLine().	
	
////////////////////////////////////////////////////////////////////////////////////////////////	
//Making a new drop meanu with more options
					
					
	
	
	
	
	
	

	}
	
		