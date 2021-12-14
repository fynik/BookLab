package ed.inno.javajunior.booklab.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 65535)
    private String description;

    @Column(name = "publ_year")
    private Integer publishedYear;

    @Column(name = "addition_date")
    private LocalDateTime additionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File cover;

    @ManyToMany
    private List<User> readers = new ArrayList<>();






}
