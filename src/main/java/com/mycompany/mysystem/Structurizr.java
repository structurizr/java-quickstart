package com.mycompany.mysystem;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.io.WorkspaceWriterException;
import com.structurizr.io.json.JsonWriter;
import com.structurizr.model.Model;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;

import java.io.StringWriter;

/**
 * This is a simple example of how to get started with Structurizr for Java.
 */
public class Structurizr {

    public static void main(String[] args) throws Exception {
        Workspace workspace = new Workspace("My software system", "A description of my software system.");
        Model model = workspace.getModel();
        ViewSet viewSet = workspace.getViews();
        Styles styles = viewSet.getConfiguration().getStyles();

        // create your model, views and styles

        outputWorkspaceToConsole(workspace);
        uploadWorkspaceToStructurizr(workspace);
    }

    private static void outputWorkspaceToConsole(Workspace workspace) throws WorkspaceWriterException {
        JsonWriter jsonWriter = new JsonWriter(true);
        StringWriter stringWriter = new StringWriter();
        jsonWriter.write(workspace, stringWriter);
        System.out.println(stringWriter.toString());
    }

    private static void uploadWorkspaceToStructurizr(Workspace workspace) throws Exception {
        StructurizrClient structurizrClient = new StructurizrClient("key", "secret");
        structurizrClient.putWorkspace(1234, workspace);
    }

}