package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskPublishTest extends AcmePlannerTest{
	
		// Lifecycle management ---------------------------------------------------
	
		// Test cases -------------------------------------------------------------
		//Esta caso de puebra consiste en publicar tareas de manera correcta por un manager
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void publihsPositive(final int recordIndex, final String title, final String begin, final String end,
			final String description, final String link, final String isPublic, final String workload) {
			
			super.signIn("Managers2", "Managers2");
			
			//Se accede al listado de tareas a traves del menu
			super.clickOnMenu("Managers", "Task list");
			
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 2, workload);
			
			super.clickOnListingRecord(recordIndex);
			
			if(isPublic.equals("true"))
				super.clickOnSubmitButton("Privatize");
			else
				super.clickOnSubmitButton("Publish");
			
			//Por último se comprueba la que la tarea se ha insertado correctamente
			super.clickOnMenu("Managers", "Task list");
			
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 2, workload);

			super.clickOnListingRecord(recordIndex);
		
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("begin", begin);
			super.checkInputBoxHasValue("end", end);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			if(isPublic.equals("true"))
				super.checkInputBoxHasValue("isPublic", "false");
			else
				super.checkInputBoxHasValue("isPublic", "true");
			super.checkInputBoxHasValue("workload", workload);

			super.signOut();
			
		}
		
		//Esta caso de puebra consiste en publicar tareas de manera correcta por un manager
		@ParameterizedTest
		@CsvFileSource(resources = "/manager/task/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void publihsNegative(final int recordIndex, final String title, final String begin, final String end,
			final String description, final String link, final String isPublic, final String workload) {
					
			super.signIn("Managers1", "Managers1");
					
			//Se accede al listado de tareas a traves del menu
			super.clickOnMenu("Managers", "Task list");
					
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 2, workload);
			super.checkColumnHasValue(recordIndex, 3, isPublic);
					
			super.clickOnListingRecord(recordIndex);
					
			super.clickOnSubmitButton("Privatize");
			
			//Se comprueba que ha saltado algún error durante la inserción
			super.checkErrorsExist();	

			super.signOut();
					
				}

}
