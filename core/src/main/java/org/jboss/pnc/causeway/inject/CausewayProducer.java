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
package org.jboss.pnc.causeway.inject;

import org.commonjava.indy.client.core.Indy;
import org.commonjava.indy.client.core.IndyClientException;
import org.commonjava.util.jhttpc.HttpFactory;
import org.commonjava.util.jhttpc.auth.MemoryPasswordManager;
import org.commonjava.util.jhttpc.auth.PasswordManager;
import org.commonjava.util.jhttpc.auth.PasswordType;
import org.jboss.pnc.causeway.config.CausewayConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by jdcasey on 11/10/15.
 */
@ApplicationScoped
public class CausewayProducer
        implements Closeable
{
    @Inject
    private CausewayConfig config;

    private HttpFactory httpFactory;

    protected CausewayProducer()
    {
    }

    public CausewayProducer( CausewayConfig config )
    {
        this.config = config;
        setup();
    }

    @PostConstruct
    public void setup()
    {
        PasswordManager passwords = new MemoryPasswordManager();
        passwords.bind( config.getKojiClientCertificatePassword(), CausewayConfig.KOJI_SITE_ID, PasswordType.KEY );

        httpFactory = new HttpFactory( passwords );
    }

    @PreDestroy
    public void close()
    {
        if ( httpFactory != null )
        {
            try
            {
                httpFactory.close();
            }
            catch ( IOException e )
            {
                throw new RuntimeException( "Close httpFactory error " + e.getMessage(), e );
            }
        }
    }

    @Produces
    @Default
    public HttpFactory getHttpFactory()
    {
        return httpFactory;
    }

    @Produces
    @Default
    public Indy getIndy() throws IndyClientException {
        return new Indy(config.getIndyURL()).connect();
    }

    public void closeIndy(@Disposes Indy indy) {
        indy.close();
    }

}
