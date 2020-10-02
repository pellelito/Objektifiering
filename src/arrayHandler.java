import java.util.Collections;

public class arrayHandler {
	
	public synchronized static void sortList() {
		
		Collections.sort(fileHandler.books, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
		
	}
	
	

}	
	