package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.utilities.Path;
import gui.ava.html.image.generator.HtmlImageGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hpduy17 on 9/18/15.
 */
public class Html2ImageTest {
    @Before
    public void setup() throws IOException {
        Path.buildRoot();
    }
    @Test
    public void createImage(){
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml("<b>Hello World!</b> Please goto <a title=\"Goto Google\" href=\"http://www.google.com\">Google</a>.");
        imageGenerator.saveAsImage(Path.getImagePath()+"/hello-world.png");
        imageGenerator.saveAsHtmlWithMap(Path.getDataPath()+"/hello-world.html",Path.getImagePath()+"/hello-world.png");
    }


}
