package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousTaskListTest extends AcmeWorkPlansTest{
	
	// Lifecycle management ---------------------------------------------------
	
    // Test cases -------------------------------------------------------------
	
	// Este caso comprueba el correcto funcionamiento del listado de las tareas no finalizadas públicas y sus detalles
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void list(final int recordIndex, final String title, final String begin, final String end,
			final String description, final String link, final String isPublic, final String workload,final String executionPeriod) {
			
			//Accedemos al listado de las tareas desde el menus de anonimo 
			super.clickOnMenu("Anonymous", "Tasks list");
			
			//Comprueba que las columnas del listado están correctamente
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, executionPeriod);
			super.checkColumnHasValue(recordIndex, 2, workload);
			
			//Accedemos a una tarea en cuestión
			super.clickOnListingRecord(recordIndex);
			
			//Comprobamos los detalles de la tarea seleccionada anteriormente
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("begin", begin);
			super.checkInputBoxHasValue("end", end);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("workload", workload);
			super.checkInputBoxHasValue("executionPeriod", executionPeriod);
			
		}

}
