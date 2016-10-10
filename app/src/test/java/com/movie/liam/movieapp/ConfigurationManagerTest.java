package com.movie.liam.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Image;
import com.movie.liam.movieapp.utils.ConfigurationManager;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by lduf0001 on 10/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationManagerTest {

    public static final String URL = "http://test.com/";
    public static final String PATH = "/path.png";
    ConfigurationManager configurationManager;
    @Mock Context context;


    @Before
    public void setUp() throws Exception {
        configurationManager = spy(new ConfigurationManager(context));
        ConfigurationManager.configuration = new Configuration(null, getImage());
    }

    @NonNull
    private Image getImage() {
        List<String> sizes = new ArrayList<String>();
        sizes.add("w200");
        sizes.add("w350");
        sizes.add("w400");
        sizes.add("original");

        return new Image(sizes, sizes, sizes, sizes, URL, URL, sizes);
    }

    @Test
    public void testGetUrl() {
        doReturn(401).when(configurationManager).getDensity();
        assertEquals(configurationManager.getUrl(PATH, Image.Type.POSTER), URL + "w400" + PATH);

        doReturn(400).when(configurationManager).getDensity();
        assertEquals(configurationManager.getUrl(PATH, Image.Type.BACKDROP), URL + "w350" + PATH);

        doReturn(351).when(configurationManager).getDensity();
        assertEquals(configurationManager.getUrl(PATH, Image.Type.STILL), URL + "w350" + PATH);

        doReturn(350).when(configurationManager).getDensity();
        assertEquals(configurationManager.getUrl(PATH, Image.Type.LOGO), URL + "w200" + PATH);

        doReturn(10).when(configurationManager).getDensity();
        assertEquals(configurationManager.getUrl(PATH, Image.Type.LOGO), URL + "w200" + PATH);

    }

    @Test
    public void testSetAndGetConfiguration() {
        configurationManager.setConfiguration(new Configuration(null, getImage()));
        assertTrue(configurationManager.hasConfiguration());
    }

}