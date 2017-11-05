package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.LoginController;

public class LoginForm extends JFrame implements ActionListener
{
	JLabel logo;
	JTextField username;
	JPasswordField password;
	JLabel userLabel , passLabel;
	JButton login , cancel;
	JPanel mainPanel;
	Color backgroundColor = new Color(179, 235, 255);
	public LoginForm() throws IOException 
	{
		mainPanel = new JPanel(new BorderLayout());
		setLogo();
		setLoginControl();
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Login"));
		mainPanel.setBackground(backgroundColor);

		add(mainPanel);
		setTitle("Grading System");
		setSize(new Dimension(450, 350));
		setResizable(false);
		setLocationRelativeTo(null);  
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setLogo() 
	{
		logo = new JLabel (new ImageIcon("TU-logo.png"));
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logoPanel.add(logo);
		logo.setText("Thammasat University.");
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		mainPanel.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setBackground(new Color(179, 235, 255));

	}
	public void setLoginControl() 
	{
		username = new JTextField(15);
		password = new JPasswordField(15);
		userLabel = new JLabel("Username:   ");
		userLabel.setIcon(new ImageIcon("userIcon.png"));
		passLabel = new JLabel("Password:   ");
		passLabel.setIcon(new ImageIcon("passwordIcon.png"));
		
		JPanel userPanel = new JPanel(new FlowLayout());
		userPanel.add(userLabel);
		userPanel.add(username);
		
		JPanel passPanel = new JPanel(new FlowLayout());
		passPanel.add(passLabel);
		passPanel.add(password);
		
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		JPanel loginPanel = new JPanel(new FlowLayout());
		loginPanel.add(login);
		loginPanel.add(cancel);
		login.setBackground(Color.WHITE);
		cancel.setBackground(Color.WHITE);
		login.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		cancel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		login.addActionListener(this);
		cancel.addActionListener(this);
		
		JPanel controlPanel = new JPanel(new GridLayout(3, 1));
		controlPanel.add(userPanel);
		controlPanel.add(passPanel);
		controlPanel.add(loginPanel);
		mainPanel.add(controlPanel);
		userPanel.setBackground(backgroundColor);
		loginPanel.setBackground(backgroundColor);
		passPanel.setBackground(backgroundColor);
	}	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();
		if(str.equalsIgnoreCase("Cancel"))
		{
			username.setText("");
			password.setText("");
		}
		if(str.equalsIgnoreCase("Login"))
		{
			try 
			{
				if(username.getText().equals("") && password.getPassword().length == 0)
				{
					throw new LoginException();
				}
				else
				{
					LoginController controller = new LoginController(username.getText() , password.getPassword());
					boolean check = controller.disposeLoginForm();
					if(check)
					{
						dispose();
					}
				}
			} 
			catch (IOException | LoginException ex) {}
		}
	}	
	public static void main(String[] args) throws IOException 
	{
		new LoginForm();		
	}

}

