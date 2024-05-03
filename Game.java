public class Game {
	private String name;
    private double size; // in MB
    private String category;
    private String developerTeam;
    private double price; // in USD
    private double rating;
    private int ratingNum; // number of ratings
    private boolean installed;
    private boolean purchased;

    public Game(String name, double size, String category, String developerTeam, double price, double rating, int ratingNum, boolean installed, boolean purchased) {
        this.name = name;
        this.size = size;
        this.category = category;
        this.developerTeam = developerTeam;
        this.price = price;
        this.rating = rating;
        this.ratingNum = ratingNum;
        this.installed = installed;
        this.purchased = purchased;
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
    
    public boolean getPurchase() {
    	return purchased;
    }
    
    public void setPurchase(boolean purchased) {
    	this.purchased = purchased;
    }
}
