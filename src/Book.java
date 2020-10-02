import java.util.Comparator;

public class Book {

		private String title; 
		private int price; 
		private String author; 
		private String ID;
		
		public Book(String title, int price, String author, String ID) { 
			this.title = title; 
			this.price = price; 
			this.author = author; 
			this.ID = ID;
			} 
		public String getTitle() { 
			return title; 
			} 
		public void setTitle(String title) { 
			this.title = title; 
			} 
		public int getPrice() { 
			return price; 
			} 
		public void setPrice(int price) { 
			this.price = price; 
			} 
		public String getAuthor() { 
			return author; 
			} 
		public void setAuthor(String author) { 
			this.author = author; 
			} 
		public String getID() { 
			return ID; 
			} 
		public void setID(String ID) { 
			this.ID = ID; 
			} 
		  @Override public String toString() { 
			return  title + "," + price + "," + author + "," + ID + "\n"; 
			} 
		  
		}

