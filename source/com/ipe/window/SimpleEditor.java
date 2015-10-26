package com.ipe.window;

import com.ipe.Config;
import com.ipe.impl.Zygote;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SimpleEditor extends JFrame {

    private Action openAction = new OpenAction();

    private Action saveAction = new SaveActions();
    private Action aboutAction = new AboutAction();
    private Action run = new Run();
    private JTextComponent textComp;


    public static void main(String[] args) {
        SimpleEditor editor = new SimpleEditor();
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.setVisible(true);

    }

    // Create an editor.
    public SimpleEditor() {
        super("IPE ");
        textComp = createTextComponent();
        makeActionsPretty();



        Container content = getContentPane();
        content.add(textComp, BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
        setSize(500, 640);

    }

    // Create the JTextComponent subclass.
    protected JTextComponent createTextComponent() {
        JTextArea ta = new JTextArea();
        ta.setLineWrap(true);
        return ta;
    }

    // Add icons and friendly names to actions we care about.
    protected void makeActionsPretty() {
        Action a;
        a = textComp.getActionMap().get(DefaultEditorKit.cutAction);
        a.putValue(Action.NAME, "Cut");

        a = textComp.getActionMap().get(DefaultEditorKit.copyAction);
        a.putValue(Action.NAME, "Copy");

        a = textComp.getActionMap().get(DefaultEditorKit.pasteAction);
        a.putValue(Action.NAME, "Paste");

        a = textComp.getActionMap().get(DefaultEditorKit.selectAllAction);
        a.putValue(Action.NAME, "Select All");
    }

    // Create a simple JToolBar with some buttons.


    // Create a JMenuBar with file & edit menus.
    protected JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        menubar.add(file);
        menubar.add(edit);

        file.add(getRun());
        file.add(getOpenAction());
        file.add(getSaveAction());
        file.add(aboutAction);

        file.add(new ExitAction());
        edit.add(textComp.getActionMap().get(DefaultEditorKit.cutAction));
        edit.add(textComp.getActionMap().get(DefaultEditorKit.copyAction));
        edit.add(textComp.getActionMap().get(DefaultEditorKit.pasteAction));
        edit.add(textComp.getActionMap().get(DefaultEditorKit.selectAllAction));
        return menubar;
    }

    // Subclass can override to use a different open action.
    protected Action getOpenAction() {
        return openAction;
    }

    // Subclass can override to use a different save action.
    protected Action getSaveAction() {
        return saveAction;
    }
    protected Action getRun(){
        return run;
    }


    // ********** ACTION INNER CLASSES ********** //

    // A very simple exit action
    public class ExitAction extends AbstractAction {
        public ExitAction() {
            super("Exit");
        }

        public void actionPerformed(ActionEvent ev) {
            System.exit(0);
        }
    }

    // An action that opens an existing file
    class OpenAction extends AbstractAction {
        public OpenAction() {
            super("Open", new ImageIcon("icons/open.gif"));
        }

        // Query user for a filename and attempt to open and read the file into
        // the
        // text component.
        public void actionPerformed(ActionEvent ev) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(SimpleEditor.this) != JFileChooser.APPROVE_OPTION)
                return;
            File file = chooser.getSelectedFile();
            if (file == null)
                return;

            FileReader reader = null;
            try {
                reader = new FileReader(file);
                textComp.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SimpleEditor.this,
                        "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException x) {
                        x.printStackTrace();
                    }
                }
            }
        }
    }

    class Run extends AbstractAction {
        public String content;
        public Run() {
            super("Run");


        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            content = textComp.getText();

            File file = new File("cache.ipe");

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                write(content,"cache.ipe");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Zygote.execute("cache.ipe");

            Result.message = "IPE \n"+Zygote.Result;
            file.delete();
            Result.main(null);





        }
    }
    class SaveActions extends AbstractAction {
        public SaveActions() {
            super("Save", new ImageIcon("icons/save.gif"));
        }

        // Query user for a filename and attempt to open and write the text
        // component's content to the file.
        public void actionPerformed(ActionEvent ev) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(SimpleEditor.this) != JFileChooser.APPROVE_OPTION)
                return;
            File file = chooser.getSelectedFile();
            if (file == null)
                return;

            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                textComp.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SimpleEditor.this,
                        "File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException x) {
                        x.printStackTrace();
                    }
                }
            }
        }
    }

    private void write(String contentX,String path) throws IOException {


        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contentX);
        bufferedWriter.close();
    }


        public class AboutAction extends AbstractAction {
            public AboutAction() {
                super("About");
            }

            public void actionPerformed(ActionEvent ev) {
                JOptionPane.showMessageDialog(textComp,"IPE "+ Config.VERSION+"\n By AndroidFire","About",JOptionPane.INFORMATION_MESSAGE);
            }
        }

}
           