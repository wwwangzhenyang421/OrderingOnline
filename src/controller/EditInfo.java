package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditInfo extends JFrame implements ActionListener {
    private String id, FormerWindowID, Formername;
    private JPanel contain;
    private JButton submit;
    private JLabel dynamicLabel1, dynamicLabel2;
    private JTextField namet, pass1t, pass2t, inputField1, inputField2;

	// Modify the info of an existed student, teacher or seller
    public EditInfo(String id) {
        super("Modify information");
        setSize(300, 360);
        setLocation(600, 400);
        this.id = id;
        // seller
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/seller.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(id)) {
					String[] parts = line.split(" ");
					FormerWindowID = parts[3];
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// 处理读取文件时可能出现的异常
		}

        // student
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/student.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(id)) {
					String[] parts = line.split(" ");
					Formername = parts[1];
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// 处理读取文件时可能出现的异常
		}

        // teacher
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/teacher.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(id)) {
					String[] parts = line.split(" ");
					Formername = parts[1];
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// 处理读取文件时可能出现的异常
		}

        contain = new JPanel();
        contain.setLayout(null);

        submit = new JButton("submit");
        namet = new JTextField();
        inputField1 = new JTextField();
        inputField2 = new JTextField();
        pass1t = new JTextField();
        pass2t = new JTextField();

        JLabel name = new JLabel("name");
        JLabel pass1 = new JLabel("new PW");
        JLabel pass2 = new JLabel("confirm pW");
        dynamicLabel2 = new JLabel("phone");

        name.setBounds(25, 20, 75, 35);
        namet.setBounds(80, 20, 180, 35);
        inputField1.setBounds(80, 60, 180, 35);

        dynamicLabel1 = new JLabel(findDynamicLabelValue(id)); // Update dynamic tags directly based on ID
		if("WindowID".equals(findDynamicLabelValue(id))){
			dynamicLabel1.setBounds(15, 60, 75, 35);
            pass1.setBounds(25, 100, 75, 35);
            pass1t.setBounds(100, 100, 160, 35);
            pass2.setBounds(20, 140, 75, 35);
            pass2t.setBounds(100, 140, 160, 35);
            submit.setBounds(100, 200, 75, 30);
		}
		else{
			dynamicLabel1.setBounds(25, 60, 75, 35);
            dynamicLabel2.setBounds(25, 100, 75, 35);
            inputField2.setBounds(80, 100, 180, 35);
            pass1.setBounds(25, 140, 75, 35);
            pass1t.setBounds(100, 140, 160, 35);
            pass2.setBounds(20, 180, 75, 35);
            pass2t.setBounds(100, 180, 160, 35);
            submit.setBounds(100, 240, 75, 30);
		}
        
        contain.add(name);
        contain.add(namet);
        contain.add(dynamicLabel1);
        contain.add(inputField1);
        contain.add(dynamicLabel2);
        contain.add(inputField2);
        contain.add(pass1);
        contain.add(pass1t);
        contain.add(pass2);
        contain.add(pass2t);
        contain.add(submit);

        submit.addActionListener(this);
        add(contain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (inputField1.getText().isEmpty() || namet.getText().isEmpty() || pass1t.getText().isEmpty() || pass2t.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The information cannot be empty!", "prompt", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!pass1t.getText().equals(pass2t.getText())) {
                    JOptionPane.showMessageDialog(null, "The new password is not the same as the confirmation password", "prompt", JOptionPane.INFORMATION_MESSAGE);
                } else if (pass1t.getText().length() < 6) {
                    JOptionPane.showMessageDialog(null, "The password must be at least 6 characters long", "prompt", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String dynamicLabel1Value = findDynamicLabelValue(id);
                    if (dynamicLabel1Value != "default") {
                        dynamicLabel1.setText(dynamicLabel1Value);
                        if (dynamicLabel1.getText().equals("WindowID")){
                            updateTxtFileContentForAD(id, namet.getText(), inputField1.getText(), pass1t.getText());
                            JOptionPane.showMessageDialog(null, "File updated successfully", "prompt", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            updateTxtFileContent(id, namet.getText(), inputField1.getText(), inputField2.getText(), pass1t.getText());
                            JOptionPane.showMessageDialog(null, "File updated successfully", "prompt", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ID not found in any file", "prompt", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    private String findFileForId(String[] fileNames, String id) {
        for (String fileName : fileNames) {
            String filePath1 = System.getProperty("user.dir") + "/data/" + fileName; // Replace with the actual folder path
            try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(id)) {
                        return fileName;
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private String getLabelForFile(String fileName) {
        if (fileName.equals("student.txt")) {
            return "address";
        } else if (fileName.equals("teacher.txt")) {
            return "address";
        } else if (fileName.equals("seller.txt")) {
            return "WindowID";
        }
        return "default"; // If no matching file name is found, the default value is returned
    }

	private void updateTxtFileContentForAD(String id, String newName, String newInfo1, String newPassword) {
        String[] fileNames = {"student.txt", "teacher.txt", "seller.txt"};
        for (String fileName : fileNames) {
            String filePath1 = System.getProperty("user.dir") + "/data/" + fileName;
            String filePath2 = System.getProperty("user.dir") + "/data/orders.txt";
            String filePath3 = System.getProperty("user.dir") + "/data/dishes.txt";
            try {
                BufferedReader file = new BufferedReader(new FileReader(filePath1));
                String line;
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(id)) {
                        inputBuffer.append(id).append(" ").append(newName).append(" ").append(newPassword).append(" ").append(newInfo1).append("\n");
                    } else {
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath1);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                BufferedReader file = new BufferedReader(new FileReader(filePath2));
                String line;
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[3].equals(FormerWindowID)) {
                        parts[3] = newInfo1;
                        inputBuffer.append(String.join(" ", parts)).append("\n"); // Add the modified content to the inputBuffer
                    } else {
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath2);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                BufferedReader file = new BufferedReader(new FileReader(filePath3));
                String line;
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(FormerWindowID)) {
                        parts[0] = newInfo1;
                        inputBuffer.append(String.join(" ", parts)).append("\n"); // 将修改后的内容添加到 inputBuffer 中
                    } else {
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath3);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

	private void updateTxtFileContent(String id, String newName, String newInfo1, String newInfo2, String newPassword) {
        String[] fileNames = {"student.txt", "teacher.txt", "seller.txt"};
        for (String fileName : fileNames) {
            String filePath1 = System.getProperty("user.dir") + "/data/" + fileName;
            String filePath2 = System.getProperty("user.dir") + "/data/orders.txt";
            try {
                BufferedReader file = new BufferedReader(new FileReader(filePath1));
                String line;
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(id)) {
                        inputBuffer.append(id).append(" ").append(newName).append(" ").append(newPassword).append(" ").append(newInfo1).append(" ").append(newInfo2).append("\n");
                    } else {
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath1);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                BufferedReader file = new BufferedReader(new FileReader(filePath2));
                String line;
                StringBuilder inputBuffer = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(Formername)) {
                        parts[0] = newName;
                        parts[1] = newInfo1;
                        parts[2] = newInfo2;
                        inputBuffer.append(String.join(" ", parts)).append("\n"); // Add the modified content to the inputBuffer
                    } else {
                        inputBuffer.append(line).append("\n");
                    }
                }
                file.close();
                FileWriter fileOut = new FileWriter(filePath2);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private String findDynamicLabelValue(String id) {
        String[] fileNames = {"student.txt", "teacher.txt", "seller.txt"};
        String foundInFile = findFileForId(fileNames, id);
        if (foundInFile != null) {
            return getLabelForFile(foundInFile);
        } else {
            return "default";
        }
    }

    public static void main(String[] args) {
        // Create an EditInfo object and display the GUI interface
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditInfo("S02");
            }
        });
    }
}
