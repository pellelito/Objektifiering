import java.awt.EventQueue;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.JsonIOException;

import javax.swing.event.ChangeEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textPrice;
	private JTextField textCover;

	

	/**
	 * Create the frame.
	 */
	public Home() {
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
		
		JLabel lblShowCover = new JLabel("No Cover");
		lblShowCover.setBounds(472, 234, 268, 153);
		contentPane.add(lblShowCover);
		
		JComboBox cmbList = new JComboBox();
		cmbList.setBounds(462, 41, 287, 27);
		contentPane.add(cmbList);
		
		JToggleButton tglbtnEdit = new JToggleButton("Edit");
		tglbtnEdit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnEdit.isSelected())  
					setEditble();  
		        else  
		        	unSetEditble();  
		    }  
			}
		);
		
		tglbtnEdit.setBounds(776, 449, 75, 29);
		contentPane.add(tglbtnEdit);
		
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
		
		textCover = new JTextField();
		textCover.setEditable(false);
		textCover.setColumns(10);
		textCover.setBounds(462, 196, 287, 26);
		contentPane.add(textCover);
		
		JLabel lblCover = new JLabel("Cover:");
		lblCover.setBounds(342, 201, 61, 16);
		contentPane.add(lblCover);
		
		JButton btnAddNew = new JButton("Add new");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearText();
				setEditble();		
			}
			
		});
		btnAddNew.setBounds(262, 449, 117, 29);
		contentPane.add(btnAddNew);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String title = textTitle.getText();
				String author = textAuthor.getText();
				int price = 0;
				try
				{
				    price = Integer.parseInt(textPrice.getText());
				}
				catch(NumberFormatException e1)
				{
				  //If number is not integer,you will get exception and exception message will be printed
				  System.out.println(e1.getMessage());
				}			
				String cover = textCover.getText();
				
				//create new book
				boolean valid = checkInput();
				if (valid) {
					
					Book newBook = new Book(title,price,author,cover);
					unSetEditble();
					clearText();
					try {
						fileHandler.writeToFile(newBook);
					} catch (JsonIOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(newBook.toString());
				}
				
			}
		});
		btnSave.setBounds(374, 449, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unSetEditble();
				clearText();
			}
		});
		btnCancel.setBounds(487, 449, 117, 29);
		contentPane.add(btnCancel);
	}
	//makes the text fields editble
	public void setEditble() {	
		textTitle.setEditable(true);
		textTitle.requestFocus();
		textAuthor.setEditable(true);
		textPrice.setEditable(true);
		textCover.setEditable(true);	
	}
	//makes the text fields uneditble
	public void unSetEditble() {	
		textTitle.setEditable(false);
		textAuthor.setEditable(false);
		textPrice.setEditable(false);
		textCover.setEditable(false);
	}
	//clears all text fields
	public void clearText() {
		textTitle.setText("");
		textTitle.setBackground(Color.white);
		textAuthor.setText("");
		textAuthor.setBackground(Color.white);
		textPrice.setText("");
		textPrice.setBackground(Color.white);
		textCover.setText("");
	}
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
