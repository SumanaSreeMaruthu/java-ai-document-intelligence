package dev.sumana.portfolio;

import jakarta.persistence.*;

@Entity
public class DocumentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 20000)
    private String text;

    protected DocumentRecord() {
    }

    public DocumentRecord(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }
}
