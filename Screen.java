import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener{

    MinHeap<Ticket> heap;

    private JButton clientView;
    private JButton techView;
    private JButton completedView;

    private JButton addButton;
    private JTextField nameAdd;
    private JTextArea descriptionAdd;
    private JTextField priorityAdd;

    private JButton done;
    private JTextArea completionNote;
    private JTextArea details;

    private Boolean client = true;
    private Boolean tech = false;
    private Boolean complete = false;

    private JButton nextCompletedTask;
    private JTextArea completedTaskNote;
    private JTextArea completedTaskDescription;

    private int time = 3;
    private int index = 0;

    DLList<Ticket> completed;

    public Screen(){
        heap = new MinHeap<Ticket>();
        completed = new DLList<Ticket>();

        clientView = new JButton();
        clientView.setFont(new Font("Arial", Font.BOLD, 20));
        clientView.setHorizontalAlignment(SwingConstants.CENTER);
        clientView.setBounds(5, 5, 100, 30);
        clientView.setText("Client");
        this.add(clientView);
        clientView.addActionListener(this);
        
        techView = new JButton();
        techView.setFont(new Font("Arial", Font.BOLD, 20));
        techView.setHorizontalAlignment(SwingConstants.CENTER);
        techView.setBounds(115, 5, 100, 30);
        techView.setText("Tech");
        this.add(techView);
        techView.addActionListener(this);
        
        completedView = new JButton();
        completedView.setFont(new Font("Arial", Font.BOLD, 20));
        completedView.setHorizontalAlignment(SwingConstants.CENTER);
        completedView.setBounds(225, 5, 150, 30);
        completedView.setText("Completed");
        this.add(completedView);
        completedView.addActionListener(this);

        addButton = new JButton();
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);
        addButton.setBounds(305, 505, 200, 30);
        addButton.setText("ADD");
        this.add(addButton);
        addButton.addActionListener(this);
        
        nameAdd = new JTextField();
        nameAdd.setFont(new Font("Arial", Font.PLAIN, 20));
        nameAdd.setHorizontalAlignment(SwingConstants.CENTER);
        nameAdd.setBounds(205, 105, 200, 30);
        nameAdd.setText("Name");
        this.add(nameAdd);
        
        descriptionAdd = new JTextArea();
        descriptionAdd.setFont(new Font("Arial", Font.PLAIN, 20));
        descriptionAdd.setBounds(155, 155, 500, 300);
        descriptionAdd.setText("Description");
        this.add(descriptionAdd);

        priorityAdd = new JTextField();
        priorityAdd.setHorizontalAlignment(SwingConstants.CENTER);
        priorityAdd.setFont(new Font("Arial", Font.PLAIN, 20));
        priorityAdd.setBounds(405, 105, 200, 30);
        priorityAdd.setText("Priority");
        this.add(priorityAdd);

        done = new JButton();
        done.setFont(new Font("Arial", Font.BOLD, 20));
        done.setHorizontalAlignment(SwingConstants.CENTER);
        done.setBounds(305, 505, 200, 30);
        done.setText("DONE");
        this.add(done);
        done.addActionListener(this);

        details = new JTextArea();
        details.setFont(new Font("Arial", Font.PLAIN, 20));
        details.setBounds(100, 155, 300, 300);
        details.setEditable(false);
        this.add(details);

        completionNote = new JTextArea();
        completionNote.setFont(new Font("Arial", Font.PLAIN, 20));
        completionNote.setBounds(410, 155, 300, 300);
        completionNote.setText("Note");
        this.add(completionNote);

        completedTaskDescription = new JTextArea();
        completedTaskDescription.setFont(new Font("Arial", Font.PLAIN, 20));
        completedTaskDescription.setBounds(100, 155, 300, 300);
        completedTaskDescription.setEditable(false);
        this.add(completedTaskDescription);

        completedTaskNote = new JTextArea();
        completedTaskNote.setFont(new Font("Arial", Font.PLAIN, 20));
        completedTaskNote.setBounds(410, 155, 300, 300);
        completedTaskNote.setEditable(false);
        this.add(completedTaskNote);

        nextCompletedTask = new JButton();
        nextCompletedTask.setFont(new Font("Arial", Font.BOLD, 20));
        nextCompletedTask.setHorizontalAlignment(SwingConstants.CENTER);
        nextCompletedTask.setBounds(305, 505, 200, 30);
        nextCompletedTask.setText("Next");
        this.add(nextCompletedTask);
        nextCompletedTask.addActionListener(this);

        heap.add(new Ticket("Bob", "Unions being formed", 2, 0));
        heap.add(new Ticket("John", "Proletarian are rising", 3, 1));
        heap.add(new Ticket("Shelly", "child labor", 1, 2));


        setLayout(null);
    }

    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        addButton.setVisible(client);
        nameAdd.setVisible(client);
        descriptionAdd.setVisible(client);
        priorityAdd.setVisible(client);
        
        done.setVisible(tech);
        details.setVisible(tech);
        completionNote.setVisible(tech);

        completedTaskDescription.setVisible(complete);
        completedTaskNote.setVisible(complete);
        nextCompletedTask.setVisible(complete);
        
        
        repaint();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == clientView){
            client = true;
            tech = false;
            complete = false;
        }

        else if(e.getSource() == completedView){
            complete = true;
            tech = false;
            client = false;

            if(completed.size() > 0){
                completedTaskDescription.setText(completed.get(index).getName() + "\n" + completed.get(index).getDescription());
                completedTaskNote.setText(completed.get(index).getCompletionNote());
            }
            else{
                completedTaskDescription.setText("No completed task");
            }
        }

        else if(e.getSource() == techView){
            tech = true;
            client = false;
            complete = false;

            if(heap.size() > 0){
                Ticket temp = heap.peek();
                String detailText = temp.getName() + "\nPriority: " + temp.getPriority() + "\n" + temp.getDescription();
                details.setText(detailText);
            }
            else{
                details.setText("No task left");
            }
        }

        else if(e.getSource() == addButton){
            String name = nameAdd.getText();
            String description = descriptionAdd.getText();
            int priority = Integer.parseInt(priorityAdd.getText());

            heap.add(new Ticket(name, description, priority, time));

            nameAdd.setText("Name");
            priorityAdd.setText("Priority");
            descriptionAdd.setText("Description");
        }

        else if(e.getSource() == done){
            heap.peek().setCompletionNote(completionNote.getText());
            completed.add(heap.poll());
            
            completionNote.setText("Note");

            if(heap.size() > 0){
                Ticket temp = heap.peek();
                String detailText = temp.getName() + "\nPriority: " + temp.getPriority() + "\n" + temp.getDescription();
                details.setText(detailText);
            }
            else{
                details.setText("No task left");
            }
        }
        
        else if(e.getSource() == nextCompletedTask){
            if(index < completed.size() - 1){
                index++;
            }
            else{
                index = 0;
            }

            if(completed.size() > 0){
                completedTaskDescription.setText(completed.get(index).getName() + "\n" + completed.get(index).getDescription());
                completedTaskNote.setText(completed.get(index).getCompletionNote());
            }
            else{
                completedTaskDescription.setText("No completed task");
            }
        }

        repaint();
    }
}
