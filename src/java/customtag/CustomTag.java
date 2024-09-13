/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customtag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Dell
 */
public class CustomTag extends SimpleTagSupport {

    private String name;
    private int role;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        // Set default date format if time is empty or null

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            if (name.isEmpty()) {
                name = "No name";
            }
            String roleString;
            if(role==1){
                roleString = "ADMIN";
            } else {
                roleString = "STAFF";
            }
            out.print(name + "<br>");
            out.print("Role: " + "<b style='color: red'>" + roleString +"</b>");
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in CustomTag tag", ex);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
