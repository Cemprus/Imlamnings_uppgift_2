package Klasser;

import java.time.LocalDate;

class Customer {
    private String name;
    private Long nummer;
    private LocalDate year;

    String getName() { return name; }

    Long getNummer() { return nummer; }

    LocalDate getYear() { return year; }

    Customer(String name, Long nummer, String year) {
        this.name = name;
        this.nummer = nummer;
        this.year = LocalDate.parse(year);
    }
}
