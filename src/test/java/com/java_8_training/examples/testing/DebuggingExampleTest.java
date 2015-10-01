package com.java_8_training.examples.testing;

import com.java_8_training.music_domain.Album;
import com.java_8_training.music_domain.Artist;
import com.java_8_training.music_domain.SampleData;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DebuggingExampleTest {

    private static final Album album = SampleData.beatlesForSale;

    @Test
    public void imperativeNationalityReport() {
        Set<String> nationalities = new HashSet<>();
        for (Artist artist : album.getMusicianList()) {
            if (artist.getName().startsWith("The")) {
                String nationality = artist.getNationality();
                System.out.println("Found nationality: " + nationality);
                nationalities.add(nationality);
            }
        }

        System.out.println(nationalities);
    }

    @Test
    public void forEachLoggingFailure() {
        album.getMusicians()
             .filter(artist -> artist.getName().startsWith("The"))
             .map(artist -> artist.getNationality())
             .forEach(nationality -> System.out.println("Found: " + nationality));

        Set<String> nationalities
            = album.getMusicians()
                   .filter(artist -> artist.getName().startsWith("The"))
                   .map(artist -> artist.getNationality())
                   .collect(Collectors.<String>toSet());

        System.out.println(nationalities);
    }

    @Test
    public void nationalityReportUsingPeek() {
        Set<String> nationalities
            = album.getMusicians()
                   .filter(artist -> artist.getName().startsWith("The"))
                   .map(artist -> artist.getNationality())
                   .peek(nation -> System.out.println("Found nationality: " + nation))
                   .collect(Collectors.<String>toSet());

        System.out.println(nationalities);
    }

}
