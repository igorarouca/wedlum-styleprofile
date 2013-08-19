package com.wedlum.styleprofile.domain.survey;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.wedlum.styleprofile.domain.photo.PhotoSourceMock;
import com.wedlum.styleprofile.util.web.ParseUtils;


public class StyleProfilerTest {

    @Test
    public void testResolveMiniPalettes() throws Exception {
        Profile profile = new Profile();
        profile.addSession("singleColorSession1", Arrays.asList("1a.png","2a.png"));
        profile.addSession("singleColorSession2", Arrays.asList("1b.png"));

        profile.photoSource = new PhotoSourceMock();

        StyleProfiler subject = new StyleProfiler(profile, profile.photoSource);
        Map<String, String> resolved  = subject.resolveAll();

        assertEquals(
        	"{\"miniPalette1\":\"1a_A.png\",\"miniPalette2\":\"2a_A.png\",\"miniPalette3\":\"1b_A.png\"," +
        	 "\"miniPalette4\":\"1a_C.png\",\"miniPalette5\":\"2a_C.png\",\"miniPalette6\":\"1b_C.png\",\"palette1\":\"No photo found featuring color: code\"}",
        	ParseUtils.toJson(resolved)
        );
    }

    @Test
    public void testResolvePalettes() {
    	Profile profile = new Profile();

    	profile.addSession("session1", Arrays.asList("1a.png","2b.png"), Arrays.asList("1a.png", "1a.png","1a.png"));
        profile.addSession("session2", Arrays.asList("1a.png"), Arrays.asList("2b.png", "2b.png"));

        PhotoSourceMock photoSourceMock = new PhotoSourceMock();
        StyleProfiler subject = new StyleProfiler(profile, photoSourceMock);

        photoSourceMock.setMetadataWithoutValidation(
                "1a.png",
                "Photo:\n" +
                        "   Description:\n" +
                        "   Photographer:\n" +
                        "       Drue Carr\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 000S");

        photoSourceMock.setMetadataWithoutValidation(
                "2b.png",
                "Photo:\n" +
                        "   Description:\n" +
                        "   Photographer:\n" +
                        "       Drue Carr\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 220S");

        photoSourceMock.setMetadataWithoutValidation(
                "palette_red.png",
                "Photo:\n" +
                        "   Description:\n" +
                        "   Photographer:\n" +
                        "       Drue Carr\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         1001   \n" +
                        "      PaletteType:\n" +
                        "         Mono\n" +
                        "      Texture:\n" +
                        "         Soft\n" +
                        "      Colors:\n" +
                        "         - 000D\n" +
                        "         - 000M\n" +
                        "         - 000L\n" +
                        "         - 035N\n" +
                        "      FeaturedColor:\n" +
                        "         - 000S\n" +
                        "      Contrast:\n" +
                        "         - Mid\n" +
                        "      Boldness:\n" +
                        "         - Low\n" +
                        "      Foundation:\n" +
                        "         - Mid");

        photoSourceMock.setMetadataWithoutValidation(
                "palette_blue.png",
                "Photo:\n" +
                        "   Description:\n" +
                        "   Photographer:\n" +
                        "       Drue Carr\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         1001   \n" +
                        "      PaletteType:\n" +
                        "         Mono\n" +
                        "      Texture:\n" +
                        "         Soft\n" +
                        "      Colors:\n" +
                        "         - 000D\n" +
                        "         - 000M\n" +
                        "         - 000L\n" +
                        "         - 035N\n" +
                        "      FeaturedColor:\n" +
                        "         - 220S\n" +
                        "      Contrast:\n" +
                        "         - Mid\n" +
                        "      Boldness:\n" +
                        "         - Low\n" +
                        "      Foundation:\n" +
                        "         - Mid");

        Map<String, String> resolved  = subject.resolveAll();

        assertEquals(
            	"{\"palette1\":\"palette_red.png\",\"palette2\":\"palette_blue.png\"}" ,
            	ParseUtils.toJson(resolved)
        );
    }

