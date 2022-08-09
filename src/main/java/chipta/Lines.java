package chipta;


public class Lines {

    String[] trainLines = {"nukus", "urganch", "hazorasp", "buxoro", "samarqand", "toshkent", "navoiy", "andijon", "qarshi", "jizzax", "termiz", "xiva", "guliston"};

    public boolean correctLineFrom(String city) {
        for (String trainLine : trainLines) {
            if (city.toLowerCase().equals(trainLine)) {
                return true;
            }
        }
        return false;
}

public void cities() {
        for (int i=0; i< trainLines.length; i++) {
            System.out.println(trainLines[i].toUpperCase());
        }
}

}
