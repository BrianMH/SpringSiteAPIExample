package com.example.favoritemoviesite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Set;

/**
 * Takes in all the data that is requested using the movie API. Note that this particularly represents only the
 * shortened version of the movie response from the API.
 */
@Data
@Entity
@Table
public class Movie {
    @Id
    @JsonProperty("imdbID")
    private String id;
    @JsonProperty("Year")
    private int year;
    @JsonProperty("Title")
    private String title;
    @Transient
    @JsonProperty("Poster")
    private String posterURL;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private String image;

    // These are additional properties provided only by the complex wrapper
    private String rating;
    private String released;
    private String genre;

    @OneToMany
    private Set<Rating> ratings;

    public boolean convertURLToByteCode() {
        if(image != null || posterURL.isEmpty()) {
            return false; // cannot convert if already exists or does not exist
        }

        // attempt image grab and base64 conversion
        try {
            BufferedImage bImg = ImageIO.read(new URL(this.posterURL));

            // desired thumbnail size is one with max height of 150px
            double scaleRatio = 150.0 / bImg.getHeight();
            Image scaledImg = bImg.getScaledInstance((int)(bImg.getWidth() * scaleRatio), (int)(bImg.getHeight()*scaleRatio), Image.SCALE_SMOOTH);
            BufferedImage oImg = new BufferedImage((int)(bImg.getWidth() * scaleRatio), (int)(bImg.getHeight()*scaleRatio), BufferedImage.TYPE_INT_RGB);
            oImg.getGraphics().drawImage(scaledImg, 0, 0, new Color(0,0,0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(oImg, "jpg", buffer);

            this.setImage(Base64.getEncoder().encodeToString(buffer.toByteArray()));
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "{Movie (id=" + this.id + ")- Yr:" + this.year + ", Title:" + this.title + "}";
    }
}
