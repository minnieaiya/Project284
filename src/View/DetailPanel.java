package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Course;
import model.CourseList;

public class DetailPanel extends JPanel {
	private JButton settingBtn, saveBtn, fillScoreBtn;
	private JLabel fullLabel, accumulatedLabel, assignLabael, midLabel, finalLabel;
	private JTextField assfTxt, midfTxt, finalfTxt, assaTxt, midaTxt, finalaTxt;
	private JPanel panel, panelS;
	private ImageIcon icon , icon1;
	private Course course;
	CourseList courseList;
	public DetailPanel(Course course) throws IOException {
		courseList = new CourseList();
		this.course = course;
		icon = new ImageIcon("asss.png");
		icon1 = new ImageIcon("bee.png");
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 0));
		panel.setBorder(BorderFactory.createTitledBorder("Course Detail"));

		JPanel panelBtn = new JPanel();
		settingBtn = new JButton("Setting");
		fillScoreBtn = new JButton("FillScore");
		panelBtn.add(settingBtn);
		panelBtn.add(fillScoreBtn);
		settingBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		fillScoreBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		settingBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Setting")) {
					setTxt(true);
					settingBtn.setText("Save");
				}
				try {
					setFullScore();
					setAccumulatedScore();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if (e.getActionCommand().equals("Save")) {
					setTxt(false);
					settingBtn.setText("Setting");
				}

			}
		});

		JPanel panelG = new JPanel();
		JLabel labelG = new JLabel(icon);
		panelG.setLayout(new BorderLayout());
		panelG.add(labelG, BorderLayout.CENTER);
		panel.add(panelG, BorderLayout.CENTER);

		panelS = new JPanel();
		panelS.setLayout(new GridLayout(4, 0));
		JPanel panelLabel = new JPanel();
		JLabel empty = new JLabel("                              ");
		fullLabel = new JLabel("                                                    Full Score");
		accumulatedLabel = new JLabel("Accumulated Score");

		panelLabel.add(fullLabel);
		panelLabel.add(empty);
		panelLabel.add(accumulatedLabel);
		panelS.add(panelLabel);
		setAssign();
		setMidterm();
		setfinalterm();
		setTxt(false);
		panel.add(panelS);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(450, 450));
		this.add(panel, BorderLayout.CENTER);
		this.add(panelBtn, BorderLayout.SOUTH);
		this.setVisible(true);

	}

	public void setAssign() {
		JPanel assPanel = new JPanel();
		JLabel assLabel = new JLabel("                        ");
		assignLabael = new JLabel("Assignment                 ");
		assfTxt = new JTextField();
		assfTxt.setPreferredSize(new Dimension(100, 20));
		assfTxt.setText(course.getAssFull()+"");
		assaTxt = new JTextField();
		assaTxt.setPreferredSize(new Dimension(100, 20));
		assaTxt.setText(course.getAssAcc()+"");
		assPanel.add(assignLabael);
		assPanel.add(assfTxt);
		assPanel.add(assLabel);
		assPanel.add(assaTxt);
		panelS.add(assPanel);
	}

	public void setMidterm() {
		JPanel midPanel = new JPanel();
		JLabel midtermLabel = new JLabel("                         ");
		midLabel = new JLabel("Midterm                        ");
		midfTxt = new JTextField();
		midfTxt.setPreferredSize(new Dimension(100, 20));
		midfTxt.setText(course.getMidFull()+"");
		midaTxt = new JTextField();
		midaTxt.setPreferredSize(new Dimension(100, 20));
		midaTxt.setText(course.getMidAcc()+"");

		midPanel.add(midLabel);
		midPanel.add(midfTxt);
		midPanel.add(midtermLabel);
		midPanel.add(midaTxt);
		panelS.add(midPanel);

	}

	public void setfinalterm() {
		JPanel finalPanel = new JPanel();
		JLabel fiLabel = new JLabel("                         ");
		finalLabel = new JLabel("Final                               ");
		finalfTxt = new JTextField();
		finalfTxt.setPreferredSize(new Dimension(100, 20));
		finalfTxt.setText(course.getFinalFull()+"");
		finalaTxt = new JTextField();
		finalaTxt.setPreferredSize(new Dimension(100, 20));
		finalaTxt.setText(course.getFinalAcc()+"");

		finalPanel.add(finalLabel);
		finalPanel.add(finalfTxt);
		finalPanel.add(fiLabel);
		finalPanel.add(finalaTxt);
		panelS.add(finalPanel);

	}

	public void setTxt(boolean check) 
	{
		if(check)
		{
			assfTxt.setEditable(true);
			assaTxt.setEditable(true);
			midfTxt.setEditable(true);
			midaTxt.setEditable(true);
			finalfTxt.setEditable(true);
			finalaTxt.setEditable(true);
		}
		else
		{
			assfTxt.setEditable(false);
			assaTxt.setEditable(false);
			midfTxt.setEditable(false);
			midaTxt.setEditable(false);
			finalfTxt.setEditable(false);
			finalaTxt.setEditable(false);
		}
	}

	public void setFullScore() throws IOException {
		int a = course.getAssFull();
		int m = course.getMidFull();
		int f = course.getFinalFull();
		try
		{
			a = Integer.valueOf(assfTxt.getText());
			m = Integer.valueOf(midfTxt.getText());
			f = Integer.valueOf(finalfTxt.getText());
			if(a<0 || m<0 || f<0)
			{
				throw new DetailException("The score must not be negative.");
			}
		}
		catch (NumberFormatException | DetailException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE,icon1);
			a = course.getAssFull();
			m = course.getMidFull();
			f = course.getFinalFull();		
			assfTxt.setText(a+"");
			midfTxt.setText(m+"");
			finalfTxt.setText(f+"");
		}
		course.setAssFull(a);
		course.setMidFull(m);
		course.setFinalFull(f);
		courseList.updateFile(course);	
	
	}
	public void setAccumulatedScore() throws IOException {
		
		int a = course.getAssAcc();
		int m = course.getMidAcc();
		int f = course.getFinalAcc();
		try
		{
			a = Integer.valueOf(assaTxt.getText());
			m = Integer.valueOf(midaTxt.getText());
			f = Integer.valueOf(finalaTxt.getText());	
			if(a<0 || m<0 || f<0)
			{
				throw new DetailException("The ratio would not be negative");
			}
		}
		catch (NumberFormatException | DetailException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE,icon1);
			a = course.getAssAcc();
			m = course.getMidAcc();
			f = course.getFinalAcc();
			assaTxt.setText(a+"");
			midaTxt.setText(m+"");
			finalaTxt.setText(f+"");
		}
		if (a + m + f != 100) 
		{
			JOptionPane.showMessageDialog(null, "Accumulated score exceeds 100 points.","Message",JOptionPane.INFORMATION_MESSAGE,icon1);
			assaTxt.setText(course.getAssAcc()+"");
			midaTxt.setText(course.getMidAcc()+"");
			finalaTxt.setText(course.getFinalAcc()+"");
		}
		else
		{	
			course.setAssAcc(a);
			course.setMidAcc(m);
			course.setFinalAcc(f);
			courseList.updateFile(course);	
		}
	
	}
	

	

}
