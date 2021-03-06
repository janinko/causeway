/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.causeway.pncclient.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BuildEnvironment")
@Deprecated
public class BuildEnvironmentRest {

    private Integer id;

    private String name;

    private String description;

    private String systemImageRepositoryUrl;

    private String systemImageId;

    private Map<String, String> attributes;

    private SystemImageType systemImageType;

    public BuildEnvironmentRest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SystemImageType getSystemImageType() {
        return systemImageType;
    }

    public void setSystemImageType(SystemImageType systemImageType) {
        this.systemImageType = systemImageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Deprecated
    public String getImageRepositoryUrl() {
        return systemImageRepositoryUrl;
    }

    @Deprecated
    public void setImageRepositoryUrl(String systemImageRepositoryUrl) {
        this.systemImageRepositoryUrl = systemImageRepositoryUrl;
    }

    public String getSystemImageRepositoryUrl() {
        return systemImageRepositoryUrl;
    }

    public void setSystemImageRepositoryUrl(String systemImageRepositoryUrl) {
        this.systemImageRepositoryUrl = systemImageRepositoryUrl;
    }

    public String getSystemImageId() {
        return systemImageId;
    }

    public void setSystemImageId(String systemImageId) {
        this.systemImageId = systemImageId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}
