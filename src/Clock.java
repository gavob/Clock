public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        //model.addObserver(view); // No longer necessary
        Controller controller = new Controller(model, view);
        
        //new Alarm(model,8,54,"am"); // Test
    }
}
