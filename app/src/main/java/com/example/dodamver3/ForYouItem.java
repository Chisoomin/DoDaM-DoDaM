package com.example.dodamver3;

public class ForYouItem {
    Integer happyInte, badInte, sadInte;
    String videoId;
    String music;
    String artist;
    byte[] albumImage;

    public byte[] getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(byte[] albumImage) {
        this.albumImage = albumImage;
    }

    public Integer getHappyInte() {
        return happyInte;
    }

    public void setHappyInte(Integer happyInte) {
        this.happyInte = happyInte;
    }

    public Integer getBadInte() {
        return badInte;
    }

    public void setBadInte(Integer badInte) {
        this.badInte = badInte;
    }

    public Integer getSadInte() {
        return sadInte;
    }

    public void setSadInte(Integer sadInte) {
        this.sadInte = sadInte;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
