package org.coode.html.test;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.coode.html.util.URLUtils;

import java.net.MalformedURLException;
import java.net.URL;
/*
* Copyright (C) 2007, University of Manchester
*
* Modifications to the initial code base are copyright of their
* respective authors, or their employers as appropriate.  Authorship
* of the modifications may be determined from the ChangeLog placed at
* the end of this file.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.

* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/**
 * Author: drummond<br>
 * http://www.cs.man.ac.uk/~drummond/<br><br>
 * <p/>
 * The University Of Manchester<br>
 * Bio Health Informatics Group<br>
 * Date: Mar 28, 2008<br><br>
 */
public class URLTests extends TestCase {

    private static final Logger logger = Logger.getLogger(URLTests.class);


    public void testRelativeURLBothEndingWithPage(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/Beef.html");
            URL target = new URL("http://www.co-ode.org/ontologies/properties/hasMeat.html");
            assertEquals("../properties/hasMeat.html", URLUtils.createRelativeURL(current, target));
            assertEquals("../classes/Beef.html", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLSubdir(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/Beef.html");
            URL target = new URL("http://www.co-ode.org/ontologies/classes/subdir/Gibbon.html");
            assertEquals("subdir/Gibbon.html", URLUtils.createRelativeURL(current, target));
            assertEquals("../Beef.html", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLSuperdir(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/Beef.html");
            URL target = new URL("http://www.co-ode.org/ontologies/Tree.html");
            assertEquals("../Tree.html", URLUtils.createRelativeURL(current, target));
            assertEquals("classes/Beef.html", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLIndexToNamedPage(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/");
            URL target = new URL("http://www.co-ode.org/ontologies/classes/Tree.html");
            assertEquals("Tree.html", URLUtils.createRelativeURL(current, target));
            assertEquals("/", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLQuerySameLevel(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/Beef.html");
            URL target = new URL("http://www.co-ode.org/ontologies/classes/?name=Monkey&base=http://www.co-ode.org/ontologies/");
            assertEquals("./?name=Monkey&base=http://www.co-ode.org/ontologies/", URLUtils.createRelativeURL(current, target));
            assertEquals("Beef.html", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLTwoQueriesSameLevel(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/?name=Domain&base=http://www.co-ode.org/ontologies/");
            URL target = new URL("http://www.co-ode.org/ontologies/classes/?name=Monkey&base=http://www.co-ode.org/ontologies/");
            assertEquals("./?name=Monkey&base=http://www.co-ode.org/ontologies/", URLUtils.createRelativeURL(current, target));
            assertEquals("./?name=Domain&base=http://www.co-ode.org/ontologies/", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLEndingWithSlash(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/");
            URL target = new URL("http://www.co-ode.org/ontologies/properties/");

            assertEquals("../properties/", URLUtils.createRelativeURL(current, target));
            assertEquals("../classes/", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }

    public void testRelativeURLEndingWithSlashAndQuery(){
        try {
            URL current = new URL("http://www.co-ode.org/ontologies/classes/?name=Monkey&base=http://www.co-ode.org/ontologies/");
            URL target = new URL("http://www.co-ode.org/ontologies/properties/?name=hasMonkey&base=http://www.co-ode.org/ontologies/");

            assertEquals("../properties/?name=hasMonkey&base=http://www.co-ode.org/ontologies/", URLUtils.createRelativeURL(current, target));
            assertEquals("../classes/?name=Monkey&base=http://www.co-ode.org/ontologies/", URLUtils.createRelativeURL(target, current));
        }
        catch (MalformedURLException e) {
            logger.error(e);
            fail();
        }
    }
}
