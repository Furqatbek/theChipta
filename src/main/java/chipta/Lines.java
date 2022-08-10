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


public String lines() {
    StringBuilder lines = new StringBuilder();
    for (int i=0; i<trainLines.length; i++) {
        lines.append(i + "." + " " + trainLines[i].toUpperCase() + System.lineSeparator());
    }
    return lines.toString();
}
}
