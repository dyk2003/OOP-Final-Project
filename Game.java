public class Game {
	private String name;
    private double size; // in MB
    private String category;
    private String developerTeam;
    private double price; // in USD
    private double rating;
    private int ratingNum; // number of ratings
    private boolean installed;

    public Game(String name, double size, String category, String developerTeam, double price, double rating, int ratingNum, boolean installed) {
        this.name = name;
        this.size = size;
        this.category = category;
        this.developerTeam = developerTeam;
        this.price = price;
        this.rating = rating;
        this.ratingNum = ratingNum;
        this.installed = installed;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public String getCategory() {
        return category;
    }

    public String getDeveloperTeam() {
        return developerTeam;
    }

    public double getPrice() {
        return price;
    }
    
    public double getRating() {
        return rating;
    }
    
    public int getRatingNum() {
    	return ratingNum;
    }
    
    public void addRatingNum() {
    	ratingNum += 1;
    }
    
    public boolean getInstalled() {
    	return installed;
    }
    
    public void setInstalled(boolean install) {
    	installed = install;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
    }
}
