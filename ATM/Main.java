package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main
{
	public static void main(String args[])
	{
		new Login();
	}
}

class LinkList 
{
	public Node head;

	public static class Node{
		
		String user="",password="",phone="",address="",email="";
		float balance=0;
		Node next;
		Node(String us, String pass, String phone, String address, String email, float balance)
		{
			this.user=us;
			this.password=pass;
			this.phone=phone;
			this.address=address;
			this.email=email;
			this.balance=balance;
			this.next=null;
		}
	}
	
	public void addUser(String us, String pass, String phone, String address, String email, float balance)
	{
		if (head != null) {
			Node n = new Node(us, pass, phone, address, email, balance);
			n.next = head;
			head = n;
			
		  } else {
			head = new Node(us, pass, phone, address, email, balance);
		  }
	}
}

class AccountsData {

	static LinkList usersData = new LinkList();
  
	AccountsData() {
	  //void addUser(String us, String pass, String phone, String address, String email, float balance)
	  usersData.addUser(
		"arif123",
		"achayaar",
		"0316-xxxxxxx",
		"Street 1,House C-40,Sukkur Sindh",
		"arif33717@gmail.com",
		15390
	  );
	  usersData.addUser(
		"adil268",
		"ad268",
		"0311-xxxxxxx",
		"Street x,House C-xx,Sukkur Sindh",
		"adilxxxx@gmail.com",
		2325
	  );
	  usersData.addUser(
		"bilal0",
		"javax",
		"0315-xxxxxxx",
		"Street x,House C-xx,Sukkur Sindh",
		"bilalxxx@gmail.coom",
		45466
	  );
	  usersData.addUser(
		"uzair565",
		"456",
		"0333-xxxxxxx",
		"Street x,House C-xx,Sukkur Sindh",
		"uzairxxx@gmail.com",
		67780
	  );
	}

	LinkList.Node CheckUser(String userName, String password) {
		System.out.println("CheckUser initiated");
	
		LinkList.Node cur = usersData.head;
		while (cur != null) {
		  if ((cur.user).equals(userName)) {
			if ((cur.password).equals(password)) {
				System.out.println("Credentials matched");
			  return cur;
			}
			break;
		  }
		  cur = cur.next;
		}
		return null;
	  }
}


class Login extends AccountsData implements ActionListener
{
	JFrame loginframe = new JFrame("Login to ATM");
	JButton login,cancel,bregister;
	JTextField userfield = new JTextField();
	JTextField passfield = new JTextField();
	JLabel loginlabel = new JLabel("Login");
	JPanel panel1= new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	
	Login()
	{	
		loginframe.setLayout(new BorderLayout());
		
		//Create Components
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		bregister = new JButton("Register?");
		
		
		//Edit component
		userfield.setBackground(Color.black);
		userfield.setForeground(Color.white);
		userfield.setCaretColor(Color.white);
		userfield.setPreferredSize(new Dimension(170,30));
		userfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		userfield.setText("Username");
		
		passfield.setBackground(Color.black);
		passfield.setForeground(Color.white);
		passfield.setCaretColor(Color.white);
		passfield.setPreferredSize(new Dimension(170,30));
		passfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		passfield.setText("Password");

		
		loginlabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		loginlabel.setForeground(Color.white);
		
		panel1.setLayout( new FlowLayout());
		panel1.setBackground(new Color(0,114,160));

		panel2.setLayout( new FlowLayout());
		panel2.setBackground(new Color(0,114,160));
		

		panel3.setLayout( new FlowLayout());
		panel3.setBackground(new Color(0,114,160));

		bregister.setForeground(Color.black);
		bregister.setFocusable(false);
		
		login.setForeground(Color.black);
		login.setFocusable(false);
		
		cancel.setForeground(Color.black);
		cancel.setFocusable(false);
		
		//Add Component
		panel1.add(loginlabel);
		panel2.add(userfield);
		panel2.add(passfield);
		panel2.add(login);
		panel2.add(cancel);
		panel3.add(bregister);
		loginframe.add(panel1, BorderLayout.NORTH);
		loginframe.add(panel2, BorderLayout.CENTER);
		loginframe.add(panel3, BorderLayout.SOUTH);
	
		//Action Listenera
		login.addActionListener(this);
		cancel.addActionListener(this);
		bregister.addActionListener(this);
		
		
		
		
		//Frame
		loginframe.getContentPane().setBackground(new Color(123,50,250));
		loginframe.setSize(400,250);
		loginframe.setResizable(false);
		loginframe.setVisible(true);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==bregister)
		{
			//System.out.println("Feature in Process: * Contact near Bank branch for now *");
			new RegisterAccount();
		}
		
