/*
Assignment: CS1410 - Project
Program: TextEditor
Programmer: Thackery Archuletta
Created: Jul 17, 2018
*/

/**
 * File: TextEditor.java
 */
package gui;

import icon.IconButton;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class: TextEditor
 * 
 *
 */
public class TextEditorAllinOne extends JFrame {

	JTextArea textArea;
	File textFileName = null;

	public TextEditorAllinOne() {

		setVisible(true);
		setTitle("TextEditor");
		setSize(550, 400);
		getContentPane().setBackground(new Color(112, 128, 144));
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		// Sets frame to middle of screen no matter what monitor it's on.
		setLocation(width / 2 - getSize().width / 2, height / 2 - getSize().height / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Uniform look and feel across all devices
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.addActionListener(newActionListener());
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(openActionListener());
		mnNewMenu.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(saveActionListener());
		mnNewMenu.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(saveAsActionListener());
		mnNewMenu.add(mntmSaveAs);

		textArea = new JTextArea();
		textArea.setOpaque(true);
		getContentPane().add(textArea, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar();
		
		JButton saveButton = new IconButton(this.getClass().getResource("images/saveIcon.png"), 15, 15).getIconButton();
		saveButton.addActionListener(saveActionListener());
		
		JButton saveAsButton = new IconButton(this.getClass().getResource("images/saveAsIcon.png"), 15, 15).getIconButton();
		saveAsButton.addActionListener(saveAsActionListener());
		
		JButton openButton = new IconButton(this.getClass().getResource("images/openIcon.png"), 15, 15).getIconButton();
		openButton.addActionListener(openActionListener());
		
		JButton newButton = new IconButton(this.getClass().getResource("images/newIcon.png"), 15, 15).getIconButton();
		newButton.addActionListener(newActionListener());
		
		toolBar.add(saveButton);
		toolBar.add(saveAsButton);
		toolBar.add(openButton);
		toolBar.add(newButton);
		getContentPane().add(toolBar, BorderLayout.NORTH);

	}

	private ActionListener saveActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();
			}
		};
	}
	
	private ActionListener saveAsActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveAs();
			}
		};
	}
	
	private ActionListener openActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Open();
			}
		};
	}
	
	private ActionListener newActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New();
			}
		};
	}
	
	public void Save() {
		if (textFileName != null && textFileName.exists()) {
			BufferedWriter outputFile = null;
			try {
				outputFile = new BufferedWriter(new FileWriter(textFileName));
				
				textArea.write(outputFile);
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputFile != null) {
					try {
						outputFile.close();
					} catch (IOException ex) {
					}
				}
			}	
		}
		else {
			SaveAs();
		}
	}

	public void SaveAs() {

		JFileChooser SaveAsWindow = new JFileChooser();
		FileFilter textFileFilter = new FileNameExtensionFilter("Text File", "txt");
		SaveAsWindow.setFileFilter(textFileFilter);
		SaveAsWindow.setApproveButtonText("Save");
		int statusOutput = SaveAsWindow.showOpenDialog(this);
		if (statusOutput != JFileChooser.APPROVE_OPTION) {
			return;
		}
		textFileName = new File(SaveAsWindow.getSelectedFile() + "");
		if (!textFileName.getAbsolutePath().endsWith(".txt")) {
			textFileName = new File(textFileName.getAbsolutePath() + ".txt");
		}
		int response = -1;
		JOptionPane alert = new JOptionPane();
		if (textFileName.exists()) {
			response = alert.showConfirmDialog(this, "The file already exists. Would you like to overwrite it?", "File Already Exists", JOptionPane.YES_NO_OPTION);
		}
		if (response != alert.NO_OPTION) {
			BufferedWriter outputFile = null;
			try {
				outputFile = new BufferedWriter(new FileWriter(textFileName));
				
				textArea.write(outputFile);
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputFile != null) {
					try {
						outputFile.close();
					} catch (IOException ex) {
					}
				}
			}	
		}
	}

	public void Open() {

		JFileChooser OpenWindow = new JFileChooser();
		FileFilter textFileFilter = new FileNameExtensionFilter("Text File", "txt");
		OpenWindow.setFileFilter(textFileFilter);

		OpenWindow.setApproveButtonText("Open");
		int statusOutput = OpenWindow.showOpenDialog(this);
		if (statusOutput != JFileChooser.APPROVE_OPTION) {
			return;
		}
		textFileName = new File(OpenWindow.getSelectedFile() + "");
		Writer inputFile = null;
		BufferedReader input = null;
		try {
			FileReader reader = new FileReader(textFileName);
			BufferedReader buffReader = new BufferedReader(reader);
			textArea.read(buffReader, null);
			buffReader.close();

		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public void New() {
		textFileName = null;
		textArea.setText("");
	}

	public static void main(String[] args) {
		TextEditorAllinOne gui = new TextEditorAllinOne();
	}

}
