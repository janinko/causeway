/**
 * Copyright (C) 2015 Red Hat, Inc. (jdcasey@commonjava.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.causeway.koji.model.messages;

import org.commonjava.rwx.estream.model.Event;
import org.commonjava.rwx.impl.estream.EventStreamParserImpl;
import org.commonjava.rwx.impl.stax.StaxParser;
import org.jboss.pnc.causeway.koji.KojiBindery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.InputStream;
import java.util.List;

/**
 * Created by jdcasey on 12/3/15.
 */
public class AbstractKojiMessageTest
{
    protected static final String MESSAGES_BASE = "messages/";

    protected static KojiBindery bindery;

    protected EventStreamParserImpl eventParser;

    @BeforeClass
    public static void setupClass()
            throws Exception
    {
        bindery = new KojiBindery();
    }

    @Before
    public void setup()
            throws Exception
    {
        eventParser = new EventStreamParserImpl();
    }

    protected List<Event<?>> parseEvents( String resourceFile )
            throws Exception
    {
        String resource = MESSAGES_BASE + resourceFile;
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream( resource ))
        {
            if ( is == null )
            {
                Assert.fail( "Cannot find message XML file on classpath: " + resource );
            }

            eventParser.clearEvents();

            StaxParser parser = new StaxParser( is );
            parser.parse( eventParser );

            List<Event<?>> events = eventParser.getEvents();

            return events;
        }
        finally
        {
            eventParser.clearEvents();
        }
    }

}
