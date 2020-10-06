import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class fileHandler {
	static File file = new File("books.csv");
	static Gson gson = new Gson();
	static List<Book> books = new ArrayList<Book>();
	
	public static void openFile() throws IOException, InterruptedException {

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		readBooksFromCSV("books.csv");
		
	}
	
	public static void writeToFile() throws JsonIOException, IOException {
		//will write to file
		FileWriter writer = new FileWriter(file);
		books.forEach((n) -> {
			try {
				writer.write(n.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        writer.close(); 
	}
	
	public static List<Book> readBooksFromCSV(String fileName) throws InterruptedException { 
		
		 
		Path pathToFile = Paths.get(fileName); 
		// create an instance of BufferedReader 
		try (
				BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { 
			// read the first line from the text file 
			String line = br.readLine(); 
			
			// loop until all lines are read 
			while (line != null) { 
				
				// use string.split to load a string array with the values from 
				// each line of the file, using a comma as the delimiter
			 
				String[] attributes = line.split(",");
				
				Book book = createBook(attributes); 
				
				// adding book into ArrayList 
				addBookToArray(book);
				// read next line before looping 
				// if end of file reached, line would be null 
				line = br.readLine(); 
				
				} 
			} catch (IOException ioe) { ioe.printStackTrace(); 
		} 
		//Sort my arraylist
		arrayHandler.sortList();
		return books;			
		}
	
		public static void addBookToArray(Book book) {
			books.add(book);
		}
		
		private static Book createBook(String[] metadata) { 
			// create and return book of this metadata
			String name = metadata[0]; 
			int price = Integer.parseInt(metadata[1]); 	
			String author = metadata[2]; 
			String ID = metadata[3];

			return new Book(name, price, author, ID); 
			}
			

}
