package Klasser;

import java.time.LocalDate;

class Customer {
    private String name;
    private Long nummer;
    private LocalDate year;

    String getName() { return name; }
    void setName(String name) { this.name = name; }

    Long getNummer() { return nummer; }
    void setNummer(Long nummer) { this.nummer = nummer; }

    LocalDate getYear() { return year; }
    void setYear(LocalDate year) { this.year = year; }

    Customer(String name, Long nummer, String year) {
        this.name = name;
        this.nummer = nummer;
        this.year = LocalDate.parse(year);
    }
}