		else if(e.getSource()==cancel)
		{
			loginframe.dispose();
		}
		
		else if(e.getSource()==login)
		{
	
			String u_name = userfield.getText().toString();
			String u_pass = passfield.getText().toString();
			
			LinkList.Node check = CheckUser(u_name,u_pass);
	
			if(check != null)
			{
				JOptionPane.showMessageDialog(null, "Login Successfully", "Logined", JOptionPane.INFORMATION_MESSAGE);
				loginframe.dispose();
				new ATM(check);
			}
			else
			JOptionPane.showMessageDialog(null, "Wrong User and Password", "Login", JOptionPane.ERROR_MESSAGE);
		}
	}
}

class ATM implements ActionListener
{
	LinkList.Node currentUser ;
	JFrame ATMframe = new JFrame("Online ATM");
	JButton bdeposite,bwithdraw,bcheckbalance,bexit,baccinfo,bother;

	JTextField inputfield = new JTextField();

	JLabel atmlabel = new JLabel("ATM");

	JLabel namelabel = new JLabel("Name:\t");
	JLabel nameoutput = new JLabel("");
	JLabel balancelabel = new JLabel("Balance:\t");
	JLabel balanceoutput = new JLabel("$0.0");
	JLabel displaymsg = new JLabel("Enter Amount");
	JPanel panel= new JPanel();
	JPanel panel2= new JPanel();
	
