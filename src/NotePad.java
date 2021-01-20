import javax.swing.*;//JFrame
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//Action listener
import java.io.*;

public class NotePad extends JFrame implements ActionListener {
    //main method
    public static void main(String[] args) {
        new NotePad();
    }
    //our text area
    JTextArea text = new JTextArea();
    //Horizontal and vertical scrollbar
    JScrollPane scrollable = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //this is for menu bar creation
    JMenuBar bar = new JMenuBar();
    //this is where you can find text operations for your notes
    JMenu File = new JMenu("File");
    //these are operations in File object
    JMenuItem New = new JMenuItem("New"); //new text area
    JMenuItem Open = new JMenuItem("Open"); //open existing one
    JMenuItem SaveAs = new JMenuItem("Save As...");//saveas
    JMenuItem Save = new JMenuItem("Save"); //saving
    JMenuItem Exit = new JMenuItem("Exit"); //terminate the program

    String filePath;

    //constructor
    public NotePad(){
        //set title
        super("My NotePad Project");
        setVisible(true);
        //set size of a window
        setSize(500,500);
        // close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        //getContentPane().add(this.text);

        this.add(scrollable);
        // font, its size and its features
        this.text.setFont(new Font("Comic Sans MS",Font.PLAIN,15));

        //adds bar to our notepad window
        setJMenuBar(this.bar);
        //adds File section to bar
        this.bar.add(this.File);

        this.File.add(this.New);
        this.New.addActionListener(this::actionPerformed);

        this.File.add(this.Open);//add open option to the file section
        this.Open.addActionListener(this::actionPerformed);//add action listener for click mechanism

        this.File.add(this.SaveAs);
        this.SaveAs.addActionListener(this::actionPerformed);

        this.File.add(this.Save);
        this.Save.addActionListener(this::actionPerformed);

        this.File.add(this.Exit);
        this.Exit.addActionListener(this::actionPerformed);
    }
    //Methods
    public void NewFile(){
        text.setText("");
        filePath=null;
    }
    public void OpenFile(){
        JFileChooser openFile = new JFileChooser();
        //user clicked the open
        if(openFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){

            //address of the file
            filePath =openFile.getSelectedFile().getPath();

            try{
                //reads text from file
                //and we need to know content and address of the file
                BufferedReader  br = new BufferedReader(new FileReader(filePath));
                //string variables for lines
                String lines ;
                String lines2=br.readLine();
                //read lines from the file
                while((lines=br.readLine()) !=null){
                    //update lines2 add lines
                    lines2= lines2+"\n"+lines;
                }
                //set the text lines2
                this.text.setText(lines2);
                setTitle(filePath);
                br.close();

            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public void SaveAs(){
        // A file chooser opens for you to save notepad file
        JFileChooser saveAsFile = new JFileChooser("f:");
        // showSaveDialog function shows us the save dialog in file chooser
        // user clicked save option
        if(saveAsFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            filePath =saveAsFile.getSelectedFile().getPath();
            try{

                // there is a BufferedWriter for write to a file
                // gets the text which is written in text area an write
                BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
                bw.write(text.getText());
                setTitle(filePath);
                //all texts from buffer is written to intended destination,
                //flush the content of the buffer
                bw.flush();
                //close stream
                bw.close();
                JOptionPane.showMessageDialog(null,"Saved!");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    }
    public void Save(){
        //if the file didn't saved before call method saveAs()
        if(filePath==null){
            SaveAs();
        }
        //if it is an existing file and saving it after maked changes
        else{
            try{
                //write to file
                FileWriter fw = new FileWriter(filePath);
                fw.write(text.getText());
                setTitle(filePath);
                fw.close();
                JOptionPane.showMessageDialog(null,"Saved!");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    public void Exit(){
        int confirm=JOptionPane.showConfirmDialog(null,"Do you want to save file?");
        if(confirm==JOptionPane.YES_OPTION)
            SaveAs();
        else if(confirm==JOptionPane.NO_OPTION)
            System.exit(0);
    }

    public void actionPerformed (ActionEvent e){
        if(e.getSource()==this.New){
            NewFile();
        }
        else if(e.getSource()==this.Open){
            OpenFile();
        }
        else if(e.getSource()==this.SaveAs){
            SaveAs();
        }
        else if(e.getSource()==this.Save){
            Save();
        }
        else if(e.getSource()==this.Exit){
            Exit();
        }
    }
}
