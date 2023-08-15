interface NBAPlayersInterface {
    public String getName();
    public double getPoints();
    public double getAssist();
    public double getRebounds();
}

// public class (implemented primarily in final app week)

public class PlayerStats implements Comparable<PlayerStats>, NBAPlayersInterface {
    String name;
    double points;
    double assists;
    double rebounds;

    public PlayerStats(String name, double points,double rebounds,  double assists) {
        this.name = name;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
    }
     // class to get name of player  given a playerstat
    @Override
    public String getName() {
        return this.name;
    }

    //class to get number of points given a player
    @Override
    public double getPoints() {
        return this.points;
    }

    // class to get number of assists given a player
    @Override
    public double getAssist() {
        return this.assists;
    }

    // class to get number of rebounds given a playerstat
       @Override
    public double getRebounds() {
        return this.rebounds;
    }

    @Override
    public int compareTo(PlayerStats o) {
        return this.name.compareTo(o.getName());
    }

}

// Placeholder code
class PlayerStatsPlaceholderA implements NBAPlayersInterface{
    public String getName() { return "LeBron James"; }
  public double getPoints() { return 26; }
    public double getAssist() { return 5.3; }
    public double getRebounds() { return 7.4; }

}
class PlayerStatsPlaceholderB implements NBAPlayersInterface {
    public String getName() { return "Giannis Antetokounmpo"; }
    public double getPoints() { return 24.5; }
    public double getAssist() { return 6.5; }
    public double getRebounds() { return 9.1; }

}
class PlayerStatsPlaceholderC implements NBAPlayersInterface {
    public String getName() { return "Kevin Durant"; }
    public double getPoints() { return 31.0; }
    public double getAssist() { return 5.8; }
    public double getRebounds() { return 7.1; }

}







