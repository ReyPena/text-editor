/*
Assignment: CS1410 - Project
Program: FileHandlingHelpers
Programmer:
Created: Jul 25, 2018
*/

/**
 * File: FileHandlingHelpers.java
 */
package gui;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
* Class: FileHandlingHelpers
 * @author Thackery Archuletta
 *
 */
public class FileHandlingHelpers {
	
	private static Component gui;
	
	/**
	 * Constructor FileHandlingHelpers
	 */
	public FileHandlingHelpers(Component gui) {
		this.gui = gui;
	}

	// Thack
	public static File Save(File textFileName, JTextArea textArea) {
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
			textFileName = SaveAs(textFileName, textArea);
		}
		return textFileName;
	}

	public static File SaveAs(File textFileName, JTextArea textArea) {

		JFileChooser SaveAsWindow = new JFileChooser();
		FileFilter textFileFilter = new FileNameExtensionFilter("Text File", "txt");
		SaveAsWindow.setFileFilter(textFileFilter);
		SaveAsWindow.setApproveButtonText("Save");
		int statusOutput = SaveAsWindow.showOpenDialog(gui);
		if (statusOutput != JFileChooser.APPROVE_OPTION) {
			return textFileName;
		}
		textFileName = new File(SaveAsWindow.getSelectedFile() + "");
		if (!textFileName.getAbsolutePath().endsWith(".txt")) {
			textFileName = new File(textFileName.getAbsolutePath() + ".txt");
		}
		int response = -1;
		JOptionPane alert = new JOptionPane();
		if (textFileName.exists()) {
			response = alert.showConfirmDialog(gui, "The file already exists. Would you like to overwrite it?", "File Already Exists", JOptionPane.YES_NO_OPTION);
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
		return textFileName;
	}

	public static File Open(File textFileName, JTextArea textArea) {

		JFileChooser OpenWindow = new JFileChooser();
		FileFilter textFileFilter = new FileNameExtensionFilter("Text File", "txt");
		OpenWindow.setFileFilter(textFileFilter);

		OpenWindow.setApproveButtonText("Open");
		int statusOutput = OpenWindow.showOpenDialog(gui);
		if (statusOutput != JFileChooser.APPROVE_OPTION) {
			return textFileName;
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
		return textFileName;
	}
	
	public static File New(File textFileName, JTextArea textArea) {
		textFileName = null;
		textArea.setText("");
		return textFileName;
	}

}
