package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// Este test muestra la correcta visión del listado de las tareas finalizadas y publicas para usuarios auntentificados
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void list(final int recordIndex, final String title, final String begin, final String end,
			final String description, final String link, final String isPublic, final String workload,final String executionPeriod) {
			
			//Nos registramos como un usuario registrado
			super.signIn("Managers1", "Managers1");
			super.clickOnMenu("Authenticated", "Tasks");
			
			//Comprobamos que el listado tiene las correspondientes columnas
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, executionPeriod);
			super.checkColumnHasValue(recordIndex, 2, workload);
			
			//Pinchamos en una tarea
			super.clickOnListingRecord(recordIndex);
			
			//Comprobamos que los campos son correctos
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("begin", begin);
			super.checkInputBoxHasValue("end", end);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("workload", workload);
			super.checkInputBoxHasValue("executionPeriod", executionPeriod);
			
			super.signOut();
			
		}

}
