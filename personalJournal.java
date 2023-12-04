import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class personalJournal {
    personalJournal(){
        JFrame jfrm = new JFrame("Journal");
        jfrm.setSize(600, 800);
        jfrm.setLayout(new BorderLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        JMenuBar jmb = new JMenuBar();
        JTabbedPane days = new JTabbedPane();
        JMenu entries = new JMenu("Entries");
        JMenu myInfo = new JMenu("My Info");
        //entries menu items
        JMenuItem newDay = new JMenuItem("New Day");
        JMenuItem newEntry = new JMenuItem("New Entry");
        JMenuItem separator = new JMenuItem("Separate");
        JMenuItem remove = new JMenuItem("Remove Day");
        //myinfo menu items
        JMenuItem editName = new JMenuItem("Edit Name");
        JMenuItem editAdd = new JMenuItem("Edit Address");

        //add menu items to entries
        entries.add(newDay);
        entries.add(newEntry);
        entries.add(separator);
        entries.add(remove);

        //add menu items to myinfo
        myInfo.add(editName);
        myInfo.add(editAdd);

        //add menus to menu bar
        jmb.add(entries);
        jmb.add(myInfo);

        //user info tab
        JPanel userInfo = new JPanel();
        days.addTab("My Info", userInfo);

        //initial entry tab
        JPanel defaultPanel = new JPanel();
        defaultPanel.setLayout(new BoxLayout(defaultPanel,BoxLayout.Y_AXIS));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        Date date = new Date();
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat formatter = new SimpleDateFormat("EEEE");
        String dayOfWeekString = formatter.format(cal.getTime());

        days.add(dayOfWeekString + " " + dateFormat.format(date), defaultPanel);
        days.add("My Info", userInfo);
        jfrm.add(jmb, BorderLayout.PAGE_START);
        jfrm.add(days, BorderLayout.CENTER);
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
