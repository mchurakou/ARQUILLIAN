package com.mikalai.ui;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Created by mikalai on 22.02.2016.
 */
public class Deployments {
    private static final String WEBAPP_SRC = "src/main/webapp";

    public static WebArchive getWebArchive() {
        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addClasses(Credentials.class, User.class, LoginController.class)
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                                .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
    }
}
