package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class Name implements Serializable
{
    private String text;

    private String html;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getHtml ()
    {
        return html;
    }

    public void setHtml (String html)
    {
        this.html = html;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", html = "+html+"]";
    }
}
