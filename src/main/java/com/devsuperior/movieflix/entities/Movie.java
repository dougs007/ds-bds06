package com.devsuperior.movieflix.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    private Integer yearsMovie;
    private String imgUrl;
    @Column(columnDefinition = "TEXT")
    private String synopsis;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie() {
    }

    public Movie(Long id, String title, String subTitle, Integer yearsMovie, String imgUrl, String synopsis, Genre genre) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.yearsMovie = yearsMovie;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return yearsMovie;
    }

    public void setYear(Integer yearsMovie) {
        this.yearsMovie = yearsMovie;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getId(), movie.getId()) && Objects.equals(getTitle(), movie.getTitle()) && Objects.equals(getSubTitle(), movie.getSubTitle()) && Objects.equals(getYear(), movie.getYear()) && Objects.equals(getImgUrl(), movie.getImgUrl()) && Objects.equals(getSynopsis(), movie.getSynopsis()) && Objects.equals(getReviews(), movie.getReviews()) && Objects.equals(getGenre(), movie.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
