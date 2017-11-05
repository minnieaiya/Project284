package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import model.Member;
import model.MemberList;
import model.ProfileException;

public class ProfileMember extends JFrame
{
	JLabel name , email , username , password;
	JTextField nameTxt, emailTxt , usernameTxt;
	JPasswordField passwordTxt;
	Member member;
	JButton setting;
	MemberList memberList;
	Color backgroundColor;
	public ProfileMember(Member member) throws IOException 
	{
		memberList = new MemberList();
		this.member = member;
		name = new JLabel("Name:  ");		
		email = new JLabel("E-mail: ");
		username = new JLabel("Username: ");
		password = new JLabel("Password:  ");	
		
		nameTxt = new JTextField(22);
		nameTxt.setText(member.getName());
		emailTxt = new JTextField(22);
		emailTxt.setText(member.getEmail());
		usernameTxt = new JTextField(20);
		usernameTxt.setText(member.getUsername());
		passwordTxt = new JPasswordField(20);
		passwordTxt.setText(member.getPassword());
		setEditInfo(false);
		
		setting = new JButton("Setting");
		setting.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		setting.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getActionCommand().equalsIgnoreCase("Setting"))
				{
					setEditInfo(true);
					setting.setText("Save");
					try {
						updateProfile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					setEditInfo(false);
					setting.setText("Setting");
					try {
						updateProfile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		setSettingPanel();
		setUserPanel();
		setTitle(member.getFirstname()+"'s Profile.");
		setVisible(true);
		setSize(500, 300);	
		setLocationRelativeTo(null);
	}
	public void updateProfile() throws IOException
	{
		String email = emailTxt.getText();
		char pass[] = passwordTxt.getPassword();
		try
		{
			String emailForm[] = email.split("@");
			String emailForm2 = email.substring(email.length()-4);
			String emailForm3 = email.substring(email.length()-6);
			int cnt = 0;
			if(pass.length < 8)
			{
				throw new ProfileException("Passwords must be at least 8 characters.");
			}
			else if(emailForm.length != 2)
			{
				throw new ProfileException("Email address is invalid.");
			}
			else if(emailForm2.equals(".com"))
			{
				cnt++;
			}
			else if(emailForm3.equals(".co.th"))
			{
				cnt++;
			}
			else if(cnt == 0)
			{
				throw new ProfileException("Email address is invalid.");
			}
			member.setEmail(email);
			String passStr = "";
			for(int i=0 ; i<pass.length ; i++)
			{
				passStr = passStr + pass[i];				
			}
			member.setPassword(passStr);
			memberList.updateFile(member);
		}
		catch (ProfileException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("bee.png"));
			if(ex.getMessage().equalsIgnoreCase("Email address is invalid."))
			{
				emailTxt.setText(member.getEmail());
			}
			else
			{
				passwordTxt.setText(member.getPassword());
			}
			setEditInfo(true);
			setting.setText("Save");
		}
				
	}
	public void setEditInfo(boolean check)
	{
		Color txtColor = new Color(255, 255, 204);
		if(!check)
		{
			emailTxt.setEditable(false);
			emailTxt.setBackground(txtColor);
			passwordTxt.setEditable(false);
			passwordTxt.setBackground(txtColor);
		}
		else
		{
			emailTxt.setEditable(true);
			emailTxt.setBackground(new Color(179, 235, 255));
			passwordTxt.setEditable(true);
			passwordTxt.setBackground(new Color(179, 235, 255));
		}
		nameTxt.setEditable(false);
		nameTxt.setBackground(txtColor);
		usernameTxt.setEditable(false);
		usernameTxt.setBackground(txtColor);
	}
	public void setSettingPanel()
	{
		backgroundColor = Color.WHITE;
		JPanel settingPanel = new JPanel(new GridLayout(4, 1));
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.setBackground(backgroundColor);
		namePanel.add(name);
		namePanel.add(nameTxt);
		
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailPanel.setBackground(backgroundColor);
		emailPanel.add(email);
		emailPanel.add(emailTxt);

		
		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernamePanel.setBackground(backgroundColor);
		usernamePanel.add(username);
		usernamePanel.add(usernameTxt);

		
		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordPanel.setBackground(backgroundColor);
		passwordPanel.add(password);
		passwordPanel.add(passwordTxt);


		settingPanel.add(namePanel);
		settingPanel.add(emailPanel);
		settingPanel.add(usernamePanel);
		settingPanel.add(passwordPanel);
		
		add(settingPanel);
		settingPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED , Color.WHITE, new Color(0,170,230)) );

	}
	
	public void setUserPanel()
	{
		JPanel userPanel = new JPanel(new BorderLayout());
		JLabel nameLabel = new JLabel(member.getName(),0);
		nameLabel.setIcon(new ImageIcon("userIcon.png"));
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		nameLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JLabel subjectLabel = new JLabel("   "+member.allSubject()+"   ",0);
		subjectLabel.setIcon(new ImageIcon("criterionIcon.png"));
		subjectLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		subjectLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		userPanel.add(nameLabel, BorderLayout.NORTH);
		userPanel.add(subjectLabel, BorderLayout.CENTER);
		userPanel.setBackground(new Color(179, 235, 255));
		userPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		userPanel.add(setting, BorderLayout.SOUTH);
		
		add(userPanel, BorderLayout.WEST);	
	}
	
}
