package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.CourseList;
import model.MemberList;

public class CourseFrame extends JFrame 
{
	JPanel mini;
	JList subject;
	String[] mySubject;
	MemberList list;
	CourseList courseList;

	private int index;
	public CourseFrame(int index) throws IOException
	{
		this.index = index;
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
				
		mini = new JPanel();
		mini.setBorder(new TitledBorder("Select Courses"));
		mini.setLayout(new BorderLayout());
		
		showSubjectList();
		subjectChose();
		
		JPanel usrePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel nameLabel = new JLabel(list.getMemberList(index).getName());
		nameLabel.setHorizontalAlignment (SwingConstants.RIGHT);
		nameLabel.setIcon(new ImageIcon("userIcon.png"));
		usrePanel.add(nameLabel);
		usrePanel.setBackground(new Color(179, 235, 255));
		usrePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		add(usrePanel, BorderLayout.NORTH);
		
		main.add(mini, BorderLayout.CENTER);
		add(main);
		setSize(450, 450);
		setTitle("Grading System");
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
	public void subjectChose() 
	{
		subject.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() == 1 || e.getClickCount() == KeyEvent.VK_ENTER) 
				{
					String subjectStr = String.valueOf(subject.getSelectedValue());
					try 
					{
						new DetailFrame(index , subjectStr);
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					dispose();
				}
			}
		});
	}
	
	public void showSubjectList() throws IOException 
	{
		list = new MemberList();
		courseList = new CourseList();
		mySubject = new String[list.getMemberList(index).getSizeSubject()];
		for (int i = 0; i < list.getMemberList(index).getSizeSubject(); i++) 
		{
			for(int j = 0 ; j<courseList.getSize() ; j++ )
			{
				if(list.getMemberList(index).getSubjectInIndex(i).equalsIgnoreCase(courseList.getCourse(j).getCourseID()))
				{
					mySubject[i] = courseList.getCourse(j).toString();
				}
			}
		}
		subject = new JList(mySubject);
		mini.add(subject);
	}

}