    @Test
    @Ignore
    public void testStyleAnalysis() {
    	Profile profile = new Profile();
//        profile.addSession("session1", Arrays.asList("1a.png","2b.png"));
//        profile.addSession("session2", Arrays.asList("1a.png"));
//        profile.photoIds = Arrays.asList("1a.png", "1a.png", "1a.png", "2b.png", "2b.png");

        PhotoSourceMock photoSourceMock = new PhotoSourceMock();
        StyleProfiler subject = new StyleProfiler(profile, photoSourceMock);

        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2008_Soft.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2001   \n" +
                        "      PaletteType:\n" +
                        "         Mono\n" +
                        "      Texture:\n" +
                        "         Soft\n" +
                        "      Colors:\n" +
                        "         - 000D\n" +
                        "         - 000M\n" +
                        "         - 000L\n" +
                        "         - 035N\n" +
                        "      FeaturedColor:\n" +
                        "         - 220S\n" +
                        "      Contrast:\n" +
                        "         - Mid\n" +
                        "      Boldness:\n" +
                        "         - Low\n" +
                        "      Foundation:\n" +
                        "         - Mid");

        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2009_Wild.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2002   \n" +
                        "      PaletteType:\n" +
                        "         Mono\n" +
                        "      Texture:\n" +
                        "         Wild\n" +
                        "      Colors:\n" +
                        "         - 170D\n" +
                        "         - 170M\n" +
                        "         - 170S\n" +
                        "         - 220N\n" +
                        "      FeaturedColor:\n" +
                        "         - 330S\n" +
                        "      Contrast:\n" +
                        "         - High\n" +
                        "      Boldness:\n" +
                        "         - Mid\n" +
                        "      Foundation:\n" +
                        "         - Mid");

        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2010_Geo.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2010   \n" +
                        "      PaletteType:\n" +
                        "         Analog\n" +
                        "      Texture:\n" +
                        "         Geo\n" +
                        "      Colors:\n" +
                        "         - 060D\n" +
                        "         - 135M\n" +
                        "         - 135S\n" +
                        "      FeaturedColor:\n" +
                        "         - 345L\n" +
                        "         - 00LG\n" +
                        "      Contrast:\n" +
                        "         - Low\n" +
                        "      Boldness:\n" +
                        "         - High\n" +
                        "      Foundation:\n" +
                        "         - High");

        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2011_Soft.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2011   \n" +
                        "      PaletteType:\n" +
                        "         Triad\n" +
                        "      Texture:\n" +
                        "         Soft\n" +
                        "      Colors:\n" +
                        "         - 100L\n" +
                        "         - 00CG\n" +
                        "         - 00WG\n" +
                        "      FeaturedColor:\n" +
                        "         - 330L\n" +
                        "         - 135M\n" +
                        "      Contrast:\n" +
                        "         - Low\n" +
                        "      Boldness:\n" +
                        "         - Low\n" +
                        "      Foundation:\n" +
                        "         - Low");
      
        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2012_Wild.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2012   \n" +
                        "      PaletteType:\n" +
                        "         Comp\n" +
                        "      Texture:\n" +
                        "         Wild\n" +
                        "      Colors:\n" +
                        "         - 330L\n" +
                        "         - 045M\n" +
                        "         - 220N\n" +
                        "      FeaturedColor:\n" +
                        "         - 330D\n" +
                        "         - 060M\n" +
                        "      Contrast:\n" +
                        "         - High\n" +
                        "      Boldness:\n" +
                        "         - Mid\n" +
                        "      Foundation:\n" +
                        "         - Mid");
      
        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2013_Geo.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2013   \n" +
                        "      PaletteType:\n" +
                        "         Triad\n" +
                        "      Texture:\n" +
                        "         Geo\n" +
                        "      Colors:\n" +
                        "         - 100L\n" +
                        "         - 00CG\n" +
                        "         - 00WG\n" +
                        "      FeaturedColor:\n" +
                        "         - 330L\n" +
                        "         - 135M\n" +
                        "      Contrast:\n" +
                        "         - Mid\n" +
                        "      Boldness:\n" +
                        "         - High\n" +
                        "      Foundation:\n" +
                        "         - Low");

        photoSourceMock.setMetadataWithoutValidation(
                "Pal_2014_Wild.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "      PaletteID:\n" +
                        "         2014   \n" +
                        "      PaletteType:\n" +
                        "         Comp\n" +
                        "      Texture:\n" +
                        "         Wild\n" +
                        "      Colors:\n" +
                        "         - 170S\n" +
                        "         - 100S\n" +
                        "         - 100L\n" +
                        "      FeaturedColor:\n" +
                        "         - 330S\n" +
                        "         - 060M\n" +
                        "      Contrast:\n" +
                        "         - Low\n" +
                        "      Boldness:\n" +
                        "         - High\n" +
                        "      Foundation:\n" +
                        "         - Low");

        String style = subject.style();
        assertEquals("Adjective Noun", style);
    }

}
