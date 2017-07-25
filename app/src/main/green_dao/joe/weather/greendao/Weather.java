package joe.weather.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "WEATHER".
 */
public class Weather {

    private Long id;
    /** Not-null value. */
    private String result;
    private Long date;

    public Weather() {
    }

    public Weather(Long id) {
        this.id = id;
    }

    public Weather(Long id, String result, Long date) {
        this.id = id;
        this.result = result;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getResult() {
        return result;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setResult(String result) {
        this.result = result;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

}