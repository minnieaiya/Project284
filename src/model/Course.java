package model;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

public class Course
{
	private String courseID;
	private String courseName;
	private int assFull , assAcc;
	private int midFull , midAcc;
	private int finalFull , finalAcc;

	public Course(String courseID , String courseName, int assFull , int assAcc, int midFull, int midAcc, int finalFull, int finalAcc) 
	{
		this.courseID = courseID;	
		this.courseName = courseName;
		this.assFull = assFull;
		this.assAcc = assAcc;
		this.midFull = midFull;
		this.midAcc = midAcc;
		this.finalFull = finalFull;
		this.finalAcc = finalAcc;
	}
	
	
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) 
	{
		this.courseID = courseID;
	}
	public String getCourseName() 
	{
		return courseName;
	}
	public void setCourseName(String courseName) 
	{
		this.courseName = courseName;
	}
	public String toString() 
	{
		return getCourseID().toUpperCase()+","+getCourseName()+","+getAssFull()+","+getAssAcc()+","+getMidFull()+","+getMidAcc()+","+getFinalFull()+","+getFinalAcc()+"\n";		
	}	
	public int getAssFull() 
	{
		return assFull;
	}
	public void setAssFull(int assFull) 
	{
		this.assFull = assFull;
	}
	public int getAssAcc() 
	{
		return assAcc;
	}
	public void setAssAcc(int assAcc) 
	{
		this.assAcc = assAcc;
	}
	public int getMidFull() {
		return midFull;
	}
	public void setMidFull(int midFull) 
	{
		this.midFull = midFull;
	}
	public int getMidAcc() 
	{
		return midAcc;
	}
	public void setMidAcc(int midAcc) 
	{
		this.midAcc = midAcc;
	}
	public int getFinalFull() 
	{
		return finalFull;
	}
	public void setFinalFull(int finalFull) 
	{
		this.finalFull = finalFull;
	}
	public int getFinalAcc() 
	{
		return finalAcc;
	}
	public void setFinalAcc(int finalAcc) 
	{
		this.finalAcc = finalAcc;
	}
	
	
	
	

}
