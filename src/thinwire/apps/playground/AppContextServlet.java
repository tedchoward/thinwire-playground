package thinwire.apps.playground;

//Dummy class that allows the app's entry point to originate from it's own context
//This allows the thinwire.jar to be shared in the 'shared/lib' folder of tomcat
//among many ThinWire applications.
public class AppContextServlet extends thinwire.render.web.WebServlet {
    public static void main(String[] args) {
        new Main();
    }
}
