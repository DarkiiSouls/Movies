package com.example.darki.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("vote_count")
    private Integer votecount;
    @SerializedName("id")
    private Integer id;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Integer voteaverage;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterpath;
    @SerializedName("original_language")
    private String originallanguage;
    @SerializedName("original_title")
    private String originaltitle;
    @SerializedName("genre_ids")
    private List<Integer> genreids=new ArrayList<Integer>();
    @SerializedName("backdrop_path")
    private String backdroppath;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releasedate;


    public Movie(Integer votecount, Integer id, Boolean video, Integer voteaverage, String title, Double popularity, String posterpath
            , String originallanguage, String originaltitle, List<Integer> genreids, String backdroppath, Boolean adult, String overview
            , String releasedate) {
        this.votecount = votecount;
        this.id = id;
        this.video = video;
        this.voteaverage = voteaverage;
        this.title = title;
        this.popularity = popularity;
        this.posterpath = posterpath;
        this.originallanguage = originallanguage;
        this.originaltitle = originaltitle;
        this.genreids = genreids;
        this.backdroppath = backdroppath;
        this.adult = adult;
        this.overview = overview;
        this.releasedate = releasedate;
    }
    String baseImageUrl="http://image.tmdb.org/t/p/w500";

    public Integer getVotecount() {
        return votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Integer getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(Integer voteaverage) {
        this.voteaverage = voteaverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterpath() {
        return "http://image.tmdb.org/t/p/w500"+ posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getOriginallanguage() {
        return originallanguage;
    }

    public void setOriginallanguage(String originallanguage) {
        this.originallanguage = originallanguage;
    }

    public String getOriginaltitle() {
        return originaltitle;
    }

    public void setOriginaltitle(String originaltitle) {
        this.originaltitle = originaltitle;
    }

    public List<Integer> getGenreids() {
        return genreids;
    }

    public void setGenreids(List<Integer> genreids) {
        this.genreids = genreids;
    }

    public String getBackdroppath() {
        return backdroppath;
    }

    public void setBackdroppath(String backdroppath) {
        this.backdroppath = backdroppath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }
}
