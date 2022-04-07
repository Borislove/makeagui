import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Screen extends JFrame {
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listPeople;
    private JButton buttonNew;
    private JButton buttonSave;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhoneNumber;
    private JTextField textAddress;
    private JLabel labelAge;
    private JTextField textDateOfBirth;
    private JPanel panelMain;
    private ArrayList<Person> people;
    private DefaultListModel listPeopleModel;

    Screen() {
        super("My fancy contacts project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        people = new ArrayList<Person>();
        listPeopleModel = new DefaultListModel();
        listPeople.setModel(listPeopleModel);
        buttonSave.setEnabled(false);

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNewClick(e);
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSavedClick(e);
            }
        });
        listPeople.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listPeopleSelection(e);
            }
        });
    }

    public void buttonNewClick(ActionEvent e) {
        Person p = new Person(
                textName.getText(),
                textEmail.getText(),
                textPhoneNumber.getText(),
                textDateOfBirth.getText()
        );
        people.add(p);
        refreshPeopleList();
    }

    public void buttonSavedClick(ActionEvent e) {
        int personNumber = listPeople.getSelectedIndex();
        if (personNumber >= 0) {
            Person p = people.get(personNumber);
            p.setName(textName.getText());
            p.setEmail(textEmail.getText());
            p.setPhoneNumber(textPhoneNumber.getText());
            p.setDateOfBirth(textDateOfBirth.getText());
            refreshPeopleList();
        }
    }

    public void listPeopleSelection(ListSelectionEvent e) {
        int personNumber = listPeople.getSelectedIndex();
        if (personNumber >= 0) {
            Person p = people.get(personNumber);
            textName.setText(p.getName());
            textEmail.setText(p.getName());
            textPhoneNumber.setText(p.getPhoneNumber());
            textDateOfBirth.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            labelAge.setText(Integer.toString(p.getAge()) + "years");
            buttonSave.setEnabled(true);
        } else {
            buttonSave.setEnabled(false);
        }
    }

    public void refreshPeopleList() {
        listPeopleModel.removeAllElements();
        System.out.println("Removing all people from list");
        for (Person p : people) {
            System.out.println("Adding person to list: " + p.getName());
            listPeopleModel.addElement(p.getName());
        }
    }

    public void addPerson(Person p) {
        people.add(p);
        refreshPeopleList();
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.setVisible(true);

        Person person1 = new Person("person 1", "person_1@gmail.com", "111 00001", "23/01/1960");
        Person person2 = new Person("person 2", "person_2@gmail.com", "222 00002", "22/02/1001");
        Person person3 = new Person("person 3", "person_3@gmail.com", "333 00003", "23/03/1011");
        Person person4 = new Person("person 4", "person_4@gmail.com", "444 00004", "24/04/1001");
        Person person5 = new Person("person 5", "person_5@gmail.com", "555 00005", "25/04/1110");
        Person person6 = new Person("person 6", "person_6@gmail.com", "666 00006", "26/06/1101");
        Person person7 = new Person("person 7", "person_7@gmail.com", "777 00007", "27/07/1001");

        screen.addPerson(person1);
        screen.addPerson(person2);
        screen.addPerson(person3);
        screen.addPerson(person4);
        screen.addPerson(person5);
        screen.addPerson(person6);
        screen.addPerson(person7);
    }
}
