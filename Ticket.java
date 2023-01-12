public class Ticket implements Comparable<Ticket>{
    private String name;
    private String description;
    private String completionNote;
    private int priority;
    private int timeAdded;

    public Ticket(String name, String description, int priority, int timeAdded){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.timeAdded = timeAdded;
    }

    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }

    public void setCompletionNote(String note){
        this.completionNote = note;
    }

    public String getCompletionNote(){
        return completionNote;
    }

    public int getPriority(){
        return priority;
    }

    public int getTimeAdded(){
        return timeAdded;
    }

    @Override
    public int compareTo(Ticket compare){
        if(this.priority > compare.getPriority()){
            return 1;
        }
        else if(this.priority < compare.getPriority()){
            return -1;
        }
        else{
            if(this.timeAdded > compare.getTimeAdded()){
                return 1;
            }
            else if(this.timeAdded < compare.getTimeAdded()){
                return -1;
            }
        }
        return 0;
    }
}