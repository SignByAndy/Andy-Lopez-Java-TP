package andy.lopez;

public class Movie {
	
	private String name;
	
	private String time;

	public Movie(String name, String time) {
        this.name = name;
        this.time = time;
    }
	
	@Override
    public String toString() {
        return "Movie{name='" + name + "', time='" + time + "'}";
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
