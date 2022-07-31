package insuranceprogram;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class insuranceApplication extends JFrame implements ActionListener, KeyListener
{
//--------------------------------Start of declerations-----------------------//
    
    protected JMenuBar menuBar;
    protected JMenu files;
    protected JTextField client, project, task, person, hours, inputDate;
    protected JLabel textClient, textDetailsReport, textProject, textTask, textPerson, textHours,
            textDate;
    protected JMenuItem createFile, openFiles, exit, save;
    protected ImageIcon saveFileIcon, openFileIcon;
    protected DateFormat df;
    protected JFormattedTextField date;
    protected Document pdfDoc = new Document();
    protected String fileName, namingFile;
    protected File openingFile;
    protected PdfPTable timeReportTable, timeReportTable2;
    protected JButton deleteLastRow, saveDate, saveReportDetails;
    protected File pdfFile, foundFile;

//---------------------------------end of declerations------------------------//

    insuranceApplication()
    {
//------------------------------Start of textfields---------------------------//

        //Creation of JMenuBar
        menuBar = new JMenuBar();
        menuBar.setVisible(true);
        menuBar.setBackground(Color.WHITE);

        //Creation of a JMenu that contains "save file,open file,exit"
        files = new JMenu("Files");
        menuBar.add(files);

        //Creation of a label to act as a Title that is "Detailed Time Report"
        textDetailsReport = new JLabel();
        textDetailsReport.setFont(new Font("gotham", Font.BOLD, 20));
        textDetailsReport.setBounds(520, 0, 300, 50);
        textDetailsReport.setText("Detailed Time Report");

        //Creation of a JTextField where the user inputs the Clients name
        client = new JTextField();
        client.setBounds(230, 75, 200, 25);

        //Creation of a JLabel that labels the JTextfield client as "Client name"
        textClient = new JLabel();
        textClient.setFont(new Font("gotham", Font.BOLD, 20));
        textClient.setBounds(10, 60, 200, 50);
        textClient.setText("Client name ");

        //Creation of JTextfield where the user inputs the projects name 
        project = new JTextField();
        project.setBounds(230, 115, 200, 25);

        //Creation of label that labels the project Text field as "Project name"
        textProject = new JLabel();
        textProject.setFont(new Font("gotham", Font.BOLD, 20));
        textProject.setBounds(10, 100, 140, 50);
        textProject.setText("Project name");

        //Creation of a JTextfield that allows the user to enter the task name
        task = new JTextField(20);
        task.setBounds(230, 155, 200, 25);

        //Creation of the label that labels the task textfield as "Task name"
        textTask = new JLabel();
        textTask.setFont(new Font("gotham", Font.BOLD, 20));
        textTask.setBounds(10, 139, 120, 50);
        textTask.setText("Task name");

        //Creation of a textfield where the user inputs the name of the person working on the claim
        person = new JTextField(20);
        person.setBounds(970, 75, 200, 25);

        //Creation of the label that labels the person Textfield as "Person working on the claim"
        textPerson = new JLabel();
        textPerson.setFont(new Font("gotham", Font.BOLD, 20));
        textPerson.setBounds(700, 60, 300, 50);
        textPerson.setText("Person working on claim");

        //Creation of Text field where the user inputs the customers Tariff ID
        hours = new JTextField(20);
        hours.setBounds(970, 115, 200, 25);

        //Craetion of a label that labels the tariffID textfield as "Tariff ID"
        textHours = new JLabel();
        textHours.setFont(new Font("gotham", Font.BOLD, 20));
        textHours.setBounds(700, 100, 300, 50);
        textHours.setText("Hours spent on claim");

        //inputDate = new JTextfield();
        String strDate = "dd/MM/yyyy";
        df = new SimpleDateFormat("dd/MM/yyyy");
        date = new JFormattedTextField(df);
        date.setText(strDate);
        date.setBounds(970, 155, 200, 25);

        //Creation of a label to label the textfield date as "Date of claim"
        textDate = new JLabel();
        textDate.setFont(new Font("gotham", Font.BOLD, 20));
        textDate.setBounds(700, 143, 300, 50);
        textDate.setText("Date of claim");

 //-----------------End of Textfields-----------------------------------------//
        
 //-----------------Start of menu items---------------------------------------//
        
        //Creation of the createFile JMenuItem that allows the user to create a file
        saveFileIcon = new ImageIcon("new-document.png");
        createFile = new JMenuItem("Create New File");
        files.add(createFile);
        createFile.addActionListener(this);
        createFile.setIcon(saveFileIcon);


        //Creation of exit JMenutItem which allows the user to exit the program
        exit = new JMenuItem("Exit");
        files.add(exit);
        exit.addActionListener(this);

 //-------------------------End of JMenutItems--------------------------------//
        
 //-------------------------Start of adding buttons---------------------------//
        deleteLastRow = new JButton("Delete Last Row");
        deleteLastRow.setBounds(200, 400, 200, 100);
        deleteLastRow.addActionListener(this);
        deleteLastRow.setVisible(true);

        saveDate = new JButton("Save Date");
        saveDate.setBounds(500, 400, 200, 100);
        saveDate.addActionListener(this);
        saveDate.setVisible(true);

        saveReportDetails = new JButton("Save Report Details");
        saveReportDetails.setBounds(800, 400, 200, 100);
        saveReportDetails.addActionListener(this);
        saveReportDetails.setVisible(true);

 //------------------------End of adding buttons------------------------------//
 
 //-----------------------start of adding to JFRAME---------------------------//
 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setJMenuBar(menuBar);
        this.setBackground(Color.WHITE);
        this.setTitle("Insurance application");
        this.add(client);
        this.add(textClient);
        this.add(textDetailsReport);
        this.add(textProject);
        this.add(project);
        this.add(task);
        this.add(textTask);
        this.add(person);
        this.add(textPerson);
        this.add(hours);
        this.add(textHours);
        this.add(date);
        this.add(textDate);
        this.add(deleteLastRow);
        this.add(saveDate);
        this.add(saveReportDetails);
    }
//----------------------------end of adding to JFRAME-------------------------//    

//-------------------------------Start of action performed--------------------//
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //If statement so that when the user presses enter it exits them from the program
        if (e.getSource() == exit)
        {
            System.exit(0);
        }

        //If statement for when the user clicks the save file menu it saves the file they are working on
        if (e.getSource() == createFile)
        {
            creatingFile();
        }

        //If statement for when the save date button is pressed it will save the date
        if (e.getSource() == saveDate)
        {
            creatingDateRow();
        }

        //If statement for if the save report details button is pressed it will save all of the deatails to the pdf
        if (e.getSource() == saveReportDetails)
        {
            creatingReportDetailsRow();
        }

        //If statement for when the delete last row button is pressed it will delete the last row
        if (e.getSource() == deleteLastRow)
        {
            timeReportTable.deleteRow(timeReportTable.size() - 1);
        }
    }
