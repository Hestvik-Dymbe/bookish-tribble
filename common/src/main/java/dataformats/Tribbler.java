package dataformats;

public class Tribbler {

    private Long    tribblerId;
    private String  name;

    public Tribbler(Long tribblerId, String name) {
        this.tribblerId = tribblerId;
        this.name = name;
    }

    public boolean isValid() {
        return  tribblerId  != null &&
                name        != null;
    }

    public Long getTribblerId() {
        return tribblerId;
    }

    public void setTribblerId(Long tribblerId) {
        this.tribblerId = tribblerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tribbler{" +
                "tribblerId=" + tribblerId +
                ", name='" + name + '\'' +
                '}';
    }
}
