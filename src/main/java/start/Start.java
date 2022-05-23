package start;

import presentation.controller.Controller;
import presentation.view.FirstView;

public class Start {
    public static void main(String[] args){
        FirstView theView = new FirstView();
        Controller theController = new Controller(theView);
    }
}