//-------------------------------end of action performed----------------------//

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

//------------------------------Start of method-------------------------------//
    //Method for creation of the file
    public void creatingFile()
    {

        namingFile = JOptionPane.showInputDialog("What name do you want to save the file as ?");
        fileName = "C:\\Users\\evilp\\OneDrive\\Documents\\" + namingFile + ".pdf";
        try
        {

            File pdfFile = new File(fileName);

            pdfDoc = new Document();

            PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFile));

            pdfDoc.open();

//           //Creation of heading for Document
//            pdfDoc.add(new Paragraph("             Detailed Time Report                   Business Name",
//                    FontFactory.getFont(FontFactory.HELVETICA,15,Element.ALIGN_CENTER)));
//            
//            pdfDoc.add(new Paragraph("        "));
//           
            //Creation of table 
            timeReportTable = new PdfPTable(5);
            timeReportTable.getDefaultCell().setBorder(0);

            //Creating of the Heading Cells
            PdfPCell cell = new PdfPCell(new Phrase("Client"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderColor(BaseColor.GRAY);
            timeReportTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Project"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderColor(BaseColor.GRAY);
            timeReportTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Task"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderColor(BaseColor.GRAY);
            timeReportTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Person"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderColor(BaseColor.GRAY);
            timeReportTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Hours"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderColor(BaseColor.GRAY);
            timeReportTable.addCell(cell);

            //Setting the first row of cells as the header for the table
            timeReportTable.setHeaderRows(1);

            //Creation of a cell that contains the date
            PdfPCell dateCell = new PdfPCell(new Phrase(date.getText()));
            dateCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            dateCell.setBorder(0);

            //Creation of an empty cell for when there is no data
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            emptyCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            emptyCell.setBorder(0);

            //Creation of clientcell which contains the clients name
            PdfPCell clientCell = new PdfPCell(new Phrase(client.getText()));
            clientCell.setBorder(0);

            //Creation of projectCell which containts the project name
            PdfPCell projectCell = new PdfPCell(new Phrase(project.getText()));
            projectCell.setBorder(0);

            //Creation of taskCell which contains the task name
            PdfPCell taskCell = new PdfPCell(new Phrase(task.getText()));
            taskCell.setBorder(0);

            //Creation of personCell which contains the persons name
            PdfPCell personCell = new PdfPCell(new Phrase(person.getText()));
            personCell.setBorder(0);

            //Creation of hoursCell which contains the hours spent on the claim
            PdfPCell hoursCell = new PdfPCell(new Phrase(hours.getText()));
            hoursCell.setBorder(0);

            //Adding of the first row which is the date
            if (date.getText().isEmpty())
            {
                timeReportTable.addCell(clientCell);
                timeReportTable.addCell(projectCell);
                timeReportTable.addCell(taskCell);
                timeReportTable.addCell(personCell);
                timeReportTable.addCell(hoursCell);
                client.setText(null);
                project.setText(null);
                task.setText(null);
                person.setText(null);
                hours.setText(null);

            } else
            {
                timeReportTable.addCell(dateCell);
                timeReportTable.addCell(emptyCell);
                timeReportTable.addCell(emptyCell);
                timeReportTable.addCell(emptyCell);
                timeReportTable.addCell(emptyCell);

            }

            pdfDoc.add(timeReportTable);

            pdfDoc.close();

            JOptionPane.showMessageDialog(null, "File Saved");
        } catch (Exception a)
        {
            System.err.print(a);
            JOptionPane.showMessageDialog(null, "Error Saving File, this could be due to incorrect hours inputed");
        }
    }