	ATM(LinkList.Node list)
	{
		currentUser=list;
		
		String bal = "$"+Float.toString(currentUser.balance);
		nameoutput.setText(currentUser.user);
		balanceoutput.setText(bal);

		ATMframe.setLayout(new BorderLayout());
		ATMframe.setBackground(new Color(0,114,160));

		panel.setLayout(new FlowLayout());
		panel.setBackground(new Color(0,114,160));

		panel2.setLayout(new GridLayout(4,1));
		panel2.setBackground(new Color(0,114,160));
		
		displaymsg.setFont(new Font("Georgia", Font.PLAIN, 14));
		displaymsg.setForeground(Color.white);
		namelabel.setFont(new Font("Georgia", Font.PLAIN, 14));
		namelabel.setForeground(Color.white);
		nameoutput.setFont(new Font("SansSarif Italic", Font.ITALIC, 17));
		nameoutput.setForeground(Color.white);
		balancelabel.setFont(new Font("Georgia", Font.PLAIN, 14));
		balancelabel.setForeground(Color.white);
		balanceoutput.setFont(new Font("SansSarif Italic", Font.ITALIC, 17));
		balanceoutput.setForeground(Color.white);

		inputfield.setBackground(Color.black);
		inputfield.setForeground(Color.white);
		inputfield.setCaretColor(Color.white);
		inputfield.setPreferredSize(new Dimension(170,20));
		inputfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		inputfield.setText("");

		bwithdraw = new JButton("Withdraw");
		bdeposite = new JButton("Deposite");
		bcheckbalance = new JButton("View Balance");
		baccinfo = new JButton("Account Info");
		bexit =new JButton("Logout");
		bother = new JButton("Other");
	
		//Turn off focus while clicking on buttons
		bwithdraw.setFocusable(false);
		bdeposite.setFocusable(false);
		bcheckbalance.setFocusable(false);
		baccinfo.setFocusable(false);
		bexit.setFocusable(false);
		bother.setFocusable(false);
		
		panel.add(bwithdraw);
		panel.add(bdeposite);
		//panel.add(bcheckbalance);
		panel.add(baccinfo);
		//panel.add(bother);
		panel.add(displaymsg);
		panel.add(inputfield);
		panel.add(bexit);
		panel2.add(namelabel);
		panel2.add(nameoutput);
		panel2.add(balancelabel);
		panel2.add(balanceoutput);
		ATMframe.add(panel, BorderLayout.CENTER);
		ATMframe.add(panel2, BorderLayout.WEST);
		
		
		//Action listener 
		bwithdraw.addActionListener(this);
		bdeposite.addActionListener(this);
		baccinfo.addActionListener(this);
		bexit.addActionListener(this);
		//bother.addActionListener(this);
		//bcheckbalance.addActionListener(this);
		
		ATMframe.setSize(400,220);
		ATMframe.setResizable(false);
		ATMframe.setVisible(true);
		ATMframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		String temp="";
		int amount=0;
		
		if(e.getSource()==bwithdraw)
		{
	
			if(inputfield.getText().equals(""))
			{
			JOptionPane.showMessageDialog(null, "Enter amount please", "Input", JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				temp=inputfield.getText();
				amount=Integer.parseInt(temp);
				
				if(amount>currentUser.balance)
				{
				JOptionPane.showMessageDialog(null, "Amount not available ", "Input", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					currentUser.balance-=amount;
					JOptionPane.showMessageDialog(null, "Withdraw successful", "", JOptionPane.INFORMATION_MESSAGE);
					String balance = "$"+Float.toString(currentUser.balance);
					balanceoutput.setText(balance);
				}
			}
		}
		
		else if(e.getSource()==bdeposite)
		{
			
			if(inputfield.getText().equals(""))
			{
			JOptionPane.showMessageDialog(null, "Enter amount please", "Input", JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				temp=inputfield.getText();
				amount=Integer.parseInt(temp);
				
				if(amount<=0)
				{
				JOptionPane.showMessageDialog(null, "Amount can't be negative", "Input", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					currentUser.balance +=amount;
					JOptionPane.showMessageDialog(null, "Deposit successful", "", JOptionPane.INFORMATION_MESSAGE);
					String balance = "$"+Float.toString(currentUser.balance);
					balanceoutput.setText(balance);
				}
			} 
		}
		 
		else if(e.getSource()==baccinfo)
		{
			JOptionPane.showMessageDialog(null, getAccountInfo() , "Account Information", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource()==bexit)
		{
			new Login();
			ATMframe.dispose();
		}
	}
	
	String getAccountInfo()
	{
		String s = 
		"Username: "+currentUser.user+
		"\nPassword: "+currentUser.password+
		"\nContact: "+currentUser.phone+
		"\nEmail: "+currentUser.email+
		"\nAddress: "+currentUser.address;

		return s;
	}
	
}

class RegisterAccount extends AccountsData implements ActionListener
{
	JFrame registerframe = new JFrame("Register your Account");
	JButton bSignup,bcancel;
	JTextField userfield = new JTextField();
	JTextField passfield = new JTextField();
	JTextField phonefield = new JTextField();
	JTextField emailfield = new JTextField();
	JTextField addressfield = new JTextField();

	JLabel createlabel = new JLabel("Create Account");
	JLabel userlabel = new JLabel("Username");
	JLabel passlabel = new JLabel("Password");
	JLabel phonelabel = new JLabel("Phone");
	JLabel emaillabel = new JLabel("Email");
	JLabel addresslabel = new JLabel("Address");

	JLabel warninglabel = new JLabel("Mesage: ");
	JPanel panel1= new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	
	RegisterAccount()
	{	
		registerframe.setLayout(new BorderLayout());
		
		//Create Buttons
		bSignup = new JButton("Signup");
		bcancel = new JButton("Cancel");
				
		//Edit component
		userfield.setBackground(Color.black);
		userfield.setForeground(Color.white);
		userfield.setCaretColor(Color.white);
		userfield.setPreferredSize(new Dimension(170,30));
		userfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		userfield.setText("");
		
		passfield.setBackground(Color.black);
		passfield.setForeground(Color.white);
		passfield.setCaretColor(Color.white);
		passfield.setPreferredSize(new Dimension(170,30));
		passfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		passfield.setText("");

		phonefield.setBackground(Color.black);
		phonefield.setForeground(Color.white);
		phonefield.setCaretColor(Color.white);
		phonefield.setPreferredSize(new Dimension(170,30));
		phonefield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		phonefield.setText("");

		emailfield.setBackground(Color.black);
		emailfield.setForeground(Color.white);
		emailfield.setCaretColor(Color.white);
		emailfield.setPreferredSize(new Dimension(170,30));
		emailfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		emailfield.setText("");

		addressfield.setBackground(Color.black);
		addressfield.setForeground(Color.white);
		addressfield.setCaretColor(Color.white);
		addressfield.setPreferredSize(new Dimension(170,30));
		addressfield.setFont(new Font("SansSarif Italic", Font.PLAIN, 14));
		addressfield.setText("");


		createlabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		createlabel.setForeground(Color.white);
		warninglabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		warninglabel.setForeground(Color.white);
		userlabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		userlabel.setForeground(Color.white);
		passlabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		passlabel.setForeground(Color.white);
		phonelabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		phonelabel.setForeground(Color.white);
		emaillabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		emaillabel.setForeground(Color.white);
		addresslabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		addresslabel.setForeground(Color.white);
		
		panel1.setLayout( new FlowLayout());
		panel1.setBackground(new Color(0,114,160));

		panel2.setLayout( new FlowLayout());
		panel2.setBackground(new Color(0,114,160));
		

		panel3.setLayout( new FlowLayout());
		panel3.setBackground(new Color(0,114,160));

		bSignup.setForeground(Color.black);
		bSignup.setFocusable(false);
		
		bcancel.setForeground(Color.black);
		bcancel.setFocusable(false);
		
		//Add Component
		panel1.add(createlabel);
		panel2.add(userlabel);
		panel2.add(userfield);
		panel2.add(passlabel);
		panel2.add(passfield);
		panel2.add(addresslabel);
		panel2.add(addressfield);
		panel2.add(phonelabel);
		panel2.add(phonefield);
		panel2.add(emaillabel);
		panel2.add(emailfield);
		
		panel3.add(bSignup);
		panel3.add(bcancel);
		//panel3.add(warninglabel);
		registerframe.add(panel1, BorderLayout.NORTH);
		registerframe.add(panel2, BorderLayout.CENTER);
		registerframe.add(panel3, BorderLayout.SOUTH);
	
		//Action Listeners
		bSignup.addActionListener(this);
		bcancel.addActionListener(this);
			
		//Frame
		registerframe.getContentPane().setBackground(new Color(123,50,250));
		registerframe.setSize(300,350);
		registerframe.setResizable(false);
		registerframe.setVisible(true);
		registerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==bcancel)
		{
			registerframe.dispose();
		}
		
		else if(e.getSource()==bSignup)
		{
			String u_name = userfield.getText();
			String u_pass = passfield.getText();
			String u_address = addressfield.getText();
			String u_phone = phonefield.getText();
			String u_email = emailfield.getText();

			if(u_name.equals("")  || u_pass.equals("") || u_address.equals("") || u_phone.equals("") || u_email.equals(""))
			{
				//warninglabel.setText("Please fill all fields");
				JOptionPane.showMessageDialog(null, "All fields must be filled", "Register Account", JOptionPane.WARNING_MESSAGE);

			}
			else
			{
				usersData.addUser(u_name, u_pass, u_phone , u_address , u_email , 0);
				JOptionPane.showMessageDialog(null, "Registered Successfully: "+u_name, "Register Account", JOptionPane.INFORMATION_MESSAGE);
				registerframe.dispose();
			}
		}
	}
}
