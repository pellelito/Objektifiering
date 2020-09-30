
public class Book {

		private String title; 
		private int price; 
		private String author; 
		private String cover;
		
		public Book(String title, int price, String author, String cover) { 
			this.title = title; 
			this.price = price; 
			this.author = author; 
			this.cover = cover;
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
		public String getCover() { 
			return cover; 
			} 
		public void setCover(String cover) { 
			this.cover = cover; 
			} 
		  @Override public String toString() { 
			return  title + "," + price + "," + author + "," + cover + "\n"; 
			} 
		}


