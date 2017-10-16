import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.StructurizrDocumentationTemplate;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.*;

/**
 * This is a simple example of how to get started with Structurizr for Java.
 */
public class Structurizr {

    private static final long WORKSPACE_ID = 1234;
    private static final String API_KEY = "key";
    private static final String API_SECRET = "secret";

    public static void main(String[] args) throws Exception {
        // a Structurizr workspace is the wrapper for a software architecture model, views and documentation
        Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");
        Model model = workspace.getModel();

        // add some elements to your software architecture model
        Person user = model.addPerson("User", "A user of my software system.");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
        user.uses(softwareSystem, "Uses");

        // define some views (the diagrams you would like to see)
        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "An example of a System Context diagram.");
        contextView.setPaperSize(PaperSize.A5_Landscape);
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        // add some documentation
        StructurizrDocumentationTemplate template = new StructurizrDocumentationTemplate(workspace);
        template.addContextSection(softwareSystem, Format.Markdown,
                "Here is some context about the software system...\n" +
                        "\n" +
                        "![](embed:SystemContext)");

        // add some styling
        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);

        uploadWorkspaceToStructurizr(workspace);
    }

    private static void uploadWorkspaceToStructurizr(Workspace workspace) throws Exception {
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}