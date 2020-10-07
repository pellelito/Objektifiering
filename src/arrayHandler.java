import java.util.Collections;

public class arrayHandler {
	
	//method for sorting list by title
	public synchronized static void sortList() {
		
		Collections.sort(fileHandler.books, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
		
	}
	
	//method for sorting list by author
	public synchronized static void sortListByAuthor() {
			
			Collections.sort(fileHandler.books, (o1, o2) -> o1.getAuthor().compareTo(o2.getAuthor()));
			
		}
	

}	
	