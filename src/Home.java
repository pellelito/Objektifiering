import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import com.google.gson.JsonIOException;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textPrice;
	private int  changeID;
	private String id;
	private boolean checked = true;
	
	/**
	 * Create the frame.
	 */
	
	public Home() {
		//builds the UI 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 262, 484);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(Home.class.getResource("/images/image.png")));
		panel.add(lblIcon);
		
		textTitle = new JTextField();
		textTitle.setEditable(false);
		textTitle.setBounds(462, 113, 287, 26);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		textAuthor = new JTextField();
		textAuthor.setEditable(false);
		textAuthor.setColumns(10);
		textAuthor.setBounds(462, 140, 287, 26);
		contentPane.add(textAuthor);
		
		textPrice = new JTextField();
		textPrice.setEditable(false);
		textPrice.setColumns(10);
		textPrice.setBounds(462, 168, 287, 26);
		contentPane.add(textPrice);
		

		//Updates text fields when user select in list
		JComboBox<Book> cmbList = new JComboBox<Book>();
		cmbList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] choice = (cmbList.getSelectedItem().toString().split(","));
				textTitle.setText(choice[0]);
				textPrice.setText(choice[1]);
				textAuthor.setText(choice[2]);
				id = choice[3].substring(0, choice[3].length() - 1);
				changeID = cmbList.getSelectedIndex();
			}
		});
		cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
		cmbList.setBounds(462, 41, 287, 27);
		contentPane.add(cmbList);

		// enables for user editing
		JToggleButton tglbtnEdit = new JToggleButton("Edit");
		tglbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnEdit.isSelected()) {  
					setEditble();  		
				}else {		
					
					//Trigger save
					boolean valid = checkInput();
					if (valid) {
						
						//add changes to book
						Book newBook = new Book(textTitle.getText().replace(",","." ),Integer.parseInt(textPrice.getText().trim()),textAuthor.getText().replace(",","." ),id);
						
						try {
							fileHandler.books.set(changeID, newBook); 
							
							try {
								fileHandler.writeToFile();
							} catch (JsonIOException e1) {
								
								// do something
								e1.printStackTrace();
							} catch (IOException e1) {
								
								// do something
								e1.printStackTrace();
							}
							
							// sorts list to user preference
							if (checked == true) {
								arrayHandler.sortList();
							}else {
								arrayHandler.sortListByAuthor();
							}
						} catch (JsonIOException e1) {
							e1.printStackTrace();
						}
						//refreshes the combolist
						cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
						unSetEditble();
						clearText();
					}
		        }	
		    }
		});
		
		tglbtnEdit.setBounds(705, 372, 75, 29);
		contentPane.add(tglbtnEdit);
		
		//just some labels
		JLabel lblBook = new JLabel("Book:");
		lblBook.setBounds(342, 45, 61, 16);
		contentPane.add(lblBook);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(342, 118, 61, 16);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(342, 145, 61, 16);
		contentPane.add(lblAuthor);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(342, 173, 61, 16);
		contentPane.add(lblPrice);

		// lets the user add a new book to library
		JButton btnAddNew = new JButton("Add new");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearText();
				setEditble();		
			}
			
		});
		btnAddNew.setBounds(266, 409, 88, 29);
		contentPane.add(btnAddNew);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Replaces commas for the CSV file
				String title = textTitle.getText().replace(",","." );
				String author = textAuthor.getText().replace(",","." );
				int price = 0;
				try
				{	
					// tries to make an int of user input, otherwise error and price will set to 0
				    price = Integer.parseInt(textPrice.getText());
				}
				catch(NumberFormatException e1)
				{
				  
					//If number is not integer,you will get exception and exception message will be printed
				  System.out.println(e1.getMessage());
				}
				
				//sets an unique ID to book or entry, useless at the moment but handy when scale up
				String ID  = UUID.randomUUID().toString();
				
				//create new book
				boolean valid = checkInput();
				if (valid) {
					
					Book newBook = new Book(title,price,author,ID);				
					unSetEditble();
					clearText();
					
					try { 
						
						//adds to arraylist and updates the file
						fileHandler.addBookToArray(newBook);
						fileHandler.writeToFile();
						
						if (checked == true) {
							arrayHandler.sortList();
						}else {
							arrayHandler.sortListByAuthor();
						}
						
						//updates the combolist as well
						cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
					} catch (JsonIOException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnSave.setBounds(356, 409, 88, 29);
		contentPane.add(btnSave);
		
		// cancel function
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unSetEditble();
				clearText();
				tglbtnEdit.setSelected(false);
			}
		});
		btnCancel.setBounds(446, 409, 88, 29);
		contentPane.add(btnCancel);
		
		// delete function
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(changeID > -1 ){
					try {
						fileHandler.books.remove(changeID);
						if (checked == true) {
							arrayHandler.sortList();
						}else {
							arrayHandler.sortListByAuthor();
						}
						fileHandler.writeToFile();
						unSetEditble();
						clearText();
						cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
					} catch (JsonIOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
		});
		btnDelete.setBounds(705, 412, 75, 29);
		contentPane.add(btnDelete);
		
		//new group for radio buttons
		ButtonGroup group = new ButtonGroup();
		
		// enables list sorted by title
		JRadioButton rdbtnSortByTitle = new JRadioButton("Title");
		group.add(rdbtnSortByTitle);
		rdbtnSortByTitle.setSelected(true);
		rdbtnSortByTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checked = true;
				unSetEditble();
				clearText();
				tglbtnEdit.setSelected(false);
				arrayHandler.sortList();
				cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
			}	
		});
		rdbtnSortByTitle.setBounds(755, 38, 109, 23);
		contentPane.add(rdbtnSortByTitle);
		
		// enables list sorted by author
		JRadioButton rdbtnSortByAuthor = new JRadioButton("Author");
		group.add(rdbtnSortByAuthor);
		rdbtnSortByAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checked = false;
				unSetEditble();
				clearText();
				tglbtnEdit.setSelected(false);
				arrayHandler.sortListByAuthor();
				cmbList.setModel(new DefaultComboBoxModel<Book>(fileHandler.books.toArray(new Book[0])));
			}
		});
		rdbtnSortByAuthor.setBounds(755, 64, 109, 23);
		contentPane.add(rdbtnSortByAuthor);
		
		
	}
	
	//makes the text fields editble
	public void setEditble() {	
		textTitle.setEditable(true);
		textTitle.requestFocus();
		textAuthor.setEditable(true);
		textPrice.setEditable(true);
		
	}
	
	//makes the text fields uneditble
	public void unSetEditble() {	
		textTitle.setEditable(false);
		textAuthor.setEditable(false);
		textPrice.setEditable(false);
		changeID = -1;
		
	}
	
	//clears all text fields
	public void clearText() {
		textTitle.setText("");
		textTitle.setBackground(null);
		textAuthor.setText("");
		textAuthor.setBackground(null);
		textPrice.setText("");
		textPrice.setBackground(null);
		
	}
	
	// check that all required fields are filled in
	public boolean checkInput() {
		
		StringBuilder errorText = new StringBuilder();
				
		if(textTitle.getText().length() == 0){
		    errorText.append("Tile is requierd\n");
		    textTitle.setBackground(Color.red);
		  }
		if(textAuthor.getText().length() == 0){
		    errorText.append("Authoris requierd\n");
		    textAuthor.setBackground(Color.red);
		  }
		if(textPrice.getText().length() == 0){
		    errorText.append("Price is requierd\n");
		    textPrice.setBackground(Color.red);
		  }
		if (errorText.length() > 0){
			JOptionPane.showMessageDialog(null, errorText);
		}
		return errorText.length() == 0;
	}
}