//---------------------------------End of method------------------------------//
    
//-------------------------------Start of method------------------------------//
    
    //Creation of method that will add the date row when the add date button is pressed
    public void creatingDateRow()
    {
        try
        {

            Document pdfDoc3 = new Document();

            File files = new File(fileName);

            PdfWriter.getInstance(pdfDoc3, new FileOutputStream(files));

            pdfDoc3.open();

            //Creation of an empty cell for when there is no data
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            emptyCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            emptyCell.setBorder(0);

            //Creation of a cell that contains the date
            PdfPCell dateCell = new PdfPCell(new Phrase(date.getText()));
            dateCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            dateCell.setBorder(0);

            //Adding of rows to table on PDF
            timeReportTable.addCell(dateCell);
            timeReportTable.addCell(emptyCell);
            timeReportTable.addCell(emptyCell);
            timeReportTable.addCell(emptyCell);
            timeReportTable.addCell(emptyCell);
            date.setText("dd/MM/yyyy");

            pdfDoc3.add(timeReportTable);

            pdfDoc3.close();

            JOptionPane.showMessageDialog(null, "File successfuly updated");

        } catch (Exception b)
        {
            System.err.print(b);
            JOptionPane.showMessageDialog(null, "Could not update file");
        }
    }
//---------------------------End of method------------------------------------//

//--------------------------Start of method-----------------------------------//
    
    //Creation of method that will add the date row when the add date button is pressed
    public void creatingReportDetailsRow()
    {
        try
        {

            Document pdfDoc4 = new Document();

            PdfWriter.getInstance(pdfDoc4, new FileOutputStream(fileName));

            pdfDoc4.open();

            //Creation of clientcell which contains the clients name
            PdfPCell clientCell = new PdfPCell(new Phrase(client.getText()));
            clientCell.setBorder(0);

            //Creation of projectCell which containts the project name
            PdfPCell projectCell = new PdfPCell(new Phrase(project.getText()));
            projectCell.setBorder(0);

            //Creation of taskCell which contains the task name
            PdfPCell taskCell = new PdfPCell(new Phrase(task.getText()));
            taskCell.setBorder(0);

            //Creation of personCell which contains the persons name
            PdfPCell personCell = new PdfPCell(new Phrase(person.getText()));
            personCell.setBorder(0);

            //Creation of hoursCell which contains the hours spent on the claim
            PdfPCell hoursCell = new PdfPCell(new Phrase(hours.getText()));
            hoursCell.setBorder(0);

            //Creation of pattern finders to restrict the hours text box to only integers
            String upperCaseCharacterChecker = "[A-Z]";
            Pattern pt = Pattern.compile(upperCaseCharacterChecker);
            Matcher mt = pt.matcher(hours.getText());
            boolean result = mt.find();

            String lowerCaseCharacterChecker = "[a-z]";
            Pattern pt2 = Pattern.compile(lowerCaseCharacterChecker);
            Matcher mt2 = pt.matcher(hours.getText());
            boolean result2 = mt.find();

            //Adding of cells to table on PDF
            timeReportTable.addCell(clientCell);
            timeReportTable.addCell(projectCell);
            timeReportTable.addCell(taskCell);
            timeReportTable.addCell(personCell);
            timeReportTable.addCell(hoursCell);
            client.setText(null);
            project.setText(null);
            task.setText(null);
            person.setText(null);
            hours.setText(null);

            pdfDoc4.add(timeReportTable);

            pdfDoc4.close();

            JOptionPane.showMessageDialog(null, "File successfuly updated");

        } catch (Exception b)
        {
            System.err.print(b);
            JOptionPane.showMessageDialog(null, "Could not update file");
        }
    }
//--------------------------End of method-------------------------------------//

}
