import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class personalJournal implements ActionListener {
    JMenuItem newDay, newEntry, remove;

    JCheckBoxMenuItem editName, editAdd;
    JTabbedPane days;
    JTextField nametf, addresstf;
    JButton save;
    DateFormat entryFormat = new SimpleDateFormat("EEEE, MM/dd HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("EEEE MM/dd");
    Calendar cal = Calendar.getInstance();
    personalJournal(){
        JFrame jfrm = new JFrame("Journal");
        jfrm.setSize(600, 800);
        jfrm.setLayout(new BorderLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        JMenuBar jmb = new JMenuBar();
        days = new JTabbedPane();
        JMenu entries = new JMenu("Entries");
        JMenu myInfo = new JMenu("My Info");
        //entries menu items
        newDay = new JMenuItem("New Day");
        newEntry = new JMenuItem("New Entry");
        JMenuItem separator = new JMenuItem("");
        remove = new JMenuItem("Remove Day");
        //myinfo menu items
        editName = new JCheckBoxMenuItem("Edit Name");
        editAdd = new JCheckBoxMenuItem("Edit Address");

        //add menu items to entries
        entries.add(newDay);
        entries.add(newEntry);
        entries.add(separator);
        entries.add(remove);

        //add menu items to myinfo
        myInfo.add(editName);
        myInfo.add(editAdd);

        //adding action listeners
        newDay.addActionListener(this);
        newEntry.addActionListener(this);
        remove.addActionListener(this);
        editName.addActionListener(this);
        editAdd.addActionListener(this);


        //add menus to menu bar
        jmb.add(entries);
        jmb.add(myInfo);

        //user info tab
        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
        userInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel name = new JLabel("User Name:");
        JLabel address = new JLabel("Address:");
        JLabel notes = new JLabel("Additional Notes:");

        nametf = new JTextField("Maddie Isaacs");
        nametf.setMaximumSize(new Dimension(200, 25));
        nametf.setEditable(false);

        addresstf = new JTextField("124 Conch St, Bikini Bottom");
        addresstf.setMaximumSize(new Dimension(200, 25));
        addresstf.setEditable(false);

        JTextPane miscNotes = new JTextPane();
        miscNotes.setContentType("text/html");
        String maddiefacts = "<html>favorite food: pasta and chinese food<br>" +
                            "top groups: stray kids & nct dream<br>" +
                            "current obession: enhypen<br>" +
                            "hobbies: making bevie bevs (barista), listening to music, & reading<br>"+
                            "favorite Fruit: strawberries<br>"+
                            "pondering: Being a housewife to a rich engineer<br>"+
                            "(idk if i can woman in stem anymore)<br>"
                            + "word ~_~ ♡";
        miscNotes.setText(maddiefacts);
        miscNotes.setMaximumSize(miscNotes.getPreferredSize());
        miscNotes.setEditable(false);

        save = new JButton("Save");
        save.setVisible(false);
        save.addActionListener(this);

        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        nametf.setAlignmentX(Component.LEFT_ALIGNMENT);
        address.setAlignmentX(Component.LEFT_ALIGNMENT);
        addresstf.setAlignmentX(Component.LEFT_ALIGNMENT);
        notes.setAlignmentX(Component.LEFT_ALIGNMENT);
        miscNotes.setAlignmentX(Component.LEFT_ALIGNMENT);
        save.setAlignmentX(Component.LEFT_ALIGNMENT);

        userInfo.add(name);
        userInfo.add(nametf);
        userInfo.add(address);
        userInfo.add(addresstf);
        userInfo.add(notes);
        userInfo.add(miscNotes);
        userInfo.add(save);
        days.addTab("My Info", userInfo);

        //initial entry tab
        JPanel defaultPanel = new JPanel();
        defaultPanel.setLayout(new BoxLayout(defaultPanel,BoxLayout.Y_AXIS));

        //DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        Date date = new Date();
        //cal.setTime(new Date());
        //DateFormat formatter = new SimpleDateFormat("EEEE");
        //String dayOfWeekString = formatter.format(cal.getTime());

        days.add(dateFormat.format(date), defaultPanel);
        days.add("My Info", userInfo);
        jfrm.add(jmb, BorderLayout.PAGE_START);
        jfrm.add(days, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(newDay)){
            days.insertTab(dateFormat.format(cal.getTime()),null, new JPanel(),null, 0);
        }

        if(e.getSource().equals(newEntry)){
            JPanel currTab = (JPanel) days.getSelectedComponent();
            currTab.setLayout(new BoxLayout(currTab,BoxLayout.Y_AXIS));

            JTextArea ta = new JTextArea(5, 50);
            ta.setLineWrap(true);
            JScrollPane jsp = new JScrollPane(ta);
            Calendar currTime = Calendar.getInstance();
            JLabel title = new JLabel(entryFormat.format(currTime.getTime()));
            title.setAlignmentX(Component.LEFT_ALIGNMENT);
            jsp.setAlignmentX(Component.LEFT_ALIGNMENT);
            jsp.setMaximumSize(jsp.getPreferredSize());

            currTab.add(title);
            currTab.add(jsp);
        }

        if(e.getSource().equals(remove)){
            if(days.getSelectedIndex() == (days.getTabCount() - 1)){
                //do nothing to the myInfo Tab
            }
            else{
                int currentTab = days.getSelectedIndex();
                days.remove(currentTab);
            }
        }

        //make name text field to be editable
        if(e.getSource().equals(editName)){
            nametf.setEditable(true);
            save.setVisible(true);
        }

        //make address text field to be editable
        if(e.getSource().equals(editAdd)) {
            addresstf.setEditable(true);
            save.setVisible(true);
        }

        //remove save button and reset menu items and text fields
        if(e.getActionCommand().equals("Save")){
            editName.setState(false);
            editAdd.setState(false);
            nametf.setEditable(false);
            addresstf.setEditable(false);
            save.setVisible(false);
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new personalJournal();
            }
        });
    }
}